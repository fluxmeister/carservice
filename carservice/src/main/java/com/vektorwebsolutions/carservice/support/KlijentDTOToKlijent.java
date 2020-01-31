package com.vektorwebsolutions.carservice.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.vektorwebsolutions.carservice.model.Klijent;
import com.vektorwebsolutions.carservice.service.KlijentService;
import com.vektorwebsolutions.carservice.web.dto.KlijentDTO;

@Component
public class KlijentDTOToKlijent implements Converter<KlijentDTO, Klijent> {

	@Autowired
	private KlijentService klijentService;

	@Override
	public Klijent convert(KlijentDTO klijentDTO) {
		
		Klijent klijent = null;
		
		
		if(klijentDTO.getId() != null) {
			klijent = klijentService.findOne(klijentDTO.getId());
		} else {
			klijent = new Klijent();
		}
		
		klijent.setIme(klijentDTO.getIme());
		klijent.setPrezime(klijentDTO.getPrezime());
		klijent.setTelefon(klijentDTO.getTelefon());
		
		return klijent;
		
	}
//
//		if (klijentDTO.getKlijentDtoId() != null) {
//			klijent = klijentService.findOne(klijentDTO.getKlijentDtoId());
//		} else {
//			klijent = new Klijent();
//		}
//
//		klijent.setIme(klijentDTO.getKlijentDtoIme());
//		klijent.setPrezime(klijentDTO.getKlijentDtoPrezime());
//		klijent.setTelefon(klijentDTO.getKlijentDtoTelefon());
//			
//			
//			return klijent;
//		} else {
//			throw new IllegalStateException("Pokušaj dodavanja na nepostojeći entitet.");
//		}

	public List<Klijent> convert(List<KlijentDTO> klijentDTOs){
		List<Klijent> ret = new ArrayList<>();
		
		for(KlijentDTO klijentDTO : klijentDTOs) {
			ret.add(convert(klijentDTO));
		}
		
		return ret;
		
	}

}

