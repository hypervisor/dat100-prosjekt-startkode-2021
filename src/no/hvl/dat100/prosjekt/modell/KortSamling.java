package no.hvl.dat100.prosjekt.modell;

import no.hvl.dat100.prosjekt.kontroll.dommer.Regler;

/**
 * Struktur for å lagre ei samling kort. Kan lagre hele kortstokken. Det finnes
 * en konstant i klassen Regler som angir antall kort i hver av de 4 fargene. Når
 * programmet er ferdig settes denne til 13, men under utvikling / testing kan
 * det være praktisk å ha denne mindre.
 * 
 */
public class KortSamling {

	private final int MAKS_KORT = 4 * Regler.MAKS_KORT_FARGE;

	private Kort[] samling;
	private int antall;
	

	/**
	 * Oppretter en tom Kortsamling med plass til MAKS_KORT (hele kortstokken).
	 */
	public KortSamling() {
		antall = 0;
		samling = new Kort[MAKS_KORT];
	}

	/**
	 * Returnerer en tabell med kortene i samlinga. Tabellen trenger ikke være
	 * full. Kortene ligger sammenhengende fra starten av tabellen. Kan få
	 * tilgang til antallet ved å bruke metoden getAntallKort(). Metoden er
	 * først og fremst ment å brukes i testklasser. Om man trenger
	 * kortene utenfor, anbefales metoden getAlleKort().
	 * 
	 * @return tabell av kort.
	 */
	public Kort[] getSamling() {
		return samling;
	}
	
	/**
	 * Antall kort i samlingen.
	 * 
	 * @return antall kort i samlinga.
	 */
	public int getAntalKort() {
		return antall;
	}
	
	/**
	 * Sjekker om samlinga er tom.
	 * 
	 * @return true om samlinga er tom, false ellers.
	 */
	public boolean erTom() {
		return antall == 0;
	}

	/**
	 * Legg et kort til samlinga.
	 * 
	 * @param kort
	 *            er kortet som skal leggast til.
	 */
	public void leggTil(Kort kort) {
		samling[antall] = kort;
		antall++;
	}
	
	/**
	 * Legger alle korta (hele kortstokken) til samlinga. Korta vil være sortert
	 * slik at de normalt må stokkes før bruk.
	 */
	public void leggTilAlle() {
		for (var farge : Kortfarge.values()) {
			for (int i = 1; i <= Regler.MAKS_KORT_FARGE; i++) {
				leggTil(new Kort(farge, i));
			}
		}
	}

	/**
	 * Fjerner alle korta fra samlinga slik at den blir tom.
	 */
	public void fjernAlle() {
		
		for (int i = 0; i < antall; i++) {
			samling[i] = null;
		}
		
		antall = 0;
	}
	
	/**
	 * Ser på siste kortet i samlinga.
	 * 
	 * @return siste kortet i samlinga, men det blir ikke fjernet. Dersom samalinga er tom, returneres
	 *         null.
	 */
	public Kort seSiste() {
		if (antall == 0)
			return null;
		
		return samling[antall - 1];
	}

	/**
	 * Tek ut siste kort fra samlinga.
	 * 
	 * @return siste kortet i samlinga. Dersom samalinga er tom, returneres
	 *         null.
	 */
	public Kort taSiste() {
		var kort = seSiste();
		
		// Skjer om antall == 0
		if (kort == null)
			return null;
		
		fjern(kort);
		
		return kort;
	}
	
	/**
	 * Undersøker om et kort finst i samlinga.
	 * 
	 * @param kort.
	 * 
	 * @return true om kortet finst i samlinga, false ellers.
	 */
	public boolean har(Kort kort) {
		if (kort == null)
			return false;
		
		for (var k : samling) {
			if (k != null && k.equals(kort)) {
				return true;
			}
		}
		return false;
	}

	private Kort[] fjernFraTabell(Kort[] arr, int index)
    {
        if (arr == null || index < 0 || index >= arr.length)
            return arr;
  
        var nyTabell = new Kort[arr.length - 1];
        
        for (int i = 0, k = 0; i < arr.length; i++) {
  
            // if the index is
            // the removal element index
            if (i == index) {
                continue;
            }
  
            // if the index is not
            // the removal element index
            nyTabell[k++] = arr[i];
        }
  
        // return the resultant array
        return nyTabell;
    }
	
	/**
	 * Fjernar et kort frå samlinga. Dersom kortet ikke finnest i samlinga,
	 * skjer ingenting med samilingen
	 * 
	 * @param kort
	 *            kortet som skal fjernast. Dersom kortet ikke finnes, skjer
	 *            ingenting.
	 * @return true om kortet blev fjernet fra samlinga, false ellers.
	 */
			 
	public boolean fjern(Kort kort) {
		if (erTom())
			return false;
		
		if (!har(kort))
			return false;
		
		for (int i = 0; i < antall; ++i) {
			if (samling[i] == kort) {
				samling = fjernFraTabell(samling, i);
				
				antall--;
				
				return true;
			}
		}
		
		return false; // Umulig å nå dette!
	}

	/**
	 * Gir kortene som en tabell av samme lengde som antall kort i samlingen
	 * 
	 * @return tabell av kort som er i samlingen, der kort skal ha samme rekkefølge
	 *         som i kortsamlinga.
	 */
	public Kort[] getAllekort() {
		var kort = new Kort[antall];
		
		for (int i = 0; i < antall; i++) {
			kort[i] = samling[i];
		}
		
		return kort;
	}
	
}