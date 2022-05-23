package com.ssafy.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssafy.web.model.dto.Chat;
import com.ssafy.web.model.dto.User;
import com.ssafy.web.model.service.ChatService;
import com.ssafy.web.util.JWTUtil;

@Controller
@RequestMapping("/chat")
public class ChatController {
	
	static String SUCCESS = "SUCCESS";
	static String FAIL = "FAIL";
	
	@Autowired
	ChatService chatService;
	
	@Autowired
	JWTUtil jwtUtil;
	
	@GetMapping("/all")
	public ResponseEntity<List<Chat>> showAll() {
		
		List<Chat> chat = chatService.showChat();

		return new ResponseEntity<List<Chat>>(chat, HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<String> deleteChat(@PathVariable int id){
		
		chatService.deleteChat(id);
		
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		
	}
	
	@PostMapping("/insert")
	public ResponseEntity<String> insertChat(@RequestParam String content, HttpServletRequest req){
		String token = req.getHeader("access-token");
		
		User user;
		try {
			user = jwtUtil.getInfo(token);
			Chat chat = new Chat();
			chat.setContent(content);
			chat.setUid(user.getId());
			chat.setWriter(user.getNickname());
			chatService.insertChat(chat);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<String>(FAIL, HttpStatus.OK);
			
		}
		
		
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}

	
}
