package com.lovnx.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lovnx.dao.ds.CityJpaDao;
import com.lovnx.model.ds.City;
import com.lovnx.service.CityService;

@Service
public class CityServiceImpl implements CityService {
	
	//@Autowired
	private CityJpaDao cjd;

	@Override
	public City findByIdOrName(Integer id, String name) {
		return cjd.findByIdOrName(id, name);
	}

	@Override
	public City findCity(String name) {
		return null;
	}

	@Override
	public List<City> findAll() {
		return cjd.findAll();
	}

	@Override
	@Transactional
	public City save(City city) {
		return cjd.save(city);
	}

	@Override
	public void delete(Integer id) {
		cjd.delete(id);
	}
	
	public List<City> findByNameLike(String name, Pageable page){
		return cjd.findByNameLike(name, page);
	}

}
