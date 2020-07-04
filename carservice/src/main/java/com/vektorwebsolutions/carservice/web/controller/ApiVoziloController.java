package com.vektorwebsolutions.carservice.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vektorwebsolutions.carservice.model.Vozilo;
import com.vektorwebsolutions.carservice.service.VoziloService;
import com.vektorwebsolutions.carservice.support.VoziloDTOToVozilo;
import com.vektorwebsolutions.carservice.support.VoziloToVoziloDTO;
import com.vektorwebsolutions.carservice.web.dto.VoziloDTO;

@RestController
@RequestMapping(value="/api/vozila")
public class ApiVoziloController {
	
	@Autowired
	private VoziloService voziloService;
	
	@Autowired
	private VoziloToVoziloDTO toDTO;
	
	@Autowired
	private VoziloDTOToVozilo toVozilo;
	
	@RequestMapping(method=RequestMethod.GET)
	ResponseEntity<List<VoziloDTO>> getVozila(
			@RequestParam(required=false) Integer godiste,
			@RequestParam(required=false) String gorivo,
			@RequestParam(required=false) String markaModel, 
			@RequestParam(required=false) String motTyp, 
			@RequestParam(required=false) String registracija, 
			@RequestParam(required=false) Long klijentId, 
			@RequestParam(value="pageNum", defaultValue="0") int pageNum ){
				Page<Vozilo> vozilaPage = null;
				
				if(registracija != null || klijentId != null || gorivo != null) {
					vozilaPage = voziloService.search(godiste, gorivo, markaModel, motTyp, registracija, klijentId, pageNum);
				}
				else {
					vozilaPage = voziloService.findAll(pageNum);
				}
				
				HttpHeaders headers = new HttpHeaders();
				headers.add("totalPages", Integer.toString(vozilaPage.getTotalPages()));
				
				return new ResponseEntity<>(
						toDTO.convert(vozilaPage.getContent()), 
						headers, 
						HttpStatus.OK);
			}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	ResponseEntity<VoziloDTO> getVozilo(@PathVariable Long id){
		Vozilo vozilo = voziloService.findOne(id);
		if(vozilo == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
			return new ResponseEntity<>(
					toDTO.convert(vozilo),
					HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	ResponseEntity<VoziloDTO> delete(@PathVariable Long id){
		Vozilo deleted = voziloService.delete(id);
		
		if(deleted == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(
				toDTO.convert(deleted),
				HttpStatus.OK);
	}

	
	@RequestMapping(method=RequestMethod.POST, 
					consumes="application/json")
	public ResponseEntity<VoziloDTO> add(
			@Validated @RequestBody VoziloDTO newVoziloDTO){
		
		Vozilo savedVozilo = voziloService.save(toVozilo.convert(newVoziloDTO));
		
		return new ResponseEntity<>(
				toDTO.convert(savedVozilo), HttpStatus.CREATED);
	}
	
	
	@RequestMapping(method=RequestMethod.PUT, 
			value="/{id}", 
			consumes="application/json")
	public ResponseEntity<VoziloDTO> edit(
			@Validated @RequestBody VoziloDTO voziloDTO, 
			@PathVariable Long id){
		
		if( !id.equals(voziloDTO.getId()) ) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Vozilo persisted = voziloService.save(toVozilo.convert(voziloDTO));
		
		return new ResponseEntity<>(toDTO.convert(persisted), HttpStatus.OK);
		
	}

	
	@ExceptionHandler(value=DataIntegrityViolationException.class)
	public ResponseEntity<Void> handle(){
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
}
