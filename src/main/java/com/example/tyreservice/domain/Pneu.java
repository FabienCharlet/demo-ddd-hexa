package com.example.tyreservice.domain;

import java.util.UUID;

import com.example.tyreservice.domain.validation.Validate;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true, access = AccessLevel.PRIVATE)
public class Pneu implements DomainEntity {

	public static Pneu nouvelleReference(
			final String marque,
			final String modele,
			final String taille,
			final int prix) {

		return new Pneu(UUID.randomUUID(), 0, marque, modele, taille, prix);
	}

	private final UUID id;
	private final int version;

	private final String marque;
	private final String modele;
	private final String taille;

	private final int prix;

	private Pneu(
			final UUID id,
			final int version,
			final String marque,
			final String modele,
			final String taille,
			final int prix) {

		this.id = Validate.notNull(id);
		this.version =  Validate.notNegative(version);
		this.marque = Validate.notEmpty(marque);
		this.modele = Validate.notEmpty(modele);
		this.taille = Validate.notEmpty(taille);
		this.prix = Validate.strictlyPositive(prix);
	}

	public Pneu changementPrix(final int nouveauPrix) {

		return toBuilder().prix(nouveauPrix).build();
	}
}
