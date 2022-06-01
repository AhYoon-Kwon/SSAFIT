package com.ssafy.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ssafy.web.model.dao.UserDao;
import com.ssafy.web.model.dto.User;
import com.ssafy.web.model.service.UserService;

@SpringBootTest
class SsafitApplicationTests {

	@Autowired
	UserDao userDao;
	@Autowired
	UserService userService;
	
	@Test
	void contextLoads() {
		User user = userDao.selectOneById("hello");
		System.out.println(user.toString());
	}

}
