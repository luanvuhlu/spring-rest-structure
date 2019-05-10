package com.luanvv.spring.springstructure.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.luanvv.spring.springstructure.entities.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

	@Query("FROM Stock WHERE uuid = ?1 ")
	Optional<Stock> findByUuid(UUID uuid);
	
	List<Stock> findAll();
	
}
