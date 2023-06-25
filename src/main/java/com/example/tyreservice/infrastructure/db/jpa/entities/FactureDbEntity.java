package com.example.tyreservice.infrastructure.db.jpa.entities;

import java.time.ZonedDateTime;
import java.util.Map;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Version;
import lombok.Data;

@Data
@Entity(name = "Facture")
public class FactureDbEntity {

	@Id
	private String id;

	@Version
	private int version;

	@ManyToOne
	private VehiculeDbEntity vehicule;

	@ElementCollection
	private Map<String, Integer> quantiteAcheteeByPneuIds;

	@ElementCollection
	private Map<String, Integer> prixByPneuIds;

	private int prixMontageUnitaire;

	private int avantageCommercial;

	private ZonedDateTime dateEmission;
	private ZonedDateTime datePaiement;



}
