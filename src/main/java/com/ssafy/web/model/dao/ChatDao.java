package com.ssafy.web.model.dao;

import java.util.List;

import com.ssafy.web.model.dto.Chat;

public interface ChatDao {
	
	List<Chat> selectAll();
	
	void insertChat(Chat chat);
	
	void deleteChat(int id);
}
