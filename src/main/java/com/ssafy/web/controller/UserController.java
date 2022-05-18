package com.ssafy.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.web.model.dto.User;
import com.ssafy.web.model.service.UserService;
import com.ssafy.web.util.JWTUtil;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/user")
public class UserController {
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private JWTUtil jwtUtil;
	
	@Autowired
	private UserService userService;
	
	// 회원가입
	@PostMapping("/join/auth")
	public String joinValidateCheck(@Valid User user, Errors errors, Model model) {
		if (errors.hasErrors()) {
			//회원가입 실패시 입력 데이터 값을 유지
			model.addAttribute("User", user);
			//유효성 통과 못한 필드와 메시지를 핸들링
			Map<String, String> validatorResult = userService.validateHandling(errors);
			for (String key : validatorResult.keySet()) {
				model.addAttribute(key, validatorResult.get(key));
			}
			// 회원가입 페이지로 다시 리턴
			return "/user/join";
		}
		//유효성 검사 성공하면 로그인 페이지로 이동
		userService.join(user);
		return "redirect:/user/login";
	}
	
	//로그인
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(@RequestBody User user){
		HttpStatus status = null;
		
		HashMap<String, Object> result = new HashMap<>();
		try {
			//user 정보를 이용하여 데이터베이스 확인
			//존재하면 토큰을 생성해서 결과에 넣어 반환
			if(userService.login(user.getUserId(), user.getPw()) == 1) {
				result.put("access-token", jwtUtil.createToken("id", user.getUserId()));
				result.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			}else {
				result.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		}catch (Exception e) {
			result.put("message", FAIL);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			// TODO: handle exception
		}
		return new ResponseEntity<Map<String,Object>>(result, status);
	}
}
