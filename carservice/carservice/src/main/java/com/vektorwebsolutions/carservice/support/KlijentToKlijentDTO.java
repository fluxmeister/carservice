package com.vektorwebsolutions.carservice.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.vektorwebsolutions.carservice.model.Klijent;
import com.vektorwebsolutions.carservice.web.dto.KlijentDTO;

@Component
public class KlijentToKlijentDTO implements Converter<Klijent, KlijentDTO>{

	@Override
	public KlijentDTO convert(Klijent klijent) {
		if(klijent == null) {
			return null;
		}
		KlijentDTO dto = new KlijentDTO();
		
		dto.setId(klijent.getId());
		dto.setIme(klijent.getIme());
		dto.setPrezime(klijent.getPrezime());
		dto.setTelefon(klijent.getTelefon());
		
		return dto;
	}
	
	public List<KlijentDTO> convert(List<Klijent> klijenti){
		List<KlijentDTO> dtoList = new ArrayList<>();
		
		for(Klijent klijent: klijenti) {
			dtoList.add(convert(klijent));
		}
		return dtoList;
	}

}
