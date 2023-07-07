package com.example.tyreservice;

import java.time.LocalDate;

import com.example.tyreservice.domain.Client;
import com.example.tyreservice.domain.Pneu;
import com.example.tyreservice.domain.Vehicule;

public class Values {

	public static final Pneu PNEU_VALID = Pneu.nouvelleReference("Michelon", "Energy", "205x55x16R", 100);

	public static final Client CLIENT_VALID = Client.nouveau(
			"CHARLET",
			"Fabien",
			"42 rue Martin Fowler 13370 MÉRINDOL", LocalDate.of(1981, 5, 4), "0000 0000 0000 0000");

	public static final Vehicule VEHICULE_VALID = Vehicule.nouveau(
			"TESLA",
			"Model S Plaid",
			2023,
			"TE-042-ST",
			CLIENT_VALID);

}
