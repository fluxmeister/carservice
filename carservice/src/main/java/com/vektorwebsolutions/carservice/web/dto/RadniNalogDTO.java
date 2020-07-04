package com.vektorwebsolutions.carservice.web.dto;

import java.util.Date;

public class RadniNalogDTO {
	
	private Long id;
	private Long voziloId;
	
	public Date datumRadnogNaloga;
	protected String registracija;
	protected String saobDozvola;
	protected String markaModel;
	protected int godiste;
	protected String tipMotora;
	protected Long kilometraza;
	protected String nalogodavac;
	protected String primedbe;
	protected String otklonjeno;
	protected String ugradjeno;
	protected String proizvodjac;
	protected String uzetoU;
	protected String pakovanje;
	protected int kolicina;
	protected int ukupno;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getDatumRadnogNaloga() {
		return datumRadnogNaloga;
	}
	public void setDatumRadnogNaloga(Date datumRadnogNaloga) {
		this.datumRadnogNaloga = datumRadnogNaloga;
	}
	public String getRegistracija() {
		return registracija;
	}
	public void setRegistracija(String registracija) {
		this.registracija = registracija;
	}
	public String getSaobDozvola() {
		return saobDozvola;
	}
	public void setSaobDozvola(String saobDozvola) {
		this.saobDozvola = saobDozvola;
	}
	public String getMarkaModel() {
		return markaModel;
	}
	public void setMarkaModel(String markaModel) {
		this.markaModel = markaModel;
	}
	public int getGodiste() {
		return godiste;
	}
	public void setGodiste(int godiste) {
		this.godiste = godiste;
	}
	public String getTipMotora() {
		return tipMotora;
	}
	public void setTipMotora(String tipMotora) {
		this.tipMotora = tipMotora;
	}
	public Long getKilometraza() {
		return kilometraza;
	}
	public void setKilometraza(Long kilometraza) {
		this.kilometraza = kilometraza;
	}
	public String getNalogodavac() {
		return nalogodavac;
	}
	public void setNalogodavac(String nalogodavac) {
		this.nalogodavac = nalogodavac;
	}
	public String getPrimedbe() {
		return primedbe;
	}
	public void setPrimedbe(String primedbe) {
		this.primedbe = primedbe;
	}
	public String getOtklonjeno() {
		return otklonjeno;
	}
	public void setOtklonjeno(String otklonjeno) {
		this.otklonjeno = otklonjeno;
	}
	public String getUgradjeno() {
		return ugradjeno;
	}
	public void setUgradjeno(String ugradjeno) {
		this.ugradjeno = ugradjeno;
	}
	public String getProizvodjac() {
		return proizvodjac;
	}
	public void setProizvodjac(String proizvodjac) {
		this.proizvodjac = proizvodjac;
	}
	public String getUzetoU() {
		return uzetoU;
	}
	public void setUzetoU(String uzetoU) {
		this.uzetoU = uzetoU;
	}
	public String getPakovanje() {
		return pakovanje;
	}
	public void setPakovanje(String pakovanje) {
		this.pakovanje = pakovanje;
	}
	public int getKolicina() {
		return kolicina;
	}
	public void setKolicina(int kolicina) {
		this.kolicina = kolicina;
	}
	public int getUkupno() {
		return ukupno;
	}
	public void setUkupno(int ukupno) {
		this.ukupno = ukupno;
	}
	public Long getVoziloId() {
		return voziloId;
	}
	public void setVoziloId(Long voziloId) {
		this.voziloId = voziloId;
	}
	
	
	
}
