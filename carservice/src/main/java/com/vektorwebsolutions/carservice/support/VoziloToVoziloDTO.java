package com.vektorwebsolutions.carservice.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.vektorwebsolutions.carservice.model.Vozilo;
import com.vektorwebsolutions.carservice.web.dto.VoziloDTO;

@Component
public class VoziloToVoziloDTO implements Converter<Vozilo, VoziloDTO>{

	@Override
	public VoziloDTO convert(Vozilo vozilo) {
		if(vozilo == null) {
			return null;
		}
		VoziloDTO dto = new VoziloDTO();
		
		dto.setVoziloDtoId(vozilo.getVoziloId());
		dto.setGodiste(vozilo.getGodiste());
		dto.setGorivo(vozilo.getGorivo());
		dto.setMarkaModel(vozilo.getMarkaModel());
		dto.setMotTyp(vozilo.getMotTyp());
		dto.setRegistracija(vozilo.getRegistracija());
		
		dto.setKlijentId(vozilo.getKlijent().getId());
		dto.setKlijentIme(vozilo.getKlijent().getIme());
		
		return dto;
	}
	
	public List<VoziloDTO> convert(List<Vozilo> vozila){
		List<VoziloDTO> dtoList = new ArrayList<>();
		
		for(Vozilo vozilo: vozila) {
			dtoList.add(convert(vozilo));
		}
		return dtoList;
	}
	
	
}
