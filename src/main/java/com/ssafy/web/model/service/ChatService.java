package com.ssafy.web.model.service;

import java.util.List;

import com.ssafy.web.model.dto.Chat;

public interface ChatService {
	
	void insertChat(Chat chat);
	
	void deleteChat(int id);
	
	List<Chat> showChat();
	
	List<Chat> showChatById(int id);
}
