package model;

public class RadniTermin {
	
//	public static int brojacID = 0;
	
	private int id;
	protected String naziv;
	protected String pocetak;
	protected String kraj;
//	// Radni nalozi u radnom terminu
//	protected ArrayList<RadniNalog> radniNaloziURadnomTerminu = new ArrayList<RadniNalog>();

//	private Klijent klijentKojiPosedujeVozilo = new Klijent();
	
	/***  KONSTRUKTORI  ***/
	
	
	public RadniTermin(int id, String naziv, String pocetak, String kraj) {
//		if(id==0){
//			brojacID++;
//			id = brojacID;
//		}
		this.id = id;
		this.naziv = naziv;
		this.pocetak = pocetak;
		this.kraj = kraj;
	}
	


	@Override
	public String toString() {
		String ispis = "| Radni termin id: " + id + " :: " + naziv + " :: " + " Početak: " + pocetak + " :: " + " Kraj: " + kraj + " |";
		return ispis;
	}
	
	
	
	@Override
	public boolean equals(Object obj) {
		if( this == obj )
			return true;
		if( obj == null )
			return false;
		if( !( obj instanceof RadniTermin ) ) {
			return false;
		}
		RadniTermin that = (RadniTermin) obj;
		return that.id == this.id;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNaziv() {
		return naziv;
	}


	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}


	public String getPocetak() {
		return pocetak;
	}


	public void setPocetak(String pocetak) {
		this.pocetak = pocetak;
	}


	public String getKraj() {
		return kraj;
	}


	public void setKraj(String kraj) {
		this.kraj = kraj;
	}


	public Klijent getKlijent(Klijent klijentKojiPosedujeVozilo) {
		return klijentKojiPosedujeVozilo;
	}



//	public ArrayList<RadniNalog> getRadniNaloziURadnomTerminu() {
//		return radniNaloziURadnomTerminu;
//	}
//
//
//	public void setRadniNaloziURadnomTerminu(ArrayList<RadniNalog> radniNaloziURadnomTerminu) {
//		this.radniNaloziURadnomTerminu = radniNaloziURadnomTerminu;
//	}
	
	

}
