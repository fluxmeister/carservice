package model;

import java.util.ArrayList;

public class Vozilo {

//	private static int brojacID = 0;
	
	private int voziloID;
	private String registracija;
	protected String tip;
	protected String markaModel;
	protected String gorivo;
	protected String godiste;
	protected String motTyp;
	
	
	/** klijent koji poseduje vozilo  **/
	private Klijent vlasnik = new  Klijent();
	
	
	// Radni nalozi za dato vozilo
	protected ArrayList<RadniNalog> voziloImaZakazaneNaloge = new ArrayList<RadniNalog>();


	
	
	/** KONSTRUKTORI **/
	
	// konstruktor bez parametra
	public Vozilo() {}


	// konstruktor sa dva parametra
	public Vozilo(int id, String registracija) {
//		if( id == 0 ) {
//			brojacID++;
//			id = brojacID;
//		}
		this.voziloID = id;
		this.registracija = registracija;
	}


	


	public Vozilo(int id, String registracija, String tip, String markaModel, String gorivo, String godiste,
			String motTyp) {
		this.voziloID = id;
		this.registracija = registracija;
		this.tip = tip;
		this.markaModel = markaModel;
		this.gorivo = gorivo;
		this.godiste = godiste;
		this.motTyp = motTyp;
	}


	@Override
	public String toString() {
		String ispis = voziloID + ". " + " registracija: " + registracija + ", tip: " + tip + ", marka/model: " + markaModel + 
				", gorivo: " + gorivo + ", godište: " + godiste +", mot/typ: " + motTyp ;
		return ispis;
	}



	@Override
	public boolean equals(Object obj) {
		if( obj == null ) return false;
		if( obj == this ) return true;
		if(! (obj instanceof Vozilo)) return false;
		
		Vozilo that = (Vozilo) obj;
		return that.voziloID == this.voziloID;
		
	}


	public int getId() {
		return voziloID;
	}


	public int setId(int id) {
		return this.voziloID = id;
	}


	public String getRegistracija() {
		return registracija;
	}


	public void setRegistracija(String registracija) {
		this.registracija = registracija;
	}


	public String getTip() {
		return tip;
	}


	public void setTip(String tip) {
		this.tip = tip;
	}


	public String getMarkaModel() {
		return markaModel;
	}


	public void setMarkaModel(String markaModel) {
		this.markaModel = markaModel;
	}


	public String getGorivo() {
		return gorivo;
	}


	public void setGorivo(String gorivo) {
		this.gorivo = gorivo;
	}
	
	public String getMotTyp() {
		return motTyp;
	}


	public void setMotTyp(String motTyp) {
		this.motTyp = motTyp;
	}


	public String getGodiste() {
		return godiste;
	}


	public void setGodiste(String godiste) {
		this.godiste = godiste;
	}


	public Klijent getVlasnik() {
		return vlasnik;
	}


	public void setVlasnik(Klijent vlasnik) {
		this.vlasnik = vlasnik;
	}


	public ArrayList<RadniNalog> getVoziloImaZakazaneNaloge() {
		return voziloImaZakazaneNaloge;
	}


	public void setVoziloImaZakazaneNaloge(ArrayList<RadniNalog> voziloImaZakazaneNaloge) {
		this.voziloImaZakazaneNaloge = voziloImaZakazaneNaloge;
	}


	
}
