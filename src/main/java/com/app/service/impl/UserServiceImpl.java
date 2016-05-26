package com.app.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.app.dao.UserMapper;
import com.app.pojo.User;
import com.app.service.IUserService;

@Service("userService")
public class UserServiceImpl implements IUserService {
	@Resource
	private UserMapper userDao;

	@Override
	public User getUserById(int userId){
		return this.userDao.selectByPrimaryKey(userId);
	}

	@Override
	public int Login(String userName, String password) {
		return this.userDao.Login(userName,password);
	}
	
	@Override
	public int insert(User user){
		return this.userDao.insert(user);
	}

}
