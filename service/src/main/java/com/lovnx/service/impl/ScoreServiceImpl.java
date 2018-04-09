package com.lovnx.service.impl;

import com.lovnx.dao.ws.ScoreJpaDao;
import com.lovnx.model.ws.Score;
import com.lovnx.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScoreServiceImpl implements ScoreService {
	
	@Autowired
	private ScoreJpaDao sjd;

	@Override
	public List<Score> findAll() {
		return sjd.findAll();
	}

	@Override
	public Score save(Score score) {
		return sjd.save(score);
	}

	@Override
	public void delete(Integer id) {
		sjd.delete(id);
	}
	

}
