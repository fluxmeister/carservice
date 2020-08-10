package ui;

import java.util.List;

import dao.PosedujeDAO;
import model.Klijent;
import model.Vozilo;
import utils.PomocnaKlasa;

public class PosedujeUI {
	
	private static void ispisiMeni() {
		System.out.println("*********************************************************************************************************");
		System.out.println("***********************************AUTO SERVIS**********************************************************");
		System.out.println("Rad sa posedovanjima klijenta - opcije: ");
		System.out.println("\tOpcija 1 - Ispis vozila koja klijent poseduje");
		System.out.println("\tOpcija 2 - Ispis vlasnika za traženo vozilo");
		System.out.println("\tOpcija 3 - Dodavanje vozila vlasniku");
		System.out.println("\tOpcija 4 - Uklanjanje vlasništva nad vozilom");
		System.out.println("\t\t ...");
		System.out.println("\tOpcija 0 - IZLAZ u prethodni meni");
		System.out.println("*********************************************************************************************************");
		
	}
	
	
	public static void meniPosedujeUI() {
		
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
				ispisiVozilaZaKlijenta();
				break;
			case 2:
				ispisiKlijentaZaVozilo();
				break;
			case 3:
				dodajVoziloNaKlijenta();
				break;
			case 4:
				ukloniVlasništvoNadVozilom();
				break;
			default:
					System.out.println("Nepostojeća komanda.");
					break;
			}
			
		}
		
	}

	/**
	 * Dodavanje vozila vlasniku
	 * 
	 * prvo pronađemo klijenta na koga želimo da dodamo vozilo zatim pronađemo
	 * vozilo koje želimo da dodamo na klijenta
	 **/
	public static void dodajVoziloNaKlijenta() {
		System.out.println("*****Dodavanje vozila klijentu*********");
		// prvo pronađemo vozilo koje želimo dodati na klijenta
		Vozilo vozilo = VoziloUI.pronadjiVozilo();
		// pronađemo klijenta na koga želimo dodati vozilo
		Klijent klijent = KlijentUI.pronadjiKlijenta();

		if (vozilo != null && klijent != null) {
			// uspostavimo vezu između vozila i klijenta
			PosedujeDAO.add(ApplicationUI.getConn(), klijent.getId(), vozilo.getId());
			System.out.println(" Vozilo uspešno dodato i promena evidentirana u podacima klijenta.");
		}
	}
	

	private static void ispisiVozilaZaKlijenta() {
		System.out.println("** Traženje vozila za vlasnika ********");
		// prvo pronađemo klijenta za koga želimo ispis vozila
		Klijent klijent = KlijentUI.pronadjiKlijenta();
		if (klijent != null) {

			List<Vozilo> vozila = PosedujeDAO.getVozilaByKlijentID(ApplicationUI.getConn(), klijent.getId());
			System.out.printf("%-5s %-20s", "id", "registracija");
			System.out.println();
			System.out.println("===== ====================");

			for (Vozilo v : vozila) {
				System.out.printf("%-5s %-20s", v.getId(), v.getRegistracija());
				System.out.println();
			}
			System.out.println("*******kraj liste posedovanja**********");
			System.out.println("***************************************");
		}
		else {
			System.out.println("**Ne postoje podaci o klijentu/vozilu**");
			System.out.println("***************************************");
		}
	}
	
	
	
	private static String ispisiKlijentaZaVozilo() {
		System.out.println("** Traženje vlasnika za vozilo ********");
		// pronađemo vozilo za koga želimo ispis klijenta
		Vozilo vozilo = VoziloUI.pronadjiVozilo();
		if (vozilo != null) {

			Klijent vlasnik = PosedujeDAO.getVlasnikByVoziloID(ApplicationUI.getConn(), vozilo.getId());
//			int id, String ime, String prezime, String prebivaliste, String telefon;
			System.out.println();
			System.out.printf(" %-3s %-20s %-20s %-20s %-12s", "id", "ime", "prezime", "prebivalište", "telefon"); System.out.println();
			System.out.println("=== ==================== ==================== ==================== ============");
			System.out.printf("%-3s %-20s %-20s %-20s %-12s",
					vlasnik.getId(),
					vlasnik.getIme(), 
					vlasnik.getPrezime(), 
					vlasnik.getPrebivaliste(),
					vlasnik.getTelefon());
			System.out.println();
		}
		String poruka = "Vozilo nema vlasnika! Brzo to ispravi!";
		return poruka;

	}
	

	
	private static void ukloniVlasništvoNadVozilom() {
		// prvo pronađemo klijenta koga uklanjamo sa vozila
		Klijent vlasnik = KlijentUI.pronadjiKlijenta();
		// zatim pronađemo vozilo s akoga želimo ukloniti vlasnika
		Vozilo vozilo = VoziloUI.pronadjiVozilo();
		
		if(vlasnik != null && vozilo != null) {
			// izbrišemo vezu između vlasnika i vozila
			PosedujeDAO.delete(ApplicationUI.getConn(), vlasnik.getId(), vozilo.getId());
			System.out.println("Vozilo uspešno uklonjeno i promena evidentirana u podacima klijenta.");
		}
		
	}

}




