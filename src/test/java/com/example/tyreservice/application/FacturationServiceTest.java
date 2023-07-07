package com.example.tyreservice.application;

import java.time.ZonedDateTime;
import java.util.Map;

import org.junit.Test;

import com.example.tyreservice.Values;
import com.example.tyreservice.domain.Facture;
import com.example.tyreservice.mocks.BankMockRepository;
import com.example.tyreservice.mocks.FactureMockRepository;
import com.example.tyreservice.mocks.PneuMockRepository;
import com.google.common.truth.Truth;

public class FacturationServiceTest {

	private final FactureService service;
	private final BankMockRepository bankRepository;
	private final FactureMockRepository factureRepository;

	public FacturationServiceTest() {

		bankRepository = new BankMockRepository();
		factureRepository = new FactureMockRepository();

		service = new FactureService(new PneuMockRepository(), factureRepository, bankRepository);
	}

	@Test
	public void paiementShallHaveTheCorrectMontant() {

		final Facture factureAPayer = Facture.emettre(
				Values.VEHICULE_VALID,
				Map.of( Values.PNEU_VALID.getId(), 4),
				Map.of( Values.PNEU_VALID.getId(), Values.PNEU_VALID.getPrix()),
				12,
				0);

		service.payer(factureAPayer, ZonedDateTime.now());

		Truth.assertThat(bankRepository.debits).containsExactly(Values.VEHICULE_VALID.getProprietaire().getCarteBleue(), factureAPayer.getTotal());
	}
}
