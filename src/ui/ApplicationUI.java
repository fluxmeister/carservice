package ui;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;

import utils.PomocnaKlasa;

public class ApplicationUI {
	
	private static Connection conn;
	
	static {
		String dbUrl = "jdbc:mysql://localhost:3306/car_service?useSSL=false&useUnicode=true&characterEncoding=UTF-8";
		String user = "fluxmeister";
		String pass = "2143Pauchina";
		
		// otvaranje konekcije, jednom na pocetku aplikacije
		try {

			// učitavanje MySQL drivera
			Class.forName("com.mysql.cj.jdbc.Driver");

			// otvaranje konekcije sa databazom
			conn = DriverManager.getConnection(dbUrl, user, pass);

		} catch (Exception ex) {
			System.out.println("Neuspela konekcija na bazu!");
			ex.printStackTrace();
			// kraj aplikacije
			System.exit(0);
		}

	}
	
	public static void ispisiMenu() {
		
		System.out.println("******************************************************************************");
		System.out.println("*********************************AUTO SERVIS**********************************");
		System.out.println("*********  - osnovni meni - ***********");
		System.out.println("\tOpcija 1 - Rad sa klijentima");
		System.out.println("\tOpcija 2 - Rad sa vozilima");
//		System.out.println("\tOpcija 3 - Rad sa firmama");
//		System.out.println("\tOpcija 4 - Rad sa švercerima");
		System.out.println("\tOpcija 5 - Rad sa radnim terminima");
		System.out.println("\tOpcija 6 - Rad sa posedovanjem");
//		System.out.println("\tOpcija 7 - Rad sa radnim nalozima");
//		System.out.println("\tOpcija 8 - Rad sa serviserima");
		System.out.println("\t\t ...");
		System.out.println("\tOpcija 0 - IZLAZ IZ PROGRAMA");
		System.out.println("******************************************************************************");
		
	}
	
	public static void main( String[] args ) throws IOException, ParseException {
		
		
		int odluka = -1;
		while( odluka != 0 ) {
			
			ApplicationUI.ispisiMenu();
			System.out.println("Vaš izbor:");
			odluka = PomocnaKlasa.ocitajCeoBroj();
			switch (odluka) {

				case 0:
					System.out.println("Završen rad.");
					break;
				case 1:
					KlijentUI.meniKlijentUI();
					break;
				case 2:
					VoziloUI.meniVoziloUI();
					break;
				case 3:
					FirmaUI.meniFirmaUI();
					break;
				case 4:
					System.out.println("Još nije napisan metod.");
					break;
				case 5:
					RadniTerminUI.meniRadniTerminUI();
					break;
				case 6:
					PosedujeUI.meniPosedujeUI();
					break;
//				case 7:
//					RadniNalogUI.meniRadniNalogUI();
//					break;
//				case 8:
//					ServisiraUI.meniServisiraUI();
//					break;
			}
			
		}
		
		
		// zatvaranje konekcije, jednom na kraju aplikacije
				try {
					conn.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
		
		
		
		

		
		System.out.println("Program izvršen.");
		
	}

	public static Connection getConn() {
		return conn;
	}

}
