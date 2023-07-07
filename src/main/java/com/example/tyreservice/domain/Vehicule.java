package com.example.tyreservice.domain;

import java.util.UUID;

import com.example.tyreservice.domain.validation.Validate;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class Vehicule implements DomainEntity {

	public static Vehicule nouveau(
			final String marque,
			final String modele,
			final int anneeSortie,
			final String immatriculation,
			final Client proprietaire) {

		return new Vehicule(UUID.randomUUID(), 0, marque, modele, anneeSortie, immatriculation, proprietaire);
	}

	private final UUID id;
	private final int version;

	private final String marque;
	private final String modele;
	private final int anneeSortie;

	private final String immatriculation;

	private final Client proprietaire;

	private Vehicule(
			final UUID id,
			final int version,
			final String marque,
			final String modele,
			final int anneeSortie,
			final String immatriculation,
			final Client proprietaire) {

		this.id = Validate.notNull(id);
		this.version =  Validate.notNegative(version);

		this.marque = Validate.notEmpty(marque);
		this.modele = Validate.notEmpty(modele);
		this.anneeSortie = Validate.strictlyPositive(anneeSortie);
		this.immatriculation = Validate.notEmpty(immatriculation);

		this.proprietaire = Validate.notNull(proprietaire);
	}

	public Vehicule changementProprietaire(final Client nouveauProprietaire) {

		return toBuilder().proprietaire(nouveauProprietaire).build();
	}

}

