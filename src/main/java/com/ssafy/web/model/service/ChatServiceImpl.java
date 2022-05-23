package com.ssafy.web.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.web.model.dao.ChatDao;
import com.ssafy.web.model.dto.Chat;

@Service
public class ChatServiceImpl implements ChatService{

	@Autowired
	ChatDao chatDao;
	
	@Override
	public void insertChat(Chat chat) {
		// TODO Auto-generated method stub
		chatDao.insertChat(chat);
	}

	@Override
	public void deleteChat(int id) {
		// TODO Auto-generated method stub
		chatDao.deleteChat(id);
	}

	@Override
	public List<Chat> showChat() {
		// TODO Auto-generated method stub
		return chatDao.selectAll();
	}
	
	
}
