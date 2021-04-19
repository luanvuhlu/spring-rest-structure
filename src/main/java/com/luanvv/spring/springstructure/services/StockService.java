package com.luanvv.spring.springstructure.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.luanvv.spring.springstructure.entities.Stock;

/**
 * The Interface ClientCmsService.
 * @author luanv
 */
public interface StockService {

	/**
	 * Find one.
	 *
	 * @param stockId the stock id
	 * @return the stock
	 */
	Optional<Stock> findOne(long stockId);
	
	/**
	 * Find one.
	 *
	 * @param uuid the uuid
	 * @return the optional
	 */
	Optional<Stock> findOne(String uuid);

	/**
	 * Find paginated.
	 *
	 * @param page the page
	 * @param size the size
	 * @return the page
	 */
	Page<Stock> findPaginated(int page, int size);

	/**
	 * Find all.
	 *
	 * @return the list
	 */
	List<Stock> findAll();

	/**
	 * Delete by id.
	 *
	 * @param id the id
	 * @return true, if successful
	 */
	void delete(String id);

	/**
	 * Creates the.
	 *
	 * @param resource the resource
	 * @return the stock
	 */
	Stock create(Stock resource);

}
