package com.example.tyreservice.domain;

import java.time.LocalDate;
import java.util.UUID;

import com.example.tyreservice.domain.validation.Validate;

import lombok.Value;

@Value
public class Client {

	private final UUID id;
	private final int version;

	private final String nom;
	private final String prenom;
	private final String adresse;

	private final LocalDate dateNaissance;

	private final String carteBleue;

	public Client(final UUID id, final int version, final String nom, final String prenom, final String adresse, final LocalDate dateNaissance, final String carteBleue) {

		this.id = Validate.notNull(id);
		this.version =  Validate.notNegative(version);
		this.nom = Validate.notEmpty(nom);
		this.prenom = Validate.notEmpty(prenom);
		this.adresse = Validate.notEmpty(adresse);
		this.dateNaissance = Validate.notInPast(dateNaissance);
		this.carteBleue = Validate.notEmpty(carteBleue);
	}



}
