//package com.vektorwebsolutions.carservice;
//
//import javax.annotation.PostConstruct;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import com.vektorwebsolutions.carservice.model.Klijent;
//import com.vektorwebsolutions.carservice.model.Vozilo;
//import com.vektorwebsolutions.carservice.service.KlijentService;
//import com.vektorwebsolutions.carservice.service.VoziloService;
//
//@Component
//public class TestData{
//	
//	@Autowired
//	private KlijentService ks;
//	
//	@Autowired VoziloService vs;
//	
//	@PostConstruct
//	public void init() {
//		Klijent k1 = new Klijent();
//		k1.setIme("Petar");
//		k1.setPrezime("Petrović");
//		k1.setTelefon("063/88-02-331");
//		ks.save(k1);
//		
//		Klijent k2 = new Klijent();
//		k2.setIme("Srđan");
//		k2.setPrezime("Slavković");
//		k2.setTelefon("060/15-18-293");
//		ks.save(k2);
//		
//		Klijent k3 = new Klijent();
//		k3.setIme("Mirela");
//		k3.setPrezime("Slavković");
//		k3.setTelefon("064/18-29-666");
//		ks.save(k3);
//	
//		Vozilo v1 = new Vozilo();
//		v1.setGodiste(2003);
//		v1.setGorivo("benzin+gas");
//		v1.setMarkaModel("VW Golf2");
//		v1.setMotTyp("1.2 16v");
//		v1.setRegistracija("SU-1234-GN");
//		v1.setKlijent(k1);
//		vs.save(v1);
//		
//		Vozilo v2 = new Vozilo();
//		v2.setGodiste(2005);
//		v2.setGorivo("dizel");
//		v2.setMarkaModel("Audi TT");
//		v2.setMotTyp("2.0 GDI");
//		v2.setRegistracija("BG-4568-MF");
//		v2.setKlijent(k2);
//		vs.save(v2);
//		
//		Vozilo v3 = new Vozilo();
//		v3.setGodiste(2007);
//		v3.setGorivo("benzin+gas");
//		v3.setMarkaModel("VW Golf2");
//		v3.setMotTyp("1.8 GSI");
//		v3.setRegistracija("RU-1874-KK");
//		v3.setKlijent(k3);
//		vs.save(v3);
//	}
//}