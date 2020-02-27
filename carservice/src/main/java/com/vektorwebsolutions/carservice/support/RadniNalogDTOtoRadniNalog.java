package com.vektorwebsolutions.carservice.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import com.vektorwebsolutions.carservice.model.RadniNalog;
import com.vektorwebsolutions.carservice.web.dto.RadniNalogDTO;

public class RadniNalogDTOtoRadniNalog implements Converter<RadniNalogDTO, RadniNalog>{

	@Autowired
	private RadniNalogService radniNalogService;
	
	@Override
	public RadniNalog convert(RadniNalogDTO radniNalogDTO) {
		
		RadniNalog radniNalog = null;
		
		if(radniNalogDTO.getId() != null) {
			radniNalog = radniNalogService.findOne(radniNalogDTO.getId());
		}
		else {
			radniNalog = new RadniNalog();
		}
		
		radniNalog.setDatumRadnogNaloga(radniNalogDTO.getDatumRadnogNaloga());
		
	}

}
