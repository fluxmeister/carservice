package ui;

import java.util.ArrayList;
import java.util.List;

import dao.KlijentDAO;
import model.Klijent;
import model.Vozilo;
import utils.PomocnaKlasa;

public class KlijentUI {

	/**** ATRIBUTI KLASE ****/
	public static ArrayList<Klijent> sviKlijenti = new ArrayList<Klijent>();

	public static void meniKlijentUI() {

		int odluka = -1;
		while (odluka != 0) {

			ispisiTekstKlijentOpcije();
			System.out.println("Vaš izbor: ");
			odluka = PomocnaKlasa.ocitajCeoBroj();
			switch (odluka) {

			case 0:
				System.out.println("Izlaz iz menija sa klijentima ...");
				break;
			case 1:
				unosNovogKlijenta();
				break;
			case 2:
				izmenaPodatakaOKlijentu();
				break;
			case 3:
				brisanjeKlijenta();
				break;
			case 4:
				ispisiSveKlijente();
				break;
			default:
				System.out.println("Nepostojeća komanda. Pokušajte ponovo.");
				break;
			}

		}

	}

	/** METODE ZA ISPIS OPCIJA ****/
	// ispis teksta osnovnih opcija

	private static void ispisiTekstKlijentOpcije() {
		System.out.println("******************************************************************************");
		System.out.println("*************************** Rad sa klijentima ********************************");
		System.out.println(" Opcija 1: Unos podataka o novom klijentu");
		System.out.println(" Opcija 2: Izmena podataka o određenom klijentu");
		System.out.println(" Opcija 3: Brisanje klijenta iz evidencije");
		System.out.println(" Opcija 4: Ispis podataka svih klijenata");
//		System.out.println(" Opcija 5: ispis podataka o određenom klijentu sa njegovim vozilima na servisu");
//		System.out.println(" Opcija 6: ispis podataka o određenom klijentu sa njegovim radnim nalozima");
//		System.out.println(" Opcija 7: sortiranje klijenata po imenu");
		System.out.println("\t\t ...");
		System.out.println(" Opcija 0: IZLAZ u prethodni meni");
		System.out.println("******************************************************************************");
	}

	private static void ispisiSveKlijente() {
		System.out.println(
				"**************************************** Svi klijenti Auto-servisa ******************************************");

		List<Klijent> sviKlijenti = KlijentDAO.getAll(ApplicationUI.getConn());

		System.out.println();
		System.out.printf("%-3s %-13s %-18s %-18s %-11s", " id", "    ime", "    prezime", "      prebivalište",
				"              telefon"/*, "              vozila"*/);
		System.out.println(); 
		System.out.println(
				"=== ============= ==================== ======================= ==================== ========================");
		for (Klijent itKlijent : sviKlijenti) {
			System.out.printf("%-3s %-13s %-20s %-23s %-12s", itKlijent.getId(), itKlijent.getIme(), itKlijent.getPrezime(),
					itKlijent.getPrebivaliste(), itKlijent.getTelefon());
			System.out.println();
			for (Vozilo itVozilo : itKlijent.getKlijentImaVozila()) {
				System.out.printf("%-3s %-13s %-18s %-18s %-18s %-20s", "", "", "", "", "", itVozilo.getRegistracija());
				System.out.println();
			}
			System.out.println(
					"*************************************************************************************************************");
		}

	}

//	private static void ispisiKlijentaSaNjegovimVozilimaNaServisu() {
//		// prvo nadjemo klijenta koji nam treba
//		// onda napravimo i popunimo list vozila koje nadjeni klijent poseduje
//		// onda pretrazimo po toj listi i nadjemo da li ima takvo vozilo sa
//		// registracijom koja nam treba
//
//	}

	/** METODE ZA PRETRAGU KLIJENATA 
	 * @param klijent_id2 ****/
	public static Klijent pronadjiKlijenta() {
		Klijent retVal = null;
		System.out.println("Unesi ID klijenta: ");
		int klijent_id = PomocnaKlasa.ocitajCeoBroj();
		retVal = pronadjiKlijenta(klijent_id);
		if (retVal == null)
			System.out.println("Klijent sa ID " + klijent_id + " ne postoji u evidenciji.");
		return retVal;
	}


