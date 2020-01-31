package com.vektorwebsolutions.carservice.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.vektorwebsolutions.carservice.model.Vozilo;
import com.vektorwebsolutions.carservice.model.Klijent;
import com.vektorwebsolutions.carservice.service.VoziloService;
import com.vektorwebsolutions.carservice.service.KlijentService;
import com.vektorwebsolutions.carservice.web.dto.VoziloDTO;

@Component
public class VoziloDTOToVozilo implements Converter<VoziloDTO, Vozilo>{

	@Autowired
	private VoziloService voziloService;
	@Autowired
	private KlijentService klijentService;
	
	@Override
	public Vozilo convert(VoziloDTO voziloDTO) {
		
		Klijent klijent = klijentService.findOne(voziloDTO.getKlijentId());
		
		if(klijent != null) {
			Vozilo vozilo = null;
			
			if(voziloDTO.getId() != null) {
				vozilo = voziloService.findOne(voziloDTO.getId());
			}
			else {
				vozilo = new Vozilo();
			}
			
			vozilo.setGodiste(voziloDTO.getGodiste());
			vozilo.setGorivo(voziloDTO.getGorivo());
			vozilo.setMarkaModel(voziloDTO.getMarkaModel());
			vozilo.setMotTyp(voziloDTO.getMotTyp());
			vozilo.setRegistracija(voziloDTO.getRegistracija());
			
			vozilo.setKlijent(klijent);
			
			return vozilo;
			
		} else {
			throw new IllegalStateException("Pokušaj dodavanja na nepostojeći entitet.");
		}
		
	}

	public List<Vozilo> convert(List<VoziloDTO> voziloDTOs){
		List<Vozilo> ret = new ArrayList<>();
		
		for(VoziloDTO voziloDTO : voziloDTOs) {
			ret.add(convert(voziloDTO));
		}
		return ret;
	}
}
