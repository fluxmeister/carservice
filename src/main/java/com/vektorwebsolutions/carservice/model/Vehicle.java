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
public class Vehicle {
	@Id
	@GeneratedValue
	@Column
	private Long id;
	@Column
	private Integer year;
	@Column
	private String fuel;
	@Column
	private String brandMake;
	@Column
	private String engine;
	@Column
	private String plates;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Client client;
	
//	@OneToMany(mappedBy="vozilo", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
//	private List<RadniNalog> radniNalozi = new ArrayList<>();
	
	public Long getVehicleId() {
		return this.id;
	}
	public void setVehicleId(Long vehicleId) {
		this.id = vehicleId;
	}
	public String getPlates() {
		return plates;
	}
	public void setPlates(String plates) {
		this.plates = plates;
	}
	public String getBrandMake() {
		return brandMake;
	}
	public void setBrandMake(String brandMake) {
		this.brandMake = brandMake;
	}
	public void setEngine(String engine) {
		this.engine = engine;
	}
	public String getEngine() {
		return engine;
	}
	public String getFuel() {
		return fuel;
	}
	public void setFuel(String fuel) {
		this.fuel = fuel;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public Client getClient() {
		return client;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public void setClient(Client client) {
		this.client = client;
		if(!client.getVehicles().contains(this)) {
			client.getVehicles().add(this);
		}
	}
	
//	public List<RadniNalog> getRadniNalozi() {
//		return radniNalozi;
//	}
//	public void setRadniNalozi(List<RadniNalog> radniNalozi) {
//		this.radniNalozi = radniNalozi;
//	}
//	public void addRadniNalog(RadniNalog radniNalog) {
//		if(radniNalog.getVozilo() != this) {
//			radniNalog.setVozilo(this);
//		}
//		radniNalozi.add(radniNalog);
//	}
	
}
