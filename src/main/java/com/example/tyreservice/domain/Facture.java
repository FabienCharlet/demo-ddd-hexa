package com.example.tyreservice.domain;

import java.time.ZonedDateTime;
import java.util.Map;
import java.util.UUID;

import com.example.tyreservice.domain.validation.Validate;
import com.example.tyreservice.domain.validation.Validate.InvalidValueException;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class Facture implements DomainEntity {

	public static Facture emettre(
			final Vehicule vehicule,
			final Map<UUID, Integer> quantiteAcheteeByPneuIds,
			final Map<UUID, Integer> prixByPneuIds,
			final int prixMontageUnitaire,
			final int avantageCommercial) {

		return new Facture(
				UUID.randomUUID(),
				0,
				vehicule,
				quantiteAcheteeByPneuIds,
				prixByPneuIds,
				prixMontageUnitaire,
				avantageCommercial,
				ZonedDateTime.now(),
				null);
	}

	private final UUID id;
	private final int version;

	private final Vehicule vehicule;
	private final Map<UUID, Integer> quantiteAcheteeByPneuIds;
	private final Map<UUID, Integer> prixByPneuIds;

	private final int prixMontageUnitaire;

	private final int avantageCommercial;

	private final ZonedDateTime dateEmission;
	private final ZonedDateTime datePaiement;


	private Facture(
			final UUID id,
			final int version,
			final Vehicule vehicule,
			final Map<UUID, Integer> quantiteAcheteeByPneuIds,
			final Map<UUID, Integer> prixByPneuIds,
			final int montageUnitaire,
			final int avantageCommercial,
			final ZonedDateTime dateEmission,
			final ZonedDateTime datePaiement) {

		this.id = Validate.notNull(id);
		this.version =  Validate.notNegative(version);

		this.vehicule = Validate.notNull(vehicule);
		this.quantiteAcheteeByPneuIds = Validate.notEmpty(quantiteAcheteeByPneuIds);
		this.prixByPneuIds = Validate.notEmpty(prixByPneuIds);

		Validate.areEquals(prixByPneuIds.keySet(), quantiteAcheteeByPneuIds.keySet());

		this.prixMontageUnitaire = Validate.strictlyPositive(montageUnitaire);
		this.avantageCommercial = avantageCommercial;
		this.dateEmission = Validate.notNull(dateEmission);
		this.datePaiement = datePaiement;

		if (datePaiement != null && ! datePaiement.isAfter(dateEmission)) {

			throw new InvalidValueException("datePaiement", datePaiement + " < " + dateEmission, "datePaiement is before dateEmission");
		}

	}



	public int getTotal() {

		final int totalBeforeAvantage = prixByPneuIds.entrySet().stream()
				.mapToInt(entry -> getSousTotalParPneu(entry.getValue(), quantiteAcheteeByPneuIds.get(entry.getKey())))
				.sum();

		return totalBeforeAvantage - avantageCommercial;
	}

	private int getSousTotalParPneu(final int prix, final int quantite) {

		return (prix * quantite) + (prixMontageUnitaire * quantite);
	}

	public Facture payee(final ZonedDateTime datePaiement) {

		return toBuilder().datePaiement(datePaiement).build();
	}


}
