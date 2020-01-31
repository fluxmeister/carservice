package model;

import java.util.ArrayList;
import java.util.List;

public class Klijent {

	protected int id;
	protected String ime, prezime, prebivaliste, telefon;

	// vozila koja poseduje klijent
	protected List<Vozilo> klijentImaVozila = new ArrayList<>();

	// svi radni nalozi za klijenta
	protected List<RadniNalog> klijentZadajeRadneNaloge = new ArrayList<>();

	/*** KONSTRUKTORI ***/

	public Klijent() {}

	public Klijent(int id, String ime, String prezime, String prebivaliste, String telefon) {
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
		this.prebivaliste = prebivaliste;
		this.telefon = telefon;
	}


	


	@Override
	public String toString() {
		String ispis = "Klijent id: " + id + " || " + ime + " " + prezime + " || Prebivalište: " + prebivaliste 
				 + " || Br. mob. telefona: " + telefon + " || Vozila: " + klijentImaVozila;
		return ispis;
	}


	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj == this)
			return true;
		if (!(obj instanceof Klijent))
			return false;

		Klijent that = (Klijent) obj;
		return that.id == this.id;

	}

	
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getPrebivaliste() {
		return prebivaliste;
	}

	public void setPrebivaliste(String prebivaliste) {
		this.prebivaliste = prebivaliste;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public List<Vozilo> getKlijentImaVozila() {
		return klijentImaVozila;
	}

	public void setKlijentImaVozila(List<Vozilo> klijentImaVozila) {
		this.klijentImaVozila = klijentImaVozila;
	}

	public List<RadniNalog> getKlijentZadajeRadneNaloge() {
		return klijentZadajeRadneNaloge;
	}

	public void setKlijentZadajeRadneNaloge(List<RadniNalog> klijentZadajeRadneNaloge) {
		this.klijentZadajeRadneNaloge = klijentZadajeRadneNaloge;
	}

	
	
}

