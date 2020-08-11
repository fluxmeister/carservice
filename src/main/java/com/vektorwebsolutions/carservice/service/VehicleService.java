package com.vektorwebsolutions.carservice.service;

import org.springframework.data.domain.Page;

import com.vektorwebsolutions.carservice.model.Vehicle;

public interface VehicleService {
	Vehicle findOne(Long id);
	Page<Vehicle> findAll(int pageNum);
	Page<Vehicle> search(Integer year, String fuel, String brandMake, String engine, String plates,
			Long clientId, int pageNum);
	Vehicle save(Vehicle vehicle);
	Vehicle delete(Long id);
	
	
	
}

