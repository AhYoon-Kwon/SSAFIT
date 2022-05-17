package com.ssafy.web.model.dao;

import com.ssafy.web.model.dto.User;

public interface UserDao{
	void insertUser(User user);
	User selectById(String id);
}