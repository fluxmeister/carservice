package com.vektorwebsolutions.carservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vektorwebsolutions.carservice.model.Klijent;

@Repository
public interface KlijentRepository extends JpaRepository<Klijent, Long> {

	@Query("SELECT k FROM Klijent k WHERE "
			+ "(:ime IS NULL or k.ime LIKE :ime ) AND"
			+ "(:prezime IS NULL or k.prezime LIKE :prezime ) AND"
			+ "(:telefon IS NULL or k.telefon LIKE :telefon )"
			)
	Page<Klijent> search(
			@Param("ime") String ime, 
			@Param("prezime") String prezime,
			@Param("telefon") String telefon, 
			Pageable pageRequest);
}
