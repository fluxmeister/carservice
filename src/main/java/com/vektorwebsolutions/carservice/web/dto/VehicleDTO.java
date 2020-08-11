package com.vektorwebsolutions.carservice.web.dto;

public class VehicleDTO {

	private Long vehicleDtoId;
	protected Integer vehicleDtoYear;
	protected String fuel;
	protected String brandMake;
	protected String engine;
	private String plates;
	private long clientId;
	private String clientName;
	
	public Long getId() {
		return vehicleDtoId;
	}
	public void setVoziloDtoId(Long id) {
		this.vehicleDtoId = id;
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
	public String getFuel() {
		return fuel;
	}
	public void setFuel(String fuel) {
		this.fuel = fuel;
	}
	public int getYear() {
		return vehicleDtoYear;
	}
	public void setYear(Integer year) {
		this.vehicleDtoYear = year;
	}
	public String getEngine() {
		return engine;
	}
	public void setEngine(String engine) {
		this.engine = engine;
	}
	public long getClientId() {
		return clientId;
	}
	public void setClientId(long l) {
		this.clientId = l;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
}
