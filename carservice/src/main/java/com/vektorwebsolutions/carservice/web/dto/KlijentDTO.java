package com.vektorwebsolutions.carservice.web.dto;

public class KlijentDTO {

	private Long id;
	protected String ime;
	protected String prezime;
	private String telefon;

	
	public Long getId() {
		return id;
	}

	public void setId(Long l) {
		this.id = l;
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
}
