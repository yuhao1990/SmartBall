package com.lovnx.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import com.lovnx.model.ds.City;

public interface CityService {

	City findByIdOrName(Integer id, String name);
	
	City findCity(@Param("name") String name);
	
	List<City> findAll();
	
	City save(City city);
	
	void delete(Integer id);
	
	List<City> findByNameLike(String name, Pageable page);
		
}
