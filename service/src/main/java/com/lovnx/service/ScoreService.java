package com.lovnx.service;

import java.util.List;


import com.lovnx.model.ws.Score;

public interface ScoreService {

	List<Score> findAll();

	Score save(Score msg);
	
	void delete(Integer id);
	
		
}
