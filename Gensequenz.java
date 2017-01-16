package serie6Genetik;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Gensequenz {

	private StringBuilder gensequenz = new StringBuilder();

	public List<String> proteinSequenz(StringBuilder arg) {

		// Input arg ist die RNA-Sequenz
		
		// Ergebnisdatentyp deklarieren und hier auch gleich initialisieren
		List<String> result = new ArrayList<String>();
		
		// Import der HashMap, in der alle Sequenzen Proteinen zugeordnet sind
		HashMap<String, String> schluesselbund = Gendaten.getProteine();
		
		// Hier suche ich erstmal alle Startsequenzen AUG, hier jedoch ATG, weil knuthSuche2 (macht dasselbe wie knuthsuche, gibt jedoch nur die Positionsliste zurück) auf DNA, nicht RNA laeuft
		List<Integer> augPositionen = knuthSuche2("ATG");
		
		// der runner sagt später, wo wir gerade in der RNA (arg) sind
		int runner = 0;

		for (int i = 0; i < augPositionen.size(); ++i) {
			
			
			if (runner > i) {
				
				// Wenn AUG innerhalb einer Proteinsequenz auftaucht, bevor diese zuende ist, soll man natürlich erst am Ende der Sequenz weitermachen und nicht "zurückspringen"
				// continue springt hier wieder zum "for" und geht da einen Schritt in den AUGpositionen weiter
				continue;
			}
			
			String endpoint = arg.substring(augPositionen.get(i) + 3, augPositionen.get(i) + 5);
			// Die Proteinsequenz endet vor dem Triplett, das direkt auf das initale AUG folgt
					

			for (int j = augPositionen.get(i) + 6; j < arg.length(); j = j + 3) {
				//diese for-Schleife schaut sich alle Tripletts an, die auf AUG-Endtriplett folgen
				
				//einlesen neues Triplett
				String temp = arg.substring(j, j + 3);
				
				//runner auf aktuelle Position setzen
				runner = j;
				
				// überprüfen ob aktuelles Triplett = Endpunkt ist
				if (temp.equals(endpoint)) {
					// wenn ja - auf zur naechsten AUG-Position ohne hinzuzufügen
					break;
					
				} else {
					// wenn nein - 	hinzuguegen zur ergebnisliste					
					result.add(schluesselbund.get(temp));
				}
			}

		}

		//Rueckgabe nicht vergessen!

		return result;
	}

	public StringBuilder rnaSequenz() {
		
		// kopiere die gensequenz in einen neuen Stringbuilder
		StringBuilder result = new StringBuilder(gensequenz.toString());
		
		// suche alle Ts
		List<Integer> places = knuthSuche2("T");
		
		for (int i = 0; i < places.size(); ++i) {
			//entlang der liste aus knuth setten wir alle chars at den placen auf U
			result.setCharAt(places.get(i), 'U');
		}
		
		// Rueckgabe nicht vergessen!
		return result;
	}

	public HashMap<Integer, List<Integer>> knuthSuche(String motiv) {

		HashMap<Integer, List<Integer>> result = new HashMap<Integer, List<Integer>>();

		int i = 0;
		int j = -1;
		int[] resultA = new int[motiv.length() + 1];
		List<Integer> positionen = new ArrayList<Integer>();

		resultA[0] = j;

		while (i < motiv.length()) {

			while (j >= 0 && motiv.charAt(j) != motiv.charAt(i)) {

				j = resultA[j];

			}

			i = i + 1;
			j = j + 1;
			resultA[i] = j;

		}

		// erste Phase vorbei

		i = 0;
		j = 0;

		while (i < gensequenz.length()) {

			while (j >= 0 && gensequenz.charAt(i) != motiv.charAt(j)) {

				j = resultA[j];
			}

			i = i + 1;
			j = j + 1;

			if (j == motiv.length()) {
				positionen.add(i - motiv.length());

				j = resultA[j];

			}

		}

		Integer anzahl = new Integer(positionen.size());
		result.put(anzahl, positionen);
		return result;
	}

	public HashMap<Integer, ArrayList<Integer>> suche1(String motiv) {

		ArrayList<Integer> positionen = new ArrayList<Integer>();

		int a = 0;

		while (a != -1) {
			a = gensequenz.indexOf(motiv, a);
			if (a == -1) {
				break;
			}
			positionen.add(a);
			++a;
		}

		Integer anzahl = new Integer(positionen.size());

		HashMap<Integer, ArrayList<Integer>> result = new HashMap<Integer, ArrayList<Integer>>();
		result.put(anzahl, positionen);

		return result;
	}

	public Gensequenz(String[] args) {

		for (int i = 0; i < args.length; ++i) {

			String temp = args[i].toUpperCase();
			this.gensequenz.append(temp.toCharArray());
		}

	}

	public StringBuilder getGensequenz() {
		return this.gensequenz;
	}

	public String toString() {
		return gensequenz.toString();
	}

	// Bonus

	public List<Integer> knuthSuche2(String motiv) {

		int i = 0;
		int j = -1;
		int[] resultA = new int[motiv.length() + 1];
		List<Integer> positionen = new ArrayList<Integer>();

		resultA[0] = j;

		while (i < motiv.length()) {

			while (j >= 0 && motiv.charAt(j) != motiv.charAt(i)) {

				j = resultA[j];

			}

			i = i + 1;
			j = j + 1;
			resultA[i] = j;

		}

		// erste Phase vorbei

		i = 0;
		j = 0;

		while (i < gensequenz.length()) {

			while (j >= 0 && gensequenz.charAt(i) != motiv.charAt(j)) {

				j = resultA[j];
			}

			i = i + 1;
			j = j + 1;

			if (j == motiv.length()) {
				positionen.add(i - motiv.length());

				j = resultA[j];

			}

		}

		return positionen;
	}
}
