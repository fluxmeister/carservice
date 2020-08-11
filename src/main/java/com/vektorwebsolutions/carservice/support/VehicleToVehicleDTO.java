package com.vektorwebsolutions.carservice.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.vektorwebsolutions.carservice.model.Vehicle;
import com.vektorwebsolutions.carservice.web.dto.VehicleDTO;

@Component
public class VehicleToVehicleDTO implements Converter<Vehicle, VehicleDTO>{

	@Override
	public VehicleDTO convert(Vehicle vehicle) {
		if(vehicle == null) {
			return null;
		}
		VehicleDTO dto = new VehicleDTO();
		
		dto.setVoziloDtoId(vehicle.getVehicleId());
		dto.setYear(vehicle.getYear());
		dto.setFuel(vehicle.getFuel());
		dto.setBrandMake(vehicle.getBrandMake());
		dto.setEngine(vehicle.getEngine());
		dto.setPlates(vehicle.getPlates());
		
		dto.setClientId(vehicle.getClient().getId());
		dto.setClientName(vehicle.getClient().getName());
		
		return dto;
	}
	
	public List<VehicleDTO> convert(List<Vehicle> vehicles){
		List<VehicleDTO> dtoList = new ArrayList<>();
		
		for(Vehicle vehicle: vehicles) {
			dtoList.add(convert(vehicle));
		}
		return dtoList;
	}
	
	
}
