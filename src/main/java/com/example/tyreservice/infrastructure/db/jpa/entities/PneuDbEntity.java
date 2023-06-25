package com.example.tyreservice.infrastructure.db.jpa.entities;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Version;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "Pneu")
@NoArgsConstructor
public class PneuDbEntity {

	@Id
	private UUID id;

	@Version
	private int version;

	private String marque;
	private String modele;
	private String taille;

	private int prix;

}
