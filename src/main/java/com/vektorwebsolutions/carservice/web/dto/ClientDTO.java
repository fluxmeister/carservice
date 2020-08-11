package com.vektorwebsolutions.carservice.web.dto;

public class ClientDTO {

	private Long id;
	protected String name;
	protected String surname;
	private String phone;

	
	public Long getId() {
		return id;
	}

	public void setId(Long l) {
		this.id = l;
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
}
