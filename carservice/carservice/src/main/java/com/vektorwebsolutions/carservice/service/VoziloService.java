package com.vektorwebsolutions.carservice.service;

import org.springframework.data.domain.Page;

import com.vektorwebsolutions.carservice.model.Vozilo;

public interface VoziloService {
	Vozilo findOne(Long id);
	Page<Vozilo> findAll(int pageNum);
	Page<Vozilo> search(Integer godiste, String gorivo, String markaModel, String motTyp, String registracija,
			Long klijentId, int pageNum);
	Vozilo save(Vozilo vozilo);
	Vozilo delete(Long id);
	
	
	
}

