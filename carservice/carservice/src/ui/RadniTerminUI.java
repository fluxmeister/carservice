package ui;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import dao.RadniTerminDAO;
import model.RadniTermin;
import utils.PomocnaKlasa;

public class RadniTerminUI {
	
	public static DateFormat DATE_FORMAT = new SimpleDateFormat("ddMMyyyy");
	
	/***  ATRIBUTI KLASE  ***/
//	public static ArrayList<RadniTermin> sviRadniTermini = new ArrayList<RadniTermin>();
//	protected static ArrayList<RadniNalog> klijentZadajeRadneNaloge = new ArrayList<RadniNalog>();
	
	/****  MENI OPCIJA  
	 * @throws ParseException ****/
	public static void meniRadniTerminUI() {

		int odluka = -1;
		while (odluka != 0) {
			ispisiMeni();
			System.out.println("Vaša opcija: ");
			odluka = PomocnaKlasa.ocitajCeoBroj();
			switch (odluka) {
			case 0:
				System.out.println(" Izlaz iz menija za radne termine...");
				System.out.println("***************************************");
				break;
			case 1:
				unosNovogRadnogTermina();
				break;
			case 2:
				izmenaPodatakaORadnomTerminu();
				break;
			case 3:
				brisanjePodatakaORadnomTerminu();
				break;
			case 4:
				ispisiSveRadneTermine();
				break;
//			case 5:
//				ispisiRadneNalogeUTerminu();
//				break;
//			case 6:
//				RadniNalogUI.dodajRadniNalog();
//				break;
			case 7:
				sortirajRadneTerminePoDatumu();
				break;
			default:
				System.out.println(" Nepostojeća komanda. ");
				break;
			}
		}

	}
	
	private static void ispisiMeni() {
		System.out.println("******************************************************************************");
		System.out.println("Rad sa radnim terminima - opcije:");
		System.out.println("\tOpcija broj 1 - unos podataka o novom radnom terminu");
		System.out.println("\tOpcija broj 2 - izmena podataka o radnom terminu");
		System.out.println("\tOpcija broj 3 - brisanje podataka o radnom terminu");
		System.out.println("\tOpcija broj 4 - ispis podataka svih radnih termina");
//		System.out.println("\tOpcija broj 5 - ispis podataka o odre\u0111enom Radnom terminu sa njegovim Radnim nalozima");
		System.out.println("\tOpcija broj 6 - dodavanje novog radnog naloga");
//		System.out.println("\tOpcija broj 7 - sortiranje radnih termina po datumu");
		System.out.println("\t\t ...");
		System.out.println("\tOpcija broj 0 - IZLAZ u prethodni meni");	
		System.out.println("******************************************************************************");
	}
	
	
	
	
//	public static void ispisiRadneNalogeUTerminu() {
//		RadniTermin rt = pronadjiRadniTermin();
//		if (rt != null) {
//			List<RadniNalog> radniNalozi = rt.getRadniNalozi();
//
//			System.out.println(rt.toStringAllRadniNalog());
			
//			for (RadniNalog rn : radniNalozi) {
//				System.out.println(rn);
//
//			}
//		}
//
//	}




	
	/** METODE ZA ISPIS RADNIH TERMINA **/
	
	private static void ispisiSveRadneTermine() {
		System.out.println("******************************************************************************");
		System.out.println("********* Svi radni termini ***********");
		List<RadniTermin> sviRadniTermini = RadniTerminDAO.getAll(ApplicationUI.getConn());
		
		System.out.println();
		System.out.printf("%-3s %-15s %-15s %-15s", "id", "naziv", "početak", "kraj"); System.out.println();
		System.out.println("== ============== ============== ==============");
		for( RadniTermin rt : sviRadniTermini ) {
				System.out.printf("%-3s %-15s %-15s %-15s", 
						rt.getId(),
						rt.getNaziv(), 
						rt.getPocetak(), 
						rt.getKraj()); System.out.println();
				System.out.println("-- --------------- --------------- ---------------");
		}
			System.out.println("******************************************************************************");
			
	}
	
	
	/** METODE ZA PRETRAGU RADNIH TERMINA **/
	
	// pronadji radni termin
	public static RadniTermin pronadjiRadniTermin() {
		System.out.println("******************************************************************************");
		RadniTermin retVal = null;
		System.out.println("Unesi id radnog termina : ");
		int id = PomocnaKlasa.ocitajCeoBroj();
		retVal = RadniTerminDAO.getRadniTerminByID(ApplicationUI.getConn(), id);
		if( retVal == null )
			System.out.println("*** Radni termin id: " + id + " ne postoji u evidenciji. ***");
		return retVal;
	}
	
	
	
