//package com.vektorwebsolutions.carservice.support;
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
//public class RadniNalogToRadniNalogDTO implements Converter<RadniNalog, RadniNalogDTO> {
//	
//	@Override
//	public RadniNalogDTO convert(RadniNalog radniNalog) {
//		
//		if(radniNalog==null) {
//			return null;
//		}
//		
//		RadniNalogDTO dto = new RadniNalogDTO();
//		dto.setId(radniNalog.getId());
//		return dto;
//		
//	}
//	
//	public List<RadniNalogDTO> convert(List<RadniNalog> radniNalozi){
//		List<RadniNalogDTO> dtoList = new ArrayList<>();
//		
//		for(RadniNalog radniNalog: radniNalozi) {
//			dtoList.add(convert(radniNalog));
//		}
//		return dtoList;
//	}
//
//}
