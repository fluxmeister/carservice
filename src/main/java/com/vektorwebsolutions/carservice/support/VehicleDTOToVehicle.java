package com.vektorwebsolutions.carservice.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.vektorwebsolutions.carservice.model.Vehicle;
import com.vektorwebsolutions.carservice.model.Client;
import com.vektorwebsolutions.carservice.service.VehicleService;
import com.vektorwebsolutions.carservice.service.ClientService;
import com.vektorwebsolutions.carservice.web.dto.VehicleDTO;

@Component
public class VehicleDTOToVehicle implements Converter<VehicleDTO, Vehicle>{

	@Autowired
	private VehicleService vehicleService;
	@Autowired
	private ClientService clientService;
	
	@Override
	public Vehicle convert(VehicleDTO vehicleDTO) {
		
		Client client = clientService.findOne(vehicleDTO.getClientId());
		
		if(client != null) {
			Vehicle vehicle = null;
			
			if(vehicleDTO.getId() != null) {
				vehicle = vehicleService.findOne(vehicleDTO.getId());
			}
			else {
				vehicle = new Vehicle();
			}
			
			vehicle.setYear(vehicleDTO.getYear());
			vehicle.setFuel(vehicleDTO.getFuel());
			vehicle.setBrandMake(vehicleDTO.getBrandMake());
			vehicle.setEngine(vehicleDTO.getEngine());
			vehicle.setPlates(vehicleDTO.getPlates());
			
			vehicle.setClient(client);
			
			return vehicle;
			
		} else {
			throw new IllegalStateException("Tried adding to non-existing entity.");
		}
		
	}

	public List<Vehicle> convert(List<VehicleDTO> vehicleDTOs){
		List<Vehicle> ret = new ArrayList<>();
		
		for(VehicleDTO vehicleDTO : vehicleDTOs) {
			ret.add(convert(vehicleDTO));
		}
		return ret;
	}
}
