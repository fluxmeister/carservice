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
public class Client {
	@Id
	@GeneratedValue
	@Column
	private Long id;
	@Column
	protected String name;
	@Column
	protected String surname;
	@Column(unique=true, nullable=false)
	private String phone;
	
	@OneToMany(mappedBy="client", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<Vehicle> vehicles = new ArrayList<>();
	
//	@OneToMany(mappedBy="klijent", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
//	private List<RadniNalog> radniNalozi = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long i) {
		this.id = i;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}


	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	
	
	public List<Vehicle> getVehicles() {
		return vehicles;
	}

	public void setVehicles(List<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}

	public void addVehicle(Vehicle vehicle) {
		if(vehicle.getClient() != this) {
			vehicle.setClient(this);
		}
		vehicles.add(vehicle);
	}

//	public List<RadniNalog> getRadniNalozi() {
//		return radniNalozi;
//	}
//
//	public void setRadniNalozi(List<RadniNalog> radniNalozi) {
//		this.radniNalozi = radniNalozi;
//	}

	
	
	
}
