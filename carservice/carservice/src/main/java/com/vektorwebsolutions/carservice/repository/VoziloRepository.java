package com.vektorwebsolutions.carservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vektorwebsolutions.carservice.model.Vozilo;

@Repository
public interface VoziloRepository extends JpaRepository<Vozilo, Long> {
	
	@Query("SELECT v FROM Vozilo v WHERE "
			+ "(:godiste IS NULL or v.godiste LIKE :godiste) AND "
			+ "(:gorivo IS NULL OR v.gorivo LIKE :gorivo) AND "
			+ "(:markaModel IS NULL OR v.markaModel LIKE :markaModel) AND "
			+ "(:motTyp IS NULL OR v.motTyp LIKE :motTyp) AND "
			+ "(:registracija IS NULL or v.registracija LIKE :registracija) AND "
			+ "(:klijentId IS NULL or v.klijent.id = :klijentId)"
			)
	Page<Vozilo> search(
			@Param("godiste") Integer godiste,
			@Param("gorivo") String gorivo,
			@Param("markaModel") String markaModel,
			@Param("motTyp") String motTyp, 
			@Param("registracija") String registracija, 
			@Param("klijentId") Long klijentId, 
			Pageable pageRequest);
}
