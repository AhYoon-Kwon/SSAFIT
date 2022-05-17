package com.ssafy.web.model.service;

import com.ssafy.web.model.dto.User;

public interface UserService {
	void join(User user, String msg) throws Exception;
	User login(String id, String pw) throws Exception;
}
