package com.vektorwebsolutions.carservice.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Vozilo {
	@Id
	@GeneratedValue
	@Column
	private Long id;
	@Column
	private Integer godiste;
	@Column
	private String gorivo;
	@Column
	private String markaModel;
	@Column
	private String motTyp;
	@Column
	private String registracija;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Klijent klijent;
	
	@OneToMany(mappedBy="vozilo", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<RadniNalog> radniNalozi = new ArrayList<>();
	
	public Long getVoziloId() {
		return this.id;
	}
	public void setVoziloId(Long voziloId) {
		this.id = voziloId;
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
	public void setMotTyp(String motTyp) {
		this.motTyp = motTyp;
	}
	public String getMotTyp() {
		return motTyp;
	}
	public String getGorivo() {
		return gorivo;
	}
	public void setGorivo(String gorivo) {
		this.gorivo = gorivo;
	}
	public int getGodiste() {
		return godiste;
	}
	public void setGodiste(int godiste) {
		this.godiste = godiste;
	}
	public Klijent getKlijent() {
		return klijent;
	}
	public void setGodiste(Integer godiste) {
		this.godiste = godiste;
	}
	public void setKlijent(Klijent klijent) {
		this.klijent = klijent;
		if(!klijent.getVozila().contains(this)) {
			klijent.getVozila().add(this);
		}
	}
	
	public List<RadniNalog> getRadniNalozi() {
		return radniNalozi;
	}
	public void setRadniNalozi(List<RadniNalog> radniNalozi) {
		this.radniNalozi = radniNalozi;
	}
	public void addRadniNalog(RadniNalog radniNalog) {
		if(radniNalog.getVozilo() != this) {
			radniNalog.setVozilo(this);
		}
		radniNalozi.add(radniNalog);
	}
	
}