	/** METODE ZA UNOS, IZMENU I BRISANJE RADNOG TERMINA 
	 * @throws ParseException **/


	private static void unosNovogRadnogTermina() {
		System.out.println("******************************************************************************");
		System.out.print("Unesi naziv radnog termina: ");
		String rtNaziv = PomocnaKlasa.ocitajTekst();
		System.out.print("Unesi novi početak radnog termina (ddMMyyyy): ");
		String pocetak = PomocnaKlasa.ocitajTekst();
		System.out.println("Unesi novi kraj radnog termina (ddMMyyyy): ");
		String kraj = PomocnaKlasa.ocitajTekst();
		
		RadniTermin rTermin = new RadniTermin(0, rtNaziv, pocetak, kraj);
		RadniTerminDAO.add(ApplicationUI.getConn(), rTermin);
		
	}	
	
	
	private static void izmenaPodatakaORadnomTerminu() {
		
		RadniTermin rTermin = pronadjiRadniTermin();
		if (rTermin != null) {

			System.out.println("******************************************************************************");
			System.out.println("************************** Izmena radnog termina *****************************");

			System.out.println("Unesi novi naziv radnog termina: ");
			String rtNaziv = PomocnaKlasa.ocitajTekst();
			rTermin.setNaziv(rtNaziv);

			System.out.print("Unesi novi početak radnog termina (yyyyMMdd): ");
			String pocetak = PomocnaKlasa.ocitajTekst();
			rTermin.setPocetak(pocetak);

			System.out.println("Unesi novi kraj radnog termina (yyyyMMdd): ");
			String kraj = PomocnaKlasa.ocitajTekst();
			rTermin.setKraj(kraj);

			RadniTerminDAO.update(ApplicationUI.getConn(), rTermin);
		} else
			System.out.println("Nema takvog radnog termina! ");
	}

	


	

	
	private static void sortirajRadneTerminePoDatumu() {
		System.out.println("******************************************************************************");
		System.out.println("******************************************************************************");
		
	}


	



	
	
	
	
	
	private static void brisanjePodatakaORadnomTerminu() {
		RadniTermin rTermin = pronadjiRadniTermin();
		if(rTermin != null) {
			RadniTerminDAO.delete(ApplicationUI.getConn(), rTermin.getId());
		}
	}




	
	/** METODA ZA UCITAVANJE PODATAKA **/

//	public static void citajIzFajlaRadneTermine(File radniTerminFajl) throws IOException {
//		
//		if( radniTerminFajl.exists() ) {
//			BufferedReader in = new BufferedReader( new FileReader(radniTerminFajl) );
//			
//			//workaround for UTF-8 files and BOM marker
//			//BOM (byte order mark) marker may appear on the beginning of the file
//			//BOM can signal which of several Unicode encodings (8-bit, 16-bit, 32-bit) that text is encoded as
//			in.mark(1); //zapamti trenutnu poziciju u fajlu da mozes kasnije da se vratis na nju
//			if( in.read() != '\ufeff' ) {
//				in.reset();
//			}
//			String s2;
//			while(( s2 = in.readLine()) != null ) {
//				sviRadniTermini.add(new RadniTermin(s2));
//			}
//			in.close();
//		} else {
//			System.out.println("Ne postoji fajl za čitanje radnih termina!");
//		}
//		
//	}




	
	
	
//	public static void pisiUFajlRadneTermine(File radniTerminFajl) throws IOException {
//		PrintWriter out2 = new PrintWriter( new FileWriter( radniTerminFajl ) );
//		for( RadniTermin rt : sviRadniTermini ) {
//			out2.println(rt.toFileRepresentation());
//		}
//		
//		out2.flush();
//		out2.close();
//		
//	}

//	public static ArrayList<RadniTermin> getSviRadniTermini() {
//		return sviRadniTermini;
//	}
//
//	public static void setSviRadniTermini(ArrayList<RadniTermin> sviRadniTermini) {
//		RadniTerminUI.sviRadniTermini = sviRadniTermini;
//	}
//
//	public static ArrayList<RadniNalog> getRadniNalozi() {
//		return getRadniNalozi();
//	}
//
//
//	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
