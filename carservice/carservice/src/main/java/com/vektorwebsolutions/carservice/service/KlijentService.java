package com.vektorwebsolutions.carservice.service;

import org.springframework.data.domain.Page;

import com.vektorwebsolutions.carservice.model.Klijent;

public interface KlijentService {
	Klijent findOne(Long id);
	Page<Klijent> findAll(int pageNum);
	Page<Klijent> search(String ime, String prezime, String telefon, int pageNum);
	Klijent save(Klijent klijent);
	Klijent delete(Long id);
}
