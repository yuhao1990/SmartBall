package com.lovnx.service;

import com.lovnx.model.ws.User;

public interface UserService {
	
	public User findUserByName(String name);
	
	public User findUser(String name, String password);

	public User save(User user);

	public void delete(Integer id);

}
