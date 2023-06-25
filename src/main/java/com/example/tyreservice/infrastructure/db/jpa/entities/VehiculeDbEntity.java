package com.example.tyreservice.infrastructure.db.jpa.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Version;
import lombok.Data;


@Data
@Entity(name = "Vehicule")
public class VehiculeDbEntity {

	@Id
	private final String id;

	@Version
	private long version;

	private final String marque;
	private final String modele;
	private final int anneeSortie;

	private final String immatriculation;

	@ManyToOne
	private final ClientDbEntity proprietaire;
}
