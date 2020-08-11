package com.vektorwebsolutions.carservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vektorwebsolutions.carservice.model.Vehicle;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
	
	@Query("SELECT v FROM Vehicle v WHERE "
			+ "(:year IS NULL or v.year LIKE :year) AND "
			+ "(:fuel IS NULL OR v.fuel LIKE :fuel) AND "
			+ "(:brandMake IS NULL OR v.brandMake LIKE :brandMake) AND "
			+ "(:engine IS NULL OR v.engine LIKE :engine) AND "
			+ "(:plates IS NULL or v.plates LIKE :plates) AND "
			+ "(:clientId IS NULL or v.client.id = :clientId)"
			)
	Page<Vehicle> search(
			@Param("year") Integer year,
			@Param("fuel") String fuel,
			@Param("brandMake") String brandMake,
			@Param("engine") String engine, 
			@Param("plates") String plates, 
			@Param("clientId") Long clientId, 
			Pageable pageRequest);
}
