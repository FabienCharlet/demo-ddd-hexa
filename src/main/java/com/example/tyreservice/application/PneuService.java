package com.example.tyreservice.application;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.tyreservice.application.repositories.PneuRepository;
import com.example.tyreservice.domain.Pneu;

@Service
public class PneuService {

	private static final Logger LOGGER = LoggerFactory.getLogger(PneuService.class);

	private final PneuRepository pneuRepository;

	public PneuService(final PneuRepository pneuRepository) {
		super();
		this.pneuRepository = pneuRepository;
	}

	public Pneu nouvelleReference(
			final String marque,
			final String modele,
			final String taille,
			final int prix) {

		final Pneu newPneu = Pneu.nouvelleReference(marque, modele, taille, prix);

		final Pneu res = pneuRepository.save(newPneu);

		LOGGER.info("Nouvrau pneu ajouté {}", res);

		return res;
	}

	public Set<Pneu> findAll() {

		return pneuRepository.findAll();
	}


}
