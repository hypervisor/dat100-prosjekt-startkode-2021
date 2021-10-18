package no.hvl.dat100.prosjekt.kontroll;

import java.util.Random;

import no.hvl.dat100.prosjekt.TODO;
import no.hvl.dat100.prosjekt.kontroll.dommer.Regler;
import no.hvl.dat100.prosjekt.kontroll.spill.Handling;
import no.hvl.dat100.prosjekt.kontroll.spill.HandlingsType;
import no.hvl.dat100.prosjekt.kontroll.spill.Spillere;
import no.hvl.dat100.prosjekt.modell.Kort;
import no.hvl.dat100.prosjekt.modell.KortSamling;

/**
 * Klasse som for å representere en vriåtter syd-spiller. Strategien er å lete
 * gjennom kortene man har på hand og spille det første som er lovlig.
 *
 */
public class SydSpiller extends Spiller {

	/**
	 * Konstruktør.
	 * 
	 * @param spiller
	 *            posisjon for spilleren (nord eller syd).
	 */
	public SydSpiller(Spillere spiller) {
		super(spiller);
	}

	/**
	 * Metode for å implementere strategi. Strategien er � spille det f�rste
	 * kortet som er lovlig (ogs� en �tter selv om man har andre kort som ogs�
	 * kan spilles). Dersom man ikke har lovlige kort � spille, trekker man om
	 * man ikke allerede har trukket maks antall ganger. I så fall sier man
	 * forbi.
	 * 
	 * @param topp
	 *            kort som ligg øverst på til-bunken.
	 */
	@Override
	public Handling nesteHandling(Kort topp) {
		if (getHand() == null) {
			return new Handling(HandlingsType.FORBI, null);
		}
		// ArrayLister for de kort vi har og kan spille
		Kort[] hand = getHand().getAllekort();
		Kort lovlig = null;
		// Gå igjennom kort å finn ut hvilke som kan spilles
		for (Kort k : hand) {
			if (Regler.kanLeggeNed(k, topp)) {
				lovlig = k;
				break;
			}
		}
		
		Handling handling = null;
		
		if (lovlig != null) {
			handling = new Handling(HandlingsType.LEGGNED, lovlig);
			setAntallTrekk(0);
		} else if (getAntallTrekk() < Regler.maksTrekk()) {
			handling = new Handling(HandlingsType.TREKK, null);
		} else {
			handling = new Handling(HandlingsType.FORBI, null);
			setAntallTrekk(0);
		}
		
		return handling;
	}
}
