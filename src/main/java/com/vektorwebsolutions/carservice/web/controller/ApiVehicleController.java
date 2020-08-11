package com.vektorwebsolutions.carservice.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vektorwebsolutions.carservice.model.Vehicle;
import com.vektorwebsolutions.carservice.service.VehicleService;
import com.vektorwebsolutions.carservice.support.VehicleDTOToVehicle;
import com.vektorwebsolutions.carservice.support.VehicleToVehicleDTO;
import com.vektorwebsolutions.carservice.web.dto.VehicleDTO;

@RestController
@RequestMapping(value="/api/vehicles")
public class ApiVehicleController {
	
	@Autowired
	private VehicleService vehicleService;
	
	@Autowired
	private VehicleToVehicleDTO toDTO;
	
	@Autowired
	private VehicleDTOToVehicle toVehicle;
	
	@RequestMapping(method=RequestMethod.GET)
	ResponseEntity<List<VehicleDTO>> getVehicles(
			@RequestParam(required=false) Integer year,
			@RequestParam(required=false) String fuel,
			@RequestParam(required=false) String brandMake, 
			@RequestParam(required=false) String engine, 
			@RequestParam(required=false) String plates, 
			@RequestParam(required=false) Long clientId, 
			@RequestParam(value="pageNum", defaultValue="0") int pageNum ){
				Page<Vehicle> vehiclesPage = null;
				
				if(plates != null || clientId != null || fuel != null) {
					vehiclesPage = vehicleService.search(year, fuel, brandMake, engine, plates, clientId, pageNum);
				}
				else {
					vehiclesPage = vehicleService.findAll(pageNum);
				}
				
				HttpHeaders headers = new HttpHeaders();
				headers.add("totalPages", Integer.toString(vehiclesPage.getTotalPages()));
				
				return new ResponseEntity<>(
						toDTO.convert(vehiclesPage.getContent()), 
						headers, 
						HttpStatus.OK);
			}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	ResponseEntity<VehicleDTO> getVehicle(@PathVariable Long id){
		Vehicle vehicle = vehicleService.findOne(id);
		if(vehicle == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
			return new ResponseEntity<>(
					toDTO.convert(vehicle),
					HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	ResponseEntity<VehicleDTO> delete(@PathVariable Long id){
		Vehicle deleted = vehicleService.delete(id);
		
		if(deleted == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(
				toDTO.convert(deleted),
				HttpStatus.OK);
	}

	
	@RequestMapping(method=RequestMethod.POST, 
					consumes="application/json")
	public ResponseEntity<VehicleDTO> add(
			@Validated @RequestBody VehicleDTO newVehicleDTO){
		
		Vehicle savedVehicle = vehicleService.save(toVehicle.convert(newVehicleDTO));
		
		return new ResponseEntity<>(
				toDTO.convert(savedVehicle), HttpStatus.CREATED);
	}
	
	
	@RequestMapping(method=RequestMethod.PUT, 
			value="/{id}", 
			consumes="application/json")
	public ResponseEntity<VehicleDTO> edit(
			@Validated @RequestBody VehicleDTO vehicleDTO, 
			@PathVariable Long id){
		
		if( !id.equals(vehicleDTO.getId()) ) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Vehicle persisted = vehicleService.save(toVehicle.convert(vehicleDTO));
		
		return new ResponseEntity<>(toDTO.convert(persisted), HttpStatus.OK);
		
	}

	
	@ExceptionHandler(value=DataIntegrityViolationException.class)
	public ResponseEntity<Void> handle(){
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
}
