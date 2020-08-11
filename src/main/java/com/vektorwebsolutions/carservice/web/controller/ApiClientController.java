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

import com.vektorwebsolutions.carservice.model.Client;
import com.vektorwebsolutions.carservice.service.ClientService;
import com.vektorwebsolutions.carservice.support.ClientDTOToClient;
import com.vektorwebsolutions.carservice.support.ClientToClientDTO;
import com.vektorwebsolutions.carservice.web.dto.ClientDTO;

@RestController
@RequestMapping(value="/api/clients")
public class ApiClientController {
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private ClientToClientDTO toDTO;
	
	@Autowired
	private ClientDTOToClient toClient;
	
	@RequestMapping(method=RequestMethod.GET)
	ResponseEntity<List<ClientDTO>> getClients(
			@RequestParam(required=false) String name, 
			@RequestParam(required=false) String surname, 
			@RequestParam(required=false) String phone, 
			@RequestParam(value="pageNum", defaultValue="0") int pageNum){
				Page<Client> clientsPage = null;
				
				if(name != null || surname != null ) {
					clientsPage = clientService.search(name, surname, phone, pageNum);
				} else {
					clientsPage = clientService.findAll(pageNum);
				}
				
				HttpHeaders headers = new HttpHeaders();
				headers.add("totalPages", Integer.toString(clientsPage.getTotalPages()));
				
				return new ResponseEntity<>(
						toDTO.convert(clientsPage.getContent()), 
						headers, 
						HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	ResponseEntity<ClientDTO> getClient(@PathVariable Long id) {
		Client client = clientService.findOne(id);
		if (client == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
			return new ResponseEntity<>(
					toDTO.convert(client), 
					HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	ResponseEntity<ClientDTO> delete(@PathVariable Long id){
		Client deleted = clientService.delete(id);
		
		if(deleted == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(
				toDTO.convert(deleted), 
				HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<ClientDTO> add(
			@Validated @RequestBody ClientDTO newClientDTO){
		
		Client savedClient = clientService.save(toClient.convert(newClientDTO));
		
		return new ResponseEntity<>(toDTO.convert(savedClient),
				HttpStatus.CREATED);
	}
	
	@RequestMapping(method=RequestMethod.PUT, 
			value="/{id}", 
			consumes="application/json")
	public ResponseEntity<ClientDTO> edit(
			@Validated @RequestBody ClientDTO klijentDTO, 
			@PathVariable Long id){
		if(!id.equals(klijentDTO.getId())) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Client persisted = clientService.save(
				toClient.convert(klijentDTO));
		
		return new ResponseEntity<>(
				toDTO.convert(persisted), 
				HttpStatus.OK);
	}
	
	@ExceptionHandler(value=DataIntegrityViolationException.class)
	public ResponseEntity<Void> handle(){
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
