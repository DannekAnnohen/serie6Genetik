package serie6;



public class Anwendung {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		// +Speichereinheit
		String[] sequenzen = Gendaten.getGendaten();
		Gensequenz peter = new Gensequenz(sequenzen);
		String alpha = "ATTATAATTTGTATACCACCTTATAGATACCCATGTTCAGGAAAAGTTGA";
		System.out.println(alpha);
		System.out.println(peter.suche1(alpha));
		System.out.println(peter.getGensequenz().length());
		
		System.out.println(peter.knuthSuche(alpha));
		

		
	
	}

}
