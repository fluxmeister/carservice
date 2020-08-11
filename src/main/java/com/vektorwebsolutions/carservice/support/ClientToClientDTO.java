package com.vektorwebsolutions.carservice.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.vektorwebsolutions.carservice.model.Client;
import com.vektorwebsolutions.carservice.web.dto.ClientDTO;

@Component
public class ClientToClientDTO implements Converter<Client, ClientDTO>{

	@Override
	public ClientDTO convert(Client client) {
		if(client == null) {
			return null;
		}
		ClientDTO dto = new ClientDTO();
		
		dto.setId(client.getId());
		dto.setName(client.getName());
		dto.setSurname(client.getSurname());
		dto.setPhone(client.getPhone());
		
		return dto;
	}
	
	public List<ClientDTO> convert(List<Client> clients){
		List<ClientDTO> dtoList = new ArrayList<>();
		
		for(Client client: clients) {
			dtoList.add(convert(client));
		}
		return dtoList;
	}

}
