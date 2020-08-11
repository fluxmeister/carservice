package com.vektorwebsolutions.carservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vektorwebsolutions.carservice.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

	@Query("SELECT c FROM Client c WHERE "
			+ "(:name IS NULL or c.name LIKE :name ) AND"
			+ "(:surname IS NULL or c.surname LIKE :surname ) AND"
			+ "(:phone IS NULL or c.phone LIKE :phone )"
			)
	Page<Client> search(
			@Param("name") String name, 
			@Param("surname") String surname,
			@Param("phone") String phone, 
			Pageable pageRequest);
}
