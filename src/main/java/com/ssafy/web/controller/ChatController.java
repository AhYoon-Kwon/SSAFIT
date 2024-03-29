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
	
	@GetMapping("/all/{id}")
	public ResponseEntity<List<Chat>> showAll(@PathVariable int id) {
		
		List<Chat> chat = chatService.showChatById(id);

		return new ResponseEntity<List<Chat>>(chat, HttpStatus.OK);
	}
	
	@GetMapping("/recent")
	public ResponseEntity<Integer> getRecentId() {
		List<Chat> chat = chatService.showChat();
		if(chat == null)
			return new ResponseEntity<Integer>(0, HttpStatus.OK);
		
		int id = chat.size();
		
		return new ResponseEntity<Integer>(id, HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<String> deleteChat(@PathVariable int id){
		
		chatService.deleteChat(id);
		
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		
	}
	
	@PostMapping("/insert")
	public ResponseEntity<String> insertChat(@RequestParam String content, @RequestParam int type, HttpServletRequest req){
		String token = req.getHeader("access-token");
		
		User user;
		try { 
			user = jwtUtil.getInfo(token);
			System.out.println(user);
			Chat chat = new Chat();
			chat.setContent(content);
			chat.setUid(user.getId());
			chat.setWriter(user.getNickname());
			chat.setType(type);
			if(type == 1 && content.equals("입장")) {
				chat.setContent("입장하셨습니다");
				chatService.insertChat(chat);
				List<Chat> chats = chatService.showChat();
				if(chats == null)
					return new ResponseEntity<String>("0", HttpStatus.OK);
				
				int id = chats.size();
				return new ResponseEntity<String>(Integer.toString(id), HttpStatus.OK);
				
			}
			else if(type == 1 && content.equals("퇴장")){
				chat.setContent("퇴장하셨습니다");
			}
			
			chatService.insertChat(chat);
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<String>(FAIL, HttpStatus.OK);
			
		}
		
		
	}
	
	

	
}
