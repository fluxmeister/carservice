package ui;

import utils.PomocnaKlasa;

public class FirmaUI {

	/** METODE ZA ISPIS OPCIJA ****/
	// ispis teksta osnovnih opcija

	private static void ispisiTekstFirmaOpcije() {
		System.out.println(
				"*********************************************************************************************************");
		System.out.println("********** Rad sa firmama **********");
		System.out.println(" Opcija 1: Unos podataka o novoj firmi");
		System.out.println(" Opcija 2: Izmena podataka o određenoj firmi");
		System.out.println(" Opcija 3: Brisanje firme iz evidencije");
		System.out.println(" Opcija 4: Ispis podataka svih firmi");
		System.out.println(" Opcija 5: ispis podataka o određenoj firmi sa pripadajućim vozilima na servisu");
		System.out.println(" Opcija 6: ispis podataka o određenoj firmi sa pripadajućim radnim nalozima");
		System.out.println(" Opcija 7: sortiranje klijenata po imenu");
		System.out.println("\t\t ...");
		System.out.println(" Opcija 0: IZLAZ u prethodni meni");
		System.out.println(
				"*********************************************************************************************************");
	}

	public static void meniFirmaUI() {

		int odluka = -1;
		while (odluka != 0) {

			ispisiTekstFirmaOpcije();
			System.out.println("Vaš izbor: ");
			odluka = PomocnaKlasa.ocitajCeoBroj();
			switch (odluka) {

			case 0:
				System.out.println("Izlaz iz menija sa firmama ...");
				break;
			case 1:
				System.out.println("Treba napisati metodu za unos podataka o novoj firmi.");
				break;
			case 2:
				System.out.println("Treba napisati metodu za izmenu podataka o određenoj firmi.");
				break;
			case 3:
				System.out.println("Treba napisati metodu za brisanje firme iz evidencije.");
				break;
			case 4:
				System.out.println("Treba napisati metodu za ispis podataka svih firmi.");
				break;
//			
			}

		}

	}
}
