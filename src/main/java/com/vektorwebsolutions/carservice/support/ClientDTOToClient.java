package com.vektorwebsolutions.carservice.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.vektorwebsolutions.carservice.model.Client;
import com.vektorwebsolutions.carservice.service.ClientService;
import com.vektorwebsolutions.carservice.web.dto.ClientDTO;

@Component
public class ClientDTOToClient implements Converter<ClientDTO, Client> {

	@Autowired
	private ClientService clientService;

	@Override
	public Client convert(ClientDTO clientDTO) {
		
		Client client = null;
		
		
		if(clientDTO.getId() != null) {
			client = clientService.findOne(clientDTO.getId());
		} else {
			client = new Client();
		}
		
		client.setName(clientDTO.getName());
		client.setSurname(clientDTO.getSurname());
		client.setPhone(clientDTO.getPhone());
		
		return client;
		
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

	public List<Client> convert(List<ClientDTO> clientDTOs){
		List<Client> ret = new ArrayList<>();
		
		for(ClientDTO klijentDTO : clientDTOs) {
			ret.add(convert(klijentDTO));
		}
		
		return ret;
		
	}

}

