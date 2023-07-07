package com.example.tyreservice.interfaces.rest.pneu;

import lombok.Data;

@Data
public class PneuCreationCommand {

	private String marque;
	private String modele;
	private String taille;
	private int prix;
}
