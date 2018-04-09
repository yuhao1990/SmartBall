package com.lovnx.dao.ws;

import com.lovnx.model.ws.Score;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScoreJpaDao extends JpaRepository<Score, Integer> {

	
}
