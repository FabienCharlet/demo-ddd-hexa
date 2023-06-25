package com.example.tyreservice.application;

import java.time.ZonedDateTime;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.tyreservice.domain.Facture;
import com.example.tyreservice.domain.Pneu;
import com.example.tyreservice.domain.Vehicule;

@Service
public class FactureService {

	private static final Logger LOGGER = LoggerFactory.getLogger(FactureService.class);

	private static final int PRIX_UNITAIRE_MONTAGE = 12;

	private final PneuRepository pneuRepository;
	private final FactureRepository factureRepository;

	public FactureService(final PneuRepository pneuRepository, final FactureRepository factureRepository) {
		super();
		this.pneuRepository = pneuRepository;
		this.factureRepository = factureRepository;
	}

	public Facture generer(
			final Vehicule vehicule,
			final Map<UUID, Integer> quantiteAcheteeByPneuIds,
			final Map<UUID, Integer> prixByPneuIds,
			final int avantageCommercial) {

		final Facture facture = Facture.emettre(vehicule, quantiteAcheteeByPneuIds, prixByPneuIds, getPrixUnitaireMontage(), avantageCommercial);

		final Facture res = factureRepository.save(facture);

		LOGGER.info("Facture générée {}", res);

		return res;
	}

	public Facture payer(final Facture facture, final ZonedDateTime datePaiement) {

		final Facture res =  factureRepository.save(facture.payee(datePaiement));

		LOGGER.info("Facture payée {}", res);

		return res;
	}

	public int calculerTotal(final Map<UUID, Integer> quantiteAcheteeByPneuIds) {

		return pneuRepository
				.findByIds(quantiteAcheteeByPneuIds.keySet()).stream()
				.mapToInt(pneu -> getSousTotalParPneu(pneu, quantiteAcheteeByPneuIds.get(pneu.getId())))
				.sum();
	}

	public int getSousTotalParPneu(final Pneu pneu, final int quantite) {

		return (pneu.getPrix() * quantite) + (getPrixUnitaireMontage() * quantite);
	}

	public int getPrixUnitaireMontage() {

		// Could have a more complex logic
		return PRIX_UNITAIRE_MONTAGE;
	}
}
