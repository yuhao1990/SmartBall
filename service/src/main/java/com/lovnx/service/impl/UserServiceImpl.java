package com.lovnx.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lovnx.dao.ws.UserJpaDao;
import com.lovnx.model.ws.User;
import com.lovnx.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	//@Autowired
	private UserJpaDao ujd;
	
	public User findUserByName(String name) {
		return ujd.findUserByName(name);
	}
	
	public User findUser(String name, String password) {
		return null;
	}

	public User save(User user) {
		return ujd.save(user);
	}

	public void delete(Integer id) {
		ujd.delete(id);
	}
	

}
