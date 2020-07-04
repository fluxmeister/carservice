package com.vektorwebsolutions.carservice.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.vektorwebsolutions.carservice.model.Vozilo;
import com.vektorwebsolutions.carservice.repository.VoziloRepository;
import com.vektorwebsolutions.carservice.service.VoziloService;

@Service
@Transactional
public class JpaVoziloService implements VoziloService {

	@Autowired
	private VoziloRepository voziloRepository;

//	@Autowired
//	private RadniNalogRepository radniNalogRepository;

	@Override
	public Vozilo findOne(Long id) {
		return voziloRepository.findOne(id);
	}

	@Override
	public Page<Vozilo> findAll(int pageNum) {
		return voziloRepository.findAll(new PageRequest(pageNum, 30));
	}

	@Override
	public Page<Vozilo> search(Integer godiste, String gorivo, String markaModel, String motTyp,  String registracija, Long klijentId, int pageNum) {
		
		if( godiste != null || gorivo != null || markaModel != null || motTyp != null || registracija != null || klijentId != null) {
			godiste = '%' + godiste + '%'  ;
			gorivo = '%' + gorivo + '%'  ;
			markaModel = '%' + markaModel + '%'  ;
			motTyp = '%' + motTyp + '%'  ;
			registracija = '%' + registracija + '%'  ;
			klijentId = '%' + klijentId + '%'  ;
		}
		
		return voziloRepository.search(godiste, gorivo, markaModel, motTyp, registracija, klijentId, new PageRequest(pageNum, 30));
	}

	@Override
	public Vozilo save(Vozilo vozilo) {
		return voziloRepository.save(vozilo);
	}

	@Override
	public Vozilo delete(Long id) {
		Vozilo vozilo = voziloRepository.findOne(id);
		if (vozilo != null) {
			voziloRepository.delete(vozilo);
		}
		return vozilo;
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
