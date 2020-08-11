package com.vektorwebsolutions.carservice.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.vektorwebsolutions.carservice.model.Client;
import com.vektorwebsolutions.carservice.repository.ClientRepository;
import com.vektorwebsolutions.carservice.service.ClientService;

@Service
@Transactional
public class JpaClientService implements ClientService {

	@Autowired
	private ClientRepository clientRepository;
	
	@Override
	public Client findOne(Long id) {
		return clientRepository.findOne(id);
	}

	@Override
	public Page<Client> findAll(int pageNum) {
		return clientRepository.findAll(
				new PageRequest(pageNum, 30));
	}
	
	@Override
	public Page<Client> search(String name, String surname, String phone, int page) {
		if( name != null || surname != null || phone !=null  ) {
			name = '%' + name + '%' ;
			surname = '%' + surname + '%' ;
			phone = '%' + phone + '%' ;
		}
		return clientRepository.search(name, surname, phone, new PageRequest(page, 30));
	}
	@Override
	public Client save(Client client) {
		return clientRepository.save(client);
	}

	@Override
	public Client delete(Long id) {
		Client client = clientRepository.findOne(id);
		if(client != null) {
			clientRepository.delete(client);
		}
		return client;
	}



}
