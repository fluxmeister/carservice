package com.vektorwebsolutions.carservice;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vektorwebsolutions.carservice.model.Client;
import com.vektorwebsolutions.carservice.model.Vehicle;
import com.vektorwebsolutions.carservice.service.ClientService;
import com.vektorwebsolutions.carservice.service.VehicleService;

@Component
public class TestData{
	
	@Autowired
	private ClientService cs;
	
	@Autowired VehicleService vs;
	
	@PostConstruct
	public void init() {
		Client c1 = new Client();
		c1.setName("Petar");
		c1.setSurname("Petrović");
		c1.setPhone("063/88-02-331");
		cs.save(c1);
		
		Client c2 = new Client();
		c2.setName("Srđan");
		c2.setSurname("Slavković");
		c2.setPhone("060/15-18-293");
		cs.save(c2);
		
		Client c3 = new Client();
		c3.setName("Mirela");
		c3.setSurname("Slavković");
		c3.setPhone("064/18-29-666");
		cs.save(c3);
	
		Vehicle v1 = new Vehicle();
		v1.setYear(2003);
		v1.setFuel("benzin+gas");
		v1.setBrandMake("VW Golf2");
		v1.setEngine("1.2 16v");
		v1.setPlates("SU-1234-GN");
		v1.setClient(c1);
		vs.save(v1);
		
		Vehicle v2 = new Vehicle();
		v2.setYear(2005);
		v2.setFuel("dizel");
		v2.setBrandMake("Audi TT");
		v2.setEngine("2.0 GDI");
		v2.setPlates("BG-4568-MF");
		v2.setClient(c2);
		vs.save(v2);
		
		Vehicle v3 = new Vehicle();
		v3.setYear(2007);
		v3.setFuel("benzin+gas");
		v3.setBrandMake("VW Golf2");
		v3.setEngine("1.8 GSI");
		v3.setPlates("RU-1874-KK");
		v3.setClient(c3);
		vs.save(v3);
	}
}