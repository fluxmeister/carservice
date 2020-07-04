//package com.vektorwebsolutions.carservice.web.controller;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.core.convert.converter.Converter;
//import org.springframework.stereotype.Component;
//
//import com.vektorwebsolutions.carservice.model.RadniNalog;
//import com.vektorwebsolutions.carservice.web.dto.RadniNalogDTO;
//
//
//@Component
//public class RadniNalogToRadniNalogDTO  
//	implements Converter<RadniNalog, RadniNalogDTO>{
//
//	@Override
//	public RadniNalogDTO convert(RadniNalog radniNalog) {
//		if(radniNalog == null) {
//			return null;
//		}
//		
//		
//		RadniNalogDTO radniNalogDTO = new RadniNalogDTO();
//		radniNalogDTO.setId(radniNalog.getId());
//		
//		return radniNalogDTO;
//	}
//
//	public List<RadniNalogDTO> convert(List<RadniNalog> radniNalozi){
//		List<RadniNalogDTO> radniNalogDtoList = new ArrayList<>();
//		
//		for(RadniNalog radniNalog: radniNalozi) {
//			radniNalogDtoList.add(convert(radniNalog));
//		}
//		return radniNalogDtoList;
//	}
//	
//}
