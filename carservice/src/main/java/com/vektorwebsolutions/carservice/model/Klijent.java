package com.vektorwebsolutions.carservice.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Klijent {
	@Id
	@GeneratedValue
	@Column
	private Long id;
	@Column
	protected String ime;
	@Column
	protected String prezime;
	@Column(unique=true, nullable=false)
	private String telefon;
	
	@OneToMany(mappedBy="klijent", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<Vozilo> vozila = new ArrayList<>();
	
//	@OneToMany(mappedBy="klijent", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
//	private List<RadniNalog> radniNalozi = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long i) {
		this.id = i;
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


	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	
	
	public List<Vozilo> getVozila() {
		return vozila;
	}

	public void setVozila(List<Vozilo> vozila) {
		this.vozila = vozila;
	}

	public void addVozilo(Vozilo vozilo) {
		if(vozilo.getKlijent() != this) {
			vozilo.setKlijent(this);
		}
		vozila.add(vozilo);
	}

}
