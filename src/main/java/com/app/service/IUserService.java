package com.app.service;

import com.app.pojo.User;

public interface IUserService {
	public User getUserById(int userId);

	public int Login(String userName, String password);
	
	public int insert(User user);

	public int checkUsername(String string);
}
