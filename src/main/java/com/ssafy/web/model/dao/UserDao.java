package com.ssafy.web.model.dao;

import java.util.List;

import com.ssafy.web.model.dto.User;

public interface UserDao{
	void insert(User user);
	void update(User user);
	void delete(String userId);
	User selectOne(String userId);
	List<User> selectAll();
}