	// pronađi klijenta po ID
	public static Klijent pronadjiKlijenta(int klijent_id) {
		Klijent retVal = KlijentDAO.getKlijentByID(ApplicationUI.getConn(), klijent_id);
		return retVal;
	}
	
	// pronađi klijenta po telefonu
	public static Klijent pronadjiKlijenta(String klTelefon) {
		Klijent retVal = KlijentDAO.getKlijentByTelefon(ApplicationUI.getConn(), klTelefon);
		return retVal;
	}

	// unos novog klijenta
	private static void unosNovogKlijenta() {
		System.out.println("******************************************************************************");
		System.out.println("***************************Unos novog klijenta*******************************");
		System.out.print("Unesi ime klijenta:");
		String klIme = PomocnaKlasa.ocitajTekst();
		System.out.print("Unesi prezime klijenta:");
		String klPrezime = PomocnaKlasa.ocitajTekst();
		System.out.print("Unesi prebivalište klijenta:");
		String klPrebivaliste = PomocnaKlasa.ocitajTekst();
		System.out.print("Unesi broj telefona klijenta: ");
		String klTelefon = PomocnaKlasa.ocitajTekst();
		klTelefon = klTelefon.toUpperCase();
		while (pronadjiKlijenta(klTelefon) != null) {
			System.out.println("Klijent sa telefonom " + klTelefon + " već postoji!");
			klTelefon = PomocnaKlasa.ocitajTekst();
		}
		
		
		
		// ID atribut ce se dodeliti automatski
		Klijent kl = new Klijent(0, klIme, klPrezime, klPrebivaliste, klTelefon);
		KlijentDAO.add(ApplicationUI.getConn(), kl);
		System.out.println("************************** klijent je dodat *********************************");

//		while( ( PomocnaKlasa.ocitajOdlukuOPotvrdi("Upisati klijenta da poseduje određeno vozilo? Y/N") == 'Y'  ) 
//				|| ( PomocnaKlasa.ocitajOdlukuOPotvrdi("Upisati klijenta da poseduje određeno vozilo? Y/N") == 'y' ) ){
//			PosedujeUI.dodajKlijentaNaVozilo(kl);
//		}
	}


	// izmena klijenta
	public static void izmenaPodatakaOKlijentu() {
		System.out.println("******************************************************************************");
		System.out.println("**************************Izmena podataka klijenta****************************");
		Klijent kl = pronadjiKlijenta();
		if (kl != null) {
			System.out.println("Unesi ime klijenta: ");
			String klIme = PomocnaKlasa.ocitajTekst();
			System.out.println("Unesi prezime klijenta: ");
			String klPrezime = PomocnaKlasa.ocitajTekst();
			System.out.println("Unesi prebivalište klijenta:");
			String klPrebivaliste = PomocnaKlasa.ocitajTekst();
			System.out.print("Unesi broj telefona klijenta:");
			String klTelefon = PomocnaKlasa.ocitajTekst();
			

			kl.setIme(klIme);
			kl.setPrezime(klPrezime);
			kl.setTelefon(klTelefon);
			kl.setPrebivaliste(klPrebivaliste);
			
			KlijentDAO.update(ApplicationUI.getConn(), kl);

//			while( PomocnaKlasa.ocitajOdlukuOPotvrdi("Ukloniti klijenta tako da ne poseduje određeno vozilo? - Y/N") == 'Y' ) {
//				PosedujeUI.ukloniVlasništvoNadVozilom();
//			}
//			
//			while( PomocnaKlasa.ocitajOdlukuOPotvrdi("Upisati klijenta da poseduje određeno vozilo? - Y/N") == 'Y' ) {
//				PosedujeUI.dodajVlasništvoNadVozilom(kl);
//			}
		}
	}

	// brisanje klijenta
	public static void brisanjeKlijenta() {
		System.out.println("******************************************************************************");
		System.out.println("**************************Brisanje  klijenta*********************************");
		Klijent kl = pronadjiKlijenta();
		if (kl != null) {
			KlijentDAO.delete(ApplicationUI.getConn(), kl.getId());
			System.out.println("**************************** klijent je obrisan ***************************");
		}
	}

	

}
