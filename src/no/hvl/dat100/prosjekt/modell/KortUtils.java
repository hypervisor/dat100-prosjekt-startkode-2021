package no.hvl.dat100.prosjekt.modell;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

import no.hvl.dat100.prosjekt.TODO;

public class KortUtils {

	/**
	 * Sorterer en samling. Rekkefølgen er bestemt av compareTo() i Kort-klassen.
	 * 
	 * @see Kort
	 * 
	 * @param samling
	 * 			samling av kort som skal sorteres. 
	 */
	
	public static void sorter(KortSamling samling) {
		
	}
	
	/**
	 * Stokkar en kortsamling. 
	 * 
	 * @param samling
	 * 			samling av kort som skal stokkes. 
	 */
	public static void stokk(KortSamling samling) {
		var kort = samling.getSamling();
		
		Random r = new Random();
		for (int i = samling.getAntalKort() - 1; i > 0; i--) {
			int index = r.nextInt(i + 1);
		      // Simple swap
		      var gammelVerdi = kort[index];
		      kort[index] = kort[i];
		      kort[i] = gammelVerdi;
		}
	}
	
}
