package com.vektorwebsolutions.carservice.web.dto;

public class VoziloDTO {

	private Long voziloDtoId;
	protected Integer voziloDtoGodiste;
	protected String gorivo;
	protected String markaModel;
	protected String motTyp;
	private String registracija;
	private long klijentId;
	private String klijentIme;
	
	public Long getId() {
		return voziloDtoId;
	}
	public void setVoziloDtoId(Long id) {
		this.voziloDtoId = id;
	}
	public String getRegistracija() {
		return registracija;
	}
	public void setRegistracija(String registracija) {
		this.registracija = registracija;
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
	public int getGodiste() {
		return voziloDtoGodiste;
	}
	public void setGodiste(Integer godiste) {
		this.voziloDtoGodiste = godiste;
	}
	public String getMotTyp() {
		return motTyp;
	}
	public void setMotTyp(String motTyp) {
		this.motTyp = motTyp;
	}
	public long getKlijentId() {
		return klijentId;
	}
	public void setKlijentId(long l) {
		this.klijentId = l;
	}
	public String getKlijentIme() {
		return klijentIme;
	}
	public void setKlijentIme(String klijentIme) {
		this.klijentIme = klijentIme;
	}
}
