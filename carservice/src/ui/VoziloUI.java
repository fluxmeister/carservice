package ui;

import java.util.List;

import dao.KlijentDAO;
import dao.VoziloDAO;
import model.Klijent;
import model.Vozilo;
import utils.PomocnaKlasa;

public class VoziloUI {


	
	public static void meniVoziloUI() {
		
		int odluka = -1;
		while( odluka != 0 ) {
			
			ispisiMeni();
			System.out.println("opcija: ");
			odluka = PomocnaKlasa.ocitajCeoBroj();
			switch( odluka ) {
			
			case 0:
				System.out.println("Izlaz iz menija za vozila");
				break;
				
			case 1:
				unosNovogVozila();
				break;
			case 2:
				izmenaPodatakaOVozilu();
				break;
			case 3:
				brisanjeVozila();
				break;
			case 4:
				ispisiSvaVozila();
				break;
//			case 5:
//				RadniNalogUI.ispisiRadneNalogeZaVozilo();
//				break;
//			case 6:
//				RadniNalogUI.dodajRadniNalog();
//				break;
			}
			
		}
		
	}
	
	private static void ispisiMeni() {
		System.out.println("******************************************************************************");
		System.out.println("******************************AUTO SERVIS*************************************");
		System.out.println("****  Rad sa vozilima - opcije:  ******");
		System.out.println("\tOpcija 1 - unos podataka o novom vozilu");
		System.out.println("\tOpcija 2 - izmena podataka o vozilu");
		System.out.println("\tOpcija 3 - brisanje podataka o vozilu");
		System.out.println("\tOpcija 4 - ispis podataka svih vozila");
//		System.out.println("\tOpcija 5 - ispis podataka o određenom vozilu");
//		System.out.println("\tOpcija 6 - ispis radnih naloga za određeno vozilo");
//		System.out.println("\tOpcija 7 - unos radnog naloga za određeno vozilo");
//		System.out.println("\tOpcija 8 - sortiranje vozila po godištu");
		System.out.println("\t\t ...");
		System.out.println("\tOpcija 0 - IZLAZ u prethodni meni");
		System.out.println("******************************************************************************");
	}
	
	
	/** METODE ZA ISPIS VOZILA **/
	
	private static void ispisiSvaVozila() {
		List<Vozilo> svaVozila = VoziloDAO.getAll(ApplicationUI.getConn());

		System.out.println();
		System.out.printf("%-3s %-10s %-10s %-30s %-10s %-10s %-10s", "id", "registracija", "tip", "marka-model",
				"gorivo", "godiste", "mot-typ");
		System.out.println();
		System.out.println("=== ========== ========== ============================== ========== ========== ==========");
		for (Vozilo itVozilo : svaVozila) {
			System.out.printf("%-3s %-10s %-10s %-30s %-10s %-10s %-10s", itVozilo.getId(), itVozilo.getRegistracija(),
					itVozilo.getTip(), itVozilo.getMarkaModel(), itVozilo.getGorivo(), itVozilo.getGodiste(),
					itVozilo.getMotTyp());
			System.out.println();
			
//				Klijent vlasnik = null;
//				System.out.printf("%-3s %-13s %-18s %-18s %-11s", vlasnik.getId(), vlasnik.getIme(),
//						vlasnik.getPrezime(), vlasnik.getPrebivaliste(), vlasnik.getTelefon());
//				System.out.println();
			
			System.out.println(
					"--- ---------- ---------- ------------------------------ ---------- ---------- ----------");
		}
	}
	
	/** METODE ZA PRETRAGU VOZILA **/
	
	public static Vozilo pronadjiVozilo() {
		Vozilo retVal = null;
		System.out.print("Unesi id vozila:");
		int id = PomocnaKlasa.ocitajCeoBroj();
		retVal = pronadjiVozilo(id);
		if(retVal == null) {
			System.out.println("Vozilo sa id-om " + id + " ne postoji u evidenciji!");
		}
		return retVal;
	}
	
	public static Vozilo pronadjiVozilo(int id) {
		Vozilo retVal = VoziloDAO.getVoziloByID(ApplicationUI.getConn(), id);
		return retVal;
	}
	
	private static void unosNovogVozila() {
		System.out.println("******************************************************************************");
		System.out.println("**********Unos novog vozila************");
		System.out.println("Unesi registraciju vozila: ");
		String registracija = PomocnaKlasa.ocitajTekst().toUpperCase();
		System.out.println("Unesi tip vozila: ");
		String tip = PomocnaKlasa.ocitajTekst();
		System.out.println("Unesi marku-model vozila: ");
		String markaModel = PomocnaKlasa.ocitajTekst();
		System.out.println("Unesi vrstu goriva: ");
		String gorivo = PomocnaKlasa.ocitajTekst();
		System.out.println("Unesi godište: ");
		String godiste = PomocnaKlasa.ocitajTekst();
		System.out.println("Unesi tip motora: ");
		String motTyp = PomocnaKlasa.ocitajTekst();

		Vozilo vzl = new Vozilo(0, registracija, tip, markaModel, gorivo, godiste, motTyp);
		VoziloDAO.add(ApplicationUI.getConn(), vzl);
	}

	private static void izmenaPodatakaOVozilu() {
		Vozilo vzl = pronadjiVozilo();
		if (vzl != null) {

			System.out.println("******************************************************************************");
			System.out.println("**************************  Izmena vozila  ***********************************");
			if (vzl != null) {
				System.out.println("Unesi novu registraciju vozila: ");
				String registracija = PomocnaKlasa.ocitajTekst();
				vzl.setRegistracija(registracija);
				System.out.println("Unesi novi tip vozila: ");
				String tip = PomocnaKlasa.ocitajTekst();
				vzl.setTip(tip);
				System.out.println("Unesi novu marku-model vozila: ");
				String markaModel = PomocnaKlasa.ocitajTekst();
				vzl.setMarkaModel(markaModel);
				System.out.println("Unesi novu vrstu goriva: ");
				String gorivo = PomocnaKlasa.ocitajTekst();
				vzl.setGorivo(gorivo);
				System.out.println("Unesi novo godište: ");
				String godiste = PomocnaKlasa.ocitajTekst();
				vzl.setGodiste(godiste);
				System.out.println("Unesi novi tip motora: ");
				String motTyp = PomocnaKlasa.ocitajTekst();
				vzl.setMotTyp(motTyp);
				VoziloDAO.update(ApplicationUI.getConn(), vzl);
			}

		}
	}


private static void brisanjeVozila() {
	System.out.println("******************************************************************************");
	System.out.println("*********Brisanje  vozila***********");
	Vozilo vzl = pronadjiVozilo();
	if( vzl != null ) {
		VoziloDAO.delete(ApplicationUI.getConn(), vzl.getId());
		System.out.println("Vozilo id: " + vzl.getId() + " obrisano iz evidencije.");
		System.out.println("******************************************************************************");
	}
}



	
	
}
