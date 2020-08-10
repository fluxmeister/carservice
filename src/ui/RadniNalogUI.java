//package ui;
//
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//
//import model.Klijent;
//import model.RadniNalog;
//import model.RadniTermin;
//import model.Vozilo;
//import utils.PomocnaKlasa;
//
//
//public class RadniNalogUI {
//
//	static String sP = System.getProperty("file.separator");
//	
//	static File radniNalogEkselFajl = new File("." + sP + "data" + sP + "nalog.xlsx" );
//	
//	/*** ATRIBUT KLASE ***/
//	public static ArrayList<RadniNalog> sviRadniNalozi = new ArrayList<RadniNalog>();
//	public static ArrayList<RadniTermin> sviRadniTermini = new ArrayList<RadniTermin>();
//
//	public static int brojacID;
//	public static int radniTerminId;
//
//	
//	public static void ispisiMeniRadniNalogUI() {
//		System.out.println(
//				"*********************************************************************************************************");
//		System.out.println("******************AUTO SERVIS******************");
//		System.out.println("****  Rad sa radnim nalozima - opcije:  ******");
//		System.out.println("\tOpcija 1 - Radni nalozi za radni termin");
//		System.out.println("\tOpcija 2 - Radni nalozi za klijenta");
//		System.out.println("\tOpcija 3 - Radni nalozi za vozilo");
//		System.out.println("\tOpcija 4 - Unos novog radnog naloga");
//		System.out.println("\tOpcija 5 - Izmena radnog naloga");
//		System.out.println("\tOpcija 6 - Uklanjanje radnog naloga");
//		System.out.println("\tOpcija 7 - Učitavanje radnog naloga iz eksel fajla");
//		System.out.println("\t\t ...");
//		System.out.println("\tOpcija 0 - IZLAZ u prethodni meni");
//
//	}
//
//	public static void meniRadniNalogUI() throws ParseException, IOException {
//
//		int odluka = -1;
//		while (odluka != 0) {
//			ispisiMeniRadniNalogUI();
//			System.out.print("opcija: ");
//			odluka = PomocnaKlasa.ocitajCeoBroj();
//			switch (odluka) {
//			case 0:
//				System.out.println("Izlazak...");
//				break;
//			case 1:
//				ispisiRadneNalogeZaRadniTermin();
//				break;
//			case 2:
//				ispisiRadneNalogeZaKlijenta();
//				break;
//			case 3:
//				ispisiRadneNalogeZaVozilo();
//				break;
//			case 4:
//				dodajRadniNalog();
//				break;
//			case 6:
//				ukloniRadniNalog(null);
//				break;
//			case 7:
//				citajIzEkselFajlaRadniNalog(radniNalogEkselFajl);
//				break;
//			default:
//				System.out.println("Nepostojeća komanda.");
//				break;
//			}
//		}
//	}
//
//	
//	public static void citajIzFajlaRadneNaloge(File radniNaloziFajl) throws IOException, ParseException{
//		if(radniNaloziFajl.exists()) {
//			BufferedReader in = new BufferedReader(new FileReader(radniNaloziFajl));
//			
//			in.mark(1); //zapamti trenutnu poziciju u fajlu da mozes kasnije da se vratis na nju
//			if(in.read()!='\ufeff'){
//				in.reset();
//			}
//			
//			String s2;
//			while( (s2 = in.readLine()) != null ) {
//				sviRadniNalozi.add(new RadniNalog(s2));
//			}
//			in.close();
//		} else {
//			System.out.println("GREŠKA! - Ne postoji fajl sa radnim nalozima!");
//		}
//	}
//
//	
//	public static void citajIzEkselFajlaRadniNalog(File radniNalogEkselFajl) throws IOException, ParseException {
//		if (radniNalogEkselFajl.exists()) {
//
//			FileInputStream fis = new FileInputStream(radniNalogEkselFajl);
//
//			// Finds the workbook instance for .xlsx file
//			XSSFWorkbook myWorkBook = new XSSFWorkbook(fis);
//
//			// Return first sheet from the XLSX workbook
//			XSSFSheet mySheet = myWorkBook.getSheetAt(0);
//
//			// Get iterator to all the rows in current sheet
//			Iterator<Row> rowIterator = mySheet.iterator();
//
//			// Traversing over each row of XLSX file
//			while (rowIterator.hasNext()) {
//				Row row = rowIterator.next();
//
//				// For each row, iterate through each columns
//				Iterator<Cell> cellIterator = row.cellIterator();
//				while (cellIterator.hasNext()) {
//
//					Cell cell = cellIterator.next();
//
//					switch (cell.getCellType()) {
//					case STRING:
//						System.out.println(cell.getStringCellValue() + "\t");
//						break;
//					case NUMERIC:
//						System.out.println(cell.getNumericCellValue() + "\t");
//						break;
//					case BOOLEAN:
//						System.out.println(cell.getBooleanCellValue() + "\t");
//						break;
//					default:
//
//					}
//				}
//				System.out.println("");
//			}
//
//		} else {
//			System.out.println("GREŠKA! - Ne postoji eksel fajl sa radnim nalogom!");
//		}
//	}
//	
//	public static void ispisiRadneNalogeZaRadniTermin() {
//	
//		RadniTermin radniTermin = RadniTerminUI.pronadjiRadniTermin();
//		if( radniTermin != null ) {
//			List<RadniNalog> radniNaloziURadnomTerminu = radniTermin.getRadniNalozi();
//			
//			for( RadniNalog radniNalog : radniNaloziURadnomTerminu ) {
//				System.out.println(radniNalog);
//			}
//			System.out.println("");
//			System.out.println("*******kraj liste radnih naloga********");
//			System.out.println("***************************************");
//			System.out.println("");
//		}
//		else {
//			System.out.println("");
//			System.out.println("**********Ne postoje podaci ***********");
//			System.out.println("***************************************");
//			System.out.println("");
//		}
//	}
//	
//	
//	
//	
//	
//
//
//	public static void ispisiRadneNalogeZaVozilo() {
//		// prvo pronađemo vozilo za koje želimo ispis radnih naloga
//		Vozilo vzl = VoziloUI.pronadjiVoziloRegistracija();
//		if (vzl != null) {
//
//			List<RadniNalog> radniNalozi = vzl.getRadniNalozi();
//			
//			for (RadniNalog rnal : radniNalozi) {
//				System.out.println(rnal);
//			}
//			System.out.println("");
//			System.out.println("*******kraj liste radnih naloga********");
//			System.out.println("***************************************");
//			System.out.println("");
//		}
//		else {
//			System.out.println("");
//			System.out.println("**********Ne postoje podaci ***********");
//			System.out.println("***************************************");
//			System.out.println("");
//		}
//	}
//	
//	
//	
//	
//	public static void ispisiRadneNalogeZaKlijenta() {
//
//		Klijent kl = KlijentUI.pronadjiKlijentaSaobDozvola();
//		if (kl != null) {
//
//			List<RadniNalog> radniNalozi = kl.getRadniNalozi();
//
//			for (RadniNalog rnal : radniNalozi) {
//				System.out.println(rnal);
//			}
//			System.out.println("");
//			System.out.println("*******kraj liste radnih naloga********");
//			System.out.println("***************************************");
//			System.out.println("");
//		}
//		else {
//			System.out.println("");
//			System.out.println("**********Ne postoje podaci ***********");
//			System.out.println("***************************************");
//			System.out.println("");
//		}
//	}
//
//	
//	public static void ispisiRadneNalogeURadnomTerminu() {
//		
//		
//		RadniTermin radniTermin = RadniTerminUI.pronadjiRadniTermin();
//		if( radniTermin != null ) {
//			
//			List<RadniNalog> radniNalozi = radniTermin.getRadniNalozi();
//			
//			for( RadniNalog rnal : radniNalozi ) {
//				System.out.println(rnal);
//			}
//			System.out.println("");
//			System.out.println("*******kraj liste radnih naloga********");
//			System.out.println("***************************************");
//			System.out.println("");
//		}
//		else {
//			System.out.println("");
//			System.out.println("**********Ne postoje podaci ***********");
//			System.out.println("***************************************");
//			System.out.println("");
//		}
//	}
//		
//		
//		
//		
//		
////		RadniTermin radniTermin = RadniTerminUI.pronadjiRadniTermin();
////		if( radniTermin != null ) {
////			List<RadniNalog> radniNalozi = radniTermin.getRadniNalozi();
////			for(RadniNalog radniNalog : radniNalozi) {
////				System.out.println(radniNalog);
////			}
////			System.out.println("");
////			System.out.println("*******kraj liste radnih naloga********");
////			System.out.println("***************************************");
////			System.out.println("");
////		}
////		else {
////			System.out.println("");
////			System.out.println("**********Ne postoje podaci ***********");
////			System.out.println("***************************************");
////			System.out.println("");
////		}
////	}
//	
//	
//		//	   radniNalogId,  radniTerminId,  datumRadnogNaloga, registracija, saobDozvola, markaModel, godiste, tipMotora,  
//		//     		0		  	   1					2		 	  3				4			5			6		 7					
//		
//		
//		//  	kilometraza, nalogodavac, primedbe, otklonjeno, ugradjeno, proizvodjac, uzetoU, pakovanje, kolicina,  ukupno
//		//			8			 9			10			11		   12		  13		   14		15		  16		17
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	/**
//	protected int radniNalogId;
//	protected int radniTerminId;
//	protected Date datumRadnogNaloga;
//	protected String registracija;
//	protected String;
//	protected String markaModel;
//	protected int godiste;
//	protected String tipMotora;
//	protected int kilometraza;
//	protected String nalogodavac;
//	protected String primedbe;
//	protected String otklonjeno;
//	protected String ugradjeno;
//	protected String proizvodjac;
//	protected String uzetoU;
//	protected String pakovanje;
//	protected int kolicina;
//	protected int ukupno;
//	 * @param datumRadnogNaloga 
//	 */
//	
//	
//	protected static void dodajRadniNalog(java.util.Date datumRadnogNaloga) throws ParseException {
//		System.out.println("*********************************************************************************************************");
//		System.out.println("*********Unos novog radnog naloga***********");
//		int radniNalogId = sviRadniNalozi.size() + 1;
//		
////		if(radniNalogId ==0){
////			brojacID++;
////			radniNalogId = brojacID;
////		}
//		brojacID++;
//		
//		System.out.print("Unesi radni termin: (\"1\" ili \"2\" ili \"3\" ili \"4\") :");
//		int radniTerminId = PomocnaKlasa.ocitajCeoBroj();
//		
//		
//		System.out.println("Unesite datum u obliku ddMMyyyy");
//		SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy");
//		try {
//			datumRadnogNaloga = formatter.parse(PomocnaKlasa.ocitajTekst());
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//		
//		
//		
//		System.out.print("Unesi registraciju vozila za radni nalog: ");
//		String registracija = PomocnaKlasa.ocitajTekst().toUpperCase();
//		System.out.println("Unesi saobraćajnu dozvolu vlasnika za dato vozilo: ");
//		String saobDozvola = PomocnaKlasa.ocitajTekst();
//		System.out.print("Unesi marku-model vozila za radni nalog: ");
//		String markaModel = PomocnaKlasa.ocitajTekst();
//		System.out.print("Unesi godište vozila za radni nalog: ");
//		int godiste = PomocnaKlasa.ocitajCeoBroj();
//		System.out.print("Unesi tip motora vozila za radni nalog: ");
//		String tipMotora = PomocnaKlasa.ocitajTekst();
//		System.out.print("Unesi kilometražu vozila za radni nalog: ");
//		int kilometraza = PomocnaKlasa.ocitajCeoBroj();
//		System.out.print("Unesi nalogodavca za radni nalog: ");
//		String nalogodavac = PomocnaKlasa.ocitajTekst();
//		System.out.print("Unesi primedbe nalogodavca: ");
//		String primedbe = PomocnaKlasa.ocitajTekst();
//		System.out.print("Unesi šta je otklonjeno:  ");
//		String otklonjeno = PomocnaKlasa.ocitajTekst();
//		System.out.print("Unesi šta je ugrađeno: ");
//		String ugradjeno = PomocnaKlasa.ocitajTekst();
//		System.out.print("Unesi proizvođače ugrađenih delova: ");
//		String proizvodjac = PomocnaKlasa.ocitajTekst();
//		System.out.print("Unesi način pakovanja: ");
//		String uzetoU = PomocnaKlasa.ocitajTekst();
//		System.out.print("Unesi gde je uzeto: ");
//		String pakovanje = PomocnaKlasa.ocitajTekst();
//		System.out.print("Unesi količinu: ");
//		int kolicina = PomocnaKlasa.ocitajCeoBroj();
//		System.out.print("Unesi ukupnu cifru: ");
//		int ukupno = PomocnaKlasa.ocitajCeoBroj();
//		RadniNalog rn = new RadniNalog( null, null, null, radniNalogId, radniTerminId, datumRadnogNaloga, registracija, saobDozvola, markaModel, godiste, tipMotora, kilometraza, 
//				nalogodavac, primedbe, otklonjeno, ugradjeno, proizvodjac, uzetoU, pakovanje, kolicina, ukupno );
//		sviRadniNalozi.add(rn);
//		System.out.println("******** Radni nalog je dodat *************");
//	}
//
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	public static void ukloniRadniNalog(RadniNalog rnal) {
//		
//		
//		System.out.println("*********************************************************************************************************");
//		System.out.println("*********Brisanje  radnog naloga***********");
//		int pozicija = pronadjiPozicijuRadnogNaloga();
//		if( pozicija != -1 ) {
//			sviRadniNalozi.remove(pozicija);
//			System.out.println("Radni nalog uklonjen iz evidencije.");
//			System.out.println("*********************************************************************************************************");
//		}
//
//	}
//
//
//
//
//	public static RadniNalog pronadjiRadniNalog() {
//		System.out.println(
//				"*********************************************************************************************************");
//		RadniNalog retVal = null;
//		System.out.println("Unesi registraciju vozila radnog naloga : ");
//		String registracija = PomocnaKlasa.ocitajTekst().toUpperCase();
//		retVal = pronadjiRadniNalogRegistracija(registracija);
//		if (retVal == null)
//			System.out.println(
//					"*** Radni nalog vozila sa registracijom: " + registracija + " ne postoji u evidenciji. ***");
//		return retVal;
//	}
//
//	public static RadniNalog pronadjiRadniNalogRegistracija(String registracija) {
//		RadniNalog retVal = null;
//		for (int i = 0; i < sviRadniNalozi.size(); i++) {
//			RadniNalog rn = sviRadniNalozi.get(i);
//			if (rn.getRegistracija() == registracija) {
//				retVal = rn;
//				break;
//			}
//		}
//		return retVal;
//	}
//
//	public static RadniNalog pronadjiRadniNalogId(int radniNalogId) {
//		
//		RadniNalog retVal = null;
//		for (int i = 0; i < sviRadniNalozi.size(); i++) {
//			RadniNalog rn = sviRadniNalozi.get(i);
//			if (rn.getId() == radniNalogId) {
//				retVal = rn;
//				System.out.println(
//						"************************** čitanje radnog naloga iz evidencije ******************************************");
//				break;
//			}
//		}
//		return retVal;
//	}
//
//	public static int pronadjiPozicijuRadnogNaloga() {
//		int retVal = -1;
//		System.out.println("Unesi id radnog naloga : ");
//		Integer id = PomocnaKlasa.ocitajCeoBroj();
//		for (int i = 0; i < sviRadniNalozi.size(); i++) {
//			RadniNalog rn = sviRadniNalozi.get(i);
//			if (rn.getId() == id) {
//				retVal = i;
//				break;
//			}
//		}
//		if (retVal == -1)
//			System.out.println("Radni nalog id: " + id + " ne postoji u evidenciji!");
//		return retVal;
//	}
//
//	
//	
////	private static void IzmenaPodatakaORadnomNalogu() {
////		System.out.println(
////				"*********************************************************************************************************");
////		RadniNalog rn = pronadjiRadniNalog();
////		if (rn != null) {
////			System.out.print("Unesi broj radnog naloga: ");
////			int rnId = PomocnaKlasa.ocitajCeoBroj();
////
////			rn.setId(rnId);
////		} else
////			System.out.println("Nema takvog radnog termina! ");
////	}
////
////	
////	
////	private static void brisanjePodatakaORadnomNalogu() {
////		System.out.println(
////				"*********************************************************************************************************");
////		int pozicija = pronadjiPozicijuRadnogNaloga();
////		if (pozicija != -1) {
////
////			RadniNalog rn = sviRadniNalozi.remove(pozicija);
////
////			ArrayList<RadniNalog> listRadnihNalogaOdVozilaZaBrisanje = new ArrayList<RadniNalog>(rn.getRadniNalozi());
////
////			// sada moramo ukloniti sve za radni nalog
////			for (RadniNalog rnal : listRadnihNalogaOdVozilaZaBrisanje) {
////				RadniNalogUI.ukloniRadniNalog(rnal);
////			}
////			System.out.println("Podaci obrisani iz evidencije.");
////		} else
////			System.out.println("Nema takvog radnog termina.");
////	}
//
//	public static void pisiUFajlRadneNaloge(File radniNaloziFajl) throws IOException {
//		PrintWriter out2 = new PrintWriter( new FileWriter( radniNaloziFajl ) );
//		for( RadniNalog rn : sviRadniNalozi ) {
//			out2.print(rn.toFileRepresentation());
//		}
//		out2.flush();
//		out2.close();
//	}
//
//	
//	
//	
//
////	public static void dodajRadniNalogNaRadniTermin() {
////		RadniNalog rnal = RadniNalogUI.pronadjiRadniNalog();
////		RadniTermin rter = RadniTerminUI.pronadjiRadniTermin();
////		
////		dodajRadniNalogNaRadniTermin(rnal, rter);
////	}
////	
////	public static void dodajRadniNalogNaRadniTermin(RadniNalog rn) {
////
////		RadniNalog rnal = RadniNalogUI.pronadjiRadniNalog();
////		RadniTermin rter = RadniTerminUI.pronadjiRadniTermin();
////		dodajRadniNalogNaRadniTermin(rnal, rter);
////
////	}
////	
////	public static void dodajRadniNalogNaRadniTermin(RadniTermin rt) {
////		
////		RadniTermin rter = RadniTerminUI.pronadjiRadniTermin();
////		RadniNalog rnal = RadniNalogUI.pronadjiRadniNalog();;
////		dodajRadniNalogNaRadniTermin(rnal, rter);
////		
////	}
////	
////	public static void dodajRadniNalogNaRadniTermin(RadniNalog rn, int rter) {
////		if (rn != null && rter != 0) {
////			int radniTermin = rn.getRadniTerminId();
////			boolean found = false;
////
////			if (radniTermin) {
////				found = true;
//////				break;
////			} else if (!found) {
////				rn.setRadniTerminId(rter);
////				RadniTermin.getRadniNalozi().add(rn);
////			}
////		}
////	}
//	
//	public static void dodajRadniNalog() {
//		
//		//prvo pronadjemo klijenta koga zelimo dodati na radni nalog
//		Klijent klijent = KlijentUI.pronadjiKlijentaSaobDozvola();
//		
//		//zatim pronadjemo vozilo koje zelimo da dodamo na radni nalog
//		Vozilo vozilo = VoziloUI.pronadjiVoziloRegistracija();
//		
//		//i na kraju pronadjemo radni termin koji zelimo da dodamo na radni nalog
//		RadniTermin radniTermin = RadniTerminUI.pronadjiRadniTermin();
//		
//		dodajRadniNalog(radniTermin, klijent, vozilo);
//		
//		
//	}
//	
//	public static void dodajRadniNalog(RadniTermin radniTermin) {
//		
//		// prvo pronadjemo klijenta kog zelimo dodati na radni nalog
//		Klijent klijent = KlijentUI.pronadjiKlijentaSaobDozvola();
//		
//		// pronadjemo vozilo koje zelimo dodati na radni nalog
//		Vozilo vozilo = VoziloUI.pronadjiVoziloRegistracija();
//		
//		dodajRadniNalog(radniTermin, klijent, vozilo);
//		
//	}
//	
//	public static void dodajRadniNalogNaRadniTermin(RadniTermin radniTermin) {
//		// prvo pronadjemo klijenta za koga zelimo dodati radni nalog
//		Klijent klijent = KlijentUI.pronadjiKlijentaSaobDozvola();
//		// zatim pronadjemo vozilo
//		Vozilo vozilo = VoziloUI.pronadjiVoziloRegistracija();
//		
//		dodajRadniNalog(radniTermin, klijent, vozilo);
//		
//	}
//	
//	public static void dodajRadniNalog(Klijent klijent) {
//		
//		// pronadjemo vozilo koje zelimo dodati na radni nalog
//		Vozilo vozilo = VoziloUI.pronadjiVoziloRegistracija();
//		// pronadjemo radni termin koji zelimo dodati na radni nalog
//		RadniTermin radniTermin = RadniTerminUI.pronadjiRadniTermin();
//		
//		dodajRadniNalog(radniTermin, klijent, vozilo);
//		
//	}
//	
//	public static void dodajRadniNalog(Vozilo vozilo) {
//		
//		// prvo pronadjemo klijenta koga zelimo dodati na radni nalog
//		Klijent klijent = KlijentUI.pronadjiKlijentaSaobDozvola();
//		// pronadjemo radni termin koji zelimo dodati na radni nalog
//		RadniTermin radniTermin = RadniTerminUI.pronadjiRadniTermin();
//		
//		dodajRadniNalog( radniTermin, klijent, vozilo );
//		
//	}
//
//	public static void dodajRadniNalog(RadniTermin radniTermin, Klijent klijent, Vozilo vozilo) {
//		if( radniTermin != null  &&  klijent != null  &&   vozilo != null) {
//			// uspostavimo vezu uzmedju entiteta
//			List<RadniNalog> radniNalozi = radniTermin.getRadniNalozi();
//			boolean found = false;
//			for( int i = 0; i < radniNalozi.size(); i++ ) {
//				setRadniTerminId(radniNalozi.get(i).getRadniTerminId());
//				if(radniTermin.equals(getRadniTerminId()) && klijent.equals(radniNalozi.get(i).getKl()) 
//						&& vozilo.equals(radniNalozi.get(i).getVzl())) {
//					found = true;
//					break;
//				}
//			}
//			if(!found) {
//				System.out.println("*********************************************************************************************************");
//				System.out.println("*********Unos novog radnog naloga***********");
//				int radniNalogId = sviRadniNalozi.size() + 1;
//				
//				if(radniNalogId ==0){
//					brojacID++;
//					radniNalogId = brojacID;
//				}
//				brojacID++;
//				
//				System.out.print("Unesi radni termin: (\"1\" ili \"2\" ili \"3\" ili \"4\") :");
//				int radniTerminId = PomocnaKlasa.ocitajCeoBroj();
//				
//				
//				System.out.println("Unesite datum u obliku ddMMyyyy");
//				SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy");
//				System.out.print("Unesi datum radnog naloga: (\"ddMMyyyy\") :");
//				java.util.Date datumRadnogNaloga = null;
//				try {
//					datumRadnogNaloga = formatter.parse(PomocnaKlasa.ocitajTekst());
//				} catch (ParseException e) {
//					e.printStackTrace();
//				}
//				
//				
//				
//				System.out.print("Unesi registraciju vozila za radni nalog: ");
//				String registracija = PomocnaKlasa.ocitajTekst().toUpperCase();
//				System.out.println("Unesi saobraćajnu dozvolu vlasnika za dato vozilo: ");
//				String saobDozvola = PomocnaKlasa.ocitajTekst();
//				System.out.print("Unesi marku-model vozila za radni nalog: ");
//				String markaModel = PomocnaKlasa.ocitajTekst();
//				System.out.print("Unesi godište vozila za radni nalog: ");
//				int godiste = PomocnaKlasa.ocitajCeoBroj();
//				System.out.print("Unesi tip motora vozila za radni nalog: ");
//				String tipMotora = PomocnaKlasa.ocitajTekst();
//				System.out.print("Unesi kilometražu vozila za radni nalog: ");
//				int kilometraza = PomocnaKlasa.ocitajCeoBroj();
//				System.out.print("Unesi nalogodavca za radni nalog: ");
//				String nalogodavac = PomocnaKlasa.ocitajTekst();
//				System.out.print("Unesi primedbe nalogodavca: ");
//				String primedbe = PomocnaKlasa.ocitajTekst();
//				System.out.print("Unesi šta je otklonjeno:  ");
//				String otklonjeno = PomocnaKlasa.ocitajTekst();
//				System.out.print("Unesi šta je ugrađeno: ");
//				String ugradjeno = PomocnaKlasa.ocitajTekst();
//				System.out.print("Unesi proizvođače ugrađenih delova: ");
//				String proizvodjac = PomocnaKlasa.ocitajTekst();
//				System.out.print("Unesi gde je uzeto: ");
//				String uzetoU = PomocnaKlasa.ocitajTekst();
//				System.out.print("Unesi način pakovanja: ");
//				String pakovanje = PomocnaKlasa.ocitajTekst();
//				System.out.print("Unesi količinu: ");
//				int kolicina = PomocnaKlasa.ocitajCeoBroj();
//				System.out.print("Unesi ukupnu cifru: ");
//				int ukupno = PomocnaKlasa.ocitajCeoBroj();
//				RadniNalog rn = new RadniNalog( klijent, vozilo, radniTermin, radniNalogId, radniTerminId, datumRadnogNaloga, registracija, saobDozvola, markaModel, godiste, tipMotora, kilometraza, 
//						nalogodavac, primedbe, otklonjeno, ugradjeno, proizvodjac, uzetoU, pakovanje, kolicina, ukupno );
//				sviRadniNalozi.add(rn);
//				System.out.println("******** Radni nalog je dodat *************");
//			}
//		}
//		else {
//			System.out.println("U evidenciji ne postoje podaci o radnom terminu/klijentu/vozilu!");
//		}
//	}
//	
//	
//	
//	
//	
//
//	/**
//	 * @return the radniTerminId
//	 */
//	public static int getRadniTerminId() {
//		return radniTerminId;
//	}
//
//	/**
//	 * @param radniTerminId the radniTerminId to set
//	 */
//	public static  void setRadniTerminId(int radniTerminId) {
//		RadniNalogUI.radniTerminId = radniTerminId;
//	}
//
//	
//	
//	
//	
//
//}
//
