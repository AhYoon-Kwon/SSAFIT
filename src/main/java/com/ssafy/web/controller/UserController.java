package com.ssafy.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.javassist.bytecode.DuplicateMemberException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	//아이디 중복 체크
	@GetMapping("/join/iCk/{id}")
	public ResponseEntity<String> idDuplicateCheck(@PathVariable String id) throws Exception{
		//DB에서 아이디로 검색했을 때 값이 나오면 중복된 아이디
		if(userService.idDuplicateCheck(id)==1) {
			throw new DuplicateMemberException("중복된 아이디 입니다.");
		}
		//아무것도 찾을 수 없다면 중복 검사 통과
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
	
	//이메일 중복 체크
	@GetMapping("/join/eCk/{email}")
	public ResponseEntity<String> emailDuplicateCheck(@PathVariable String email) throws Exception{
		//DB에서 이메일로 검색했을 때 값이 나오면 중복된 이메일
		if(userService.emailDuplicateCheck(email)==1)
			throw new DuplicateMemberException("중복된 이메일 입니다.");
		//아무것도 찾을 수 없다면 중복 검사 통과
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
	
	// 회원가입
	@PostMapping("/join/auth")
	public ResponseEntity<String> join(@RequestBody User user) throws Exception {
		userService.join(user);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
	
	//로그인
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(@RequestBody User user){
		HttpStatus status = null;
		
		HashMap<String, Object> result = new HashMap<>();
		try {
			//user 정보를 이용하여 데이터베이스 확인
			//존재하면 토큰을 생성해서 결과에 넣어 반환
			if(userService.login(user.getUserid(), user.getPw()) == 1) {
				result.put("access-token", jwtUtil.createToken("id", user.getUserid()));
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
	
	//회원탈퇴
	@DeleteMapping("/signOut/{id}")
	public ResponseEntity<String> delete(@PathVariable String userid) throws Exception{
		if(userService.singOut(userid) == 1) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
}
