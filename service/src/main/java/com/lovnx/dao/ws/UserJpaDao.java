package com.lovnx.dao.ws;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lovnx.model.ws.User;

public interface UserJpaDao extends JpaRepository<User, Integer> {
	
	User findUserByName(String name);
	
}
