package serie6Genetik;



public class Anwendung {

	/**
	 * Diese Serie hat viel mehr Spaß gemacht und das Thema war ca. 12498178522934523 mal geiler!
	 * <3
	 */
	public static void main(String[] args) {
		
		
		String[] sequenzen = Gendaten.getGendaten();
		Gensequenz peter = new Gensequenz(sequenzen);
		
	
		// Proteinsequenzen
		StringBuilder temp = peter.rnaSequenz();
		System.out.println(peter.proteinSequenz(temp));
		
		// Suche nach GAGAAG
		System.out.println(peter.knuthSuche("GAGAAG"));
		System.out.println(peter.suche1("GAGAAG"));
	
	}

}
