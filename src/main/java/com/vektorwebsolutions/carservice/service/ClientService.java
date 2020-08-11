package com.vektorwebsolutions.carservice.service;

import org.springframework.data.domain.Page;

import com.vektorwebsolutions.carservice.model.Client;

public interface ClientService {
	Client findOne(Long id);
	Page<Client> findAll(int pageNum);
	Page<Client> search(String name, String surname, String phone, int pageNum);
	Client save(Client client);
	Client delete(Long id);
}
