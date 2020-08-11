package com.vektorwebsolutions.carservice.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.vektorwebsolutions.carservice.model.Vehicle;
import com.vektorwebsolutions.carservice.repository.VehicleRepository;
import com.vektorwebsolutions.carservice.service.VehicleService;

@Service
@Transactional
public class JpaVehicleService implements VehicleService {

	@Autowired
	private VehicleRepository vehicleRepository;

//	@Autowired
//	private RadniNalogRepository radniNalogRepository;

	@Override
	public Vehicle findOne(Long id) {
		return vehicleRepository.findOne(id);
	}

	@Override
	public Page<Vehicle> findAll(int pageNum) {
		return vehicleRepository.findAll(new PageRequest(pageNum, 30));
	}

	@Override
	public Page<Vehicle> search(Integer year, String fuel, String brandMake, String engine,  String plates, Long clientId, int pageNum) {
		
		if( year != null || fuel != null || brandMake != null || engine != null || plates != null || clientId != null) {
			year = '%' + year + '%'  ;
			fuel = '%' + fuel + '%'  ;
			brandMake = '%' + brandMake + '%'  ;
			engine = '%' + engine + '%'  ;
			plates = '%' + plates + '%'  ;
			clientId = '%' + clientId + '%'  ;
		}
		
		return vehicleRepository.search(year, fuel, brandMake, engine, plates, clientId, new PageRequest(pageNum, 30));
	}

	@Override
	public Vehicle save(Vehicle vehicle) {
		return vehicleRepository.save(vehicle);
	}

	@Override
	public Vehicle delete(Long id) {
		Vehicle vehicle = vehicleRepository.findOne(id);
		if (vehicle != null) {
			vehicleRepository.delete(vehicle);
		}
		return vehicle;
	}

//	@Override
//	public RadniNalog makeNalog(Long id) {
//		Vozilo vozilo = findOne(id);
//
//		if (vozilo != null) {
//			RadniNalog radniNalog = null;
//			if (vozilo.getRegistracija() != null) {
//				radniNalog = new RadniNalog();
//				radniNalogRepository.save(radniNalog);
//
//				voziloRepository.save(vozilo);
//			}
//			return radniNalog;
//		} else {
//			throw new IllegalArgumentException("Pokušaj pravljenja radnog naloga za nepostojeće vozilo!");
//		}
//	}


}
