package com.example.tyreservice.infrastructure.db.jpa.entities;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Version;
import lombok.Data;

@Data
@Entity(name = "Client")
public class ClientDbEntity {

	@Id
	private UUID id;

	@Version
	private long version;

	private String nom;
	private String prenom;
	private String adresse;

	private final LocalDate dateNaissance;

	private final String carteBleue;



}
