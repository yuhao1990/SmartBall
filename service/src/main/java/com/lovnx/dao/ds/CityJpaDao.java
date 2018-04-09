package com.lovnx.dao.ds;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lovnx.model.ds.City;

//@RepositoryRestResource(path="city")
public interface CityJpaDao extends JpaRepository<City, Integer> {

	City findByIdOrName(Integer id, String name);
	
	List<City> findByNameLike(String name, Pageable page);
	
}
