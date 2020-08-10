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

import com.vektorwebsolutions.carservice.model.Klijent;
import com.vektorwebsolutions.carservice.service.KlijentService;
import com.vektorwebsolutions.carservice.support.KlijentDTOToKlijent;
import com.vektorwebsolutions.carservice.support.KlijentToKlijentDTO;
import com.vektorwebsolutions.carservice.web.dto.KlijentDTO;

@RestController
@RequestMapping(value="/api/klijenti")
public class ApiKlijentController {
	
	@Autowired
	private KlijentService klijentService;
	
	@Autowired
	private KlijentToKlijentDTO toDTO;
	
	@Autowired
	private KlijentDTOToKlijent toKlijent;
	
	@RequestMapping(method=RequestMethod.GET)
	ResponseEntity<List<KlijentDTO>> getKlijenti(
			@RequestParam(required=false) String ime, 
			@RequestParam(required=false) String prezime, 
			@RequestParam(required=false) String telefon, 
			@RequestParam(value="pageNum", defaultValue="0") int pageNum){
				Page<Klijent> klijentiPage = null;
				
				if(ime != null || prezime != null ) {
					klijentiPage = klijentService.search(ime, prezime, telefon, pageNum);
				} else {
					klijentiPage = klijentService.findAll(pageNum);
				}
				
				HttpHeaders headers = new HttpHeaders();
				headers.add("totalPages", Integer.toString(klijentiPage.getTotalPages()));
				
				return new ResponseEntity<>(
						toDTO.convert(klijentiPage.getContent()), 
						headers, 
						HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	ResponseEntity<KlijentDTO> getKlijent(@PathVariable Long id) {
		Klijent klijent = klijentService.findOne(id);
		if (klijent == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
			return new ResponseEntity<>(
					toDTO.convert(klijent), 
					HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	ResponseEntity<KlijentDTO> delete(@PathVariable Long id){
		Klijent deleted = klijentService.delete(id);
		
		if(deleted == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(
				toDTO.convert(deleted), 
				HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<KlijentDTO> add(
			@Validated @RequestBody KlijentDTO newKlijentDTO){
		
		Klijent savedKlijent = klijentService.save(toKlijent.convert(newKlijentDTO));
		
		return new ResponseEntity<>(toDTO.convert(savedKlijent),
				HttpStatus.CREATED);
	}
	
	@RequestMapping(method=RequestMethod.PUT, 
			value="/{id}", 
			consumes="application/json")
	public ResponseEntity<KlijentDTO> edit(
			@Validated @RequestBody KlijentDTO klijentDTO, 
			@PathVariable Long id){
		if(!id.equals(klijentDTO.getId())) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Klijent persisted = klijentService.save(
				toKlijent.convert(klijentDTO));
		
		return new ResponseEntity<>(
				toDTO.convert(persisted), 
				HttpStatus.OK);
	}
	
	@ExceptionHandler(value=DataIntegrityViolationException.class)
	public ResponseEntity<Void> handle(){
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
