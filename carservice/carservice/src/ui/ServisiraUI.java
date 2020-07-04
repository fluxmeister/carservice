package ui;

import utils.PomocnaKlasa;

public class ServisiraUI {

	private static void ispisiMeni() {
		System.out.println("*********************************************************************************************************");
		System.out.println("*************AUTO SERVIS*********************************************************************************");
		System.out.println("Rad sa serviserima - opcije: ");
		System.out.println("\tOpcija 1 - Ispis vozila koja tehničar servisira");
		System.out.println("\tOpcija 2 - Ispis svih tehničara servisa");
		System.out.println("\tOpcija 3 - Dodavanje vozila tehničaru servisa");
		System.out.println("\tOpcija 4 - Uklanjanje zaduženja servisa nad vozilom");
		System.out.println("\t\t");
		System.out.println("\tOpcija 0 - IZLAZ u prethodni meni");
		System.out.println("*********************************************************************************************************");
		
	}
	
	
	public static void meniServisiraUI() {
		
		int odluka = -1;
		while( odluka != 0 ) {
			
			ispisiMeni();
			System.out.println("opcija:");
			odluka = PomocnaKlasa.ocitajCeoBroj();
			switch( odluka ) {
			case 0:
				System.out.println("Izlaz iz menija za posedovanja");
				break;
			case 1:
				ispisiVozilaZaServisera();
				break;
			case 2:
				ispisiTehnicareServisa();
				break;
			case 3:
				dodajVoziloNaTehnicara();
				break;
			case 4:
				ukloniZaduzenjaNadVozilom();
				break;
			default:
					System.out.println("Nepostojeća komanda.");
					break;
			}
			
		}
		
	}


	private static void ukloniZaduzenjaNadVozilom() {
		// TODO Auto-generated method stub
		System.out.println("napisati metodu za uklanjanje zaduzenja.");
	}


	private static void dodajVoziloNaTehnicara() {
		// TODO Auto-generated method stub
		System.out.println("napisati metodu za dodavanje vozila na zaduzenje tehnicaru.");
	}


	private static void ispisiTehnicareServisa() {
		// TODO Auto-generated method stub
		System.out.println("napisati metodu za ispis tehnicara servisa.");
	}


	private static void ispisiVozilaZaServisera() {
		// TODO Auto-generated method stub
		System.out.println("napisati metodu za ispis vozila na zaduzenju kod servisera.");
	}
	
}
