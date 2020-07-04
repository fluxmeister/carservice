package com.vektorwebsolutions.carservice.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.vektorwebsolutions.carservice.model.Klijent;
import com.vektorwebsolutions.carservice.repository.KlijentRepository;
import com.vektorwebsolutions.carservice.service.KlijentService;

@Service
@Transactional
public class JpaKlijentService implements KlijentService {

	@Autowired
	private KlijentRepository klijentRepository;
	
	@Override
	public Klijent findOne(Long id) {
		return klijentRepository.findOne(id);
	}

	@Override
	public Page<Klijent> findAll(int pageNum) {
		return klijentRepository.findAll(
				new PageRequest(pageNum, 30));
	}
	
	@Override
	public Page<Klijent> search(String ime, String prezime, String telefon, int page) {
		if( ime != null || prezime != null || telefon !=null  ) {
			ime = '%' + ime + '%' ;
			prezime = '%' + prezime + '%' ;
			telefon = '%' + telefon + '%' ;
		}
		return klijentRepository.search(ime, prezime, telefon, new PageRequest(page, 30));
	}
	@Override
	public Klijent save(Klijent klijent) {
		return klijentRepository.save(klijent);
	}

	@Override
	public Klijent delete(Long id) {
		Klijent klijent = klijentRepository.findOne(id);
		if(klijent != null) {
			klijentRepository.delete(klijent);
		}
		return klijent;
	}



}
