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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.web.exception.UserNotFoundException;
import com.ssafy.web.exception.WrongInfoException;
import com.ssafy.web.model.dto.User;
import com.ssafy.web.model.service.UserService;
import com.ssafy.web.util.JWTUtil;

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
	@GetMapping("/join/id/{userid}")
	public ResponseEntity<String> idDuplicateCheck(@PathVariable String userid) throws Exception{
		//DB에서 아이디로 검색했을 때 값이 나오면 중복된 아이디
		if(userService.idDuplicateCheck(userid)==1) {
			throw new DuplicateMemberException("중복된 아이디 입니다.");
		}
		//아무것도 찾을 수 없다면 중복 검사 통과
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
	
	//이메일 중복 체크
	@GetMapping("/join/email/{email}")
	public ResponseEntity<String> emailDuplicateCheck(@PathVariable String email) throws Exception{
		//DB에서 이메일로 검색했을 때 값이 나오면 중복된 이메일
		if(userService.emailDuplicateCheck(email)==1)
			throw new DuplicateMemberException("중복된 이메일 입니다.");
		//아무것도 찾을 수 없다면 중복 검사 통과
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
	
	// 회원가입
	@PostMapping("/join")
	public ResponseEntity<String> join(User user) throws Exception {
		userService.join(user);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
	
	//로그인
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(User user) throws Exception{
		HttpStatus status = null;
		
		HashMap<String, Object> result = new HashMap<>();
		System.out.println(userService.login(user.getUserid(), user.getPw()));
		try {
			//user 정보를 이용하여 데이터베이스 확인
			//존재하면 토큰을 생성해서 결과에 넣어 반환
			if(userService.login(user.getUserid(), user.getPw()) == 1) {
				result.put("access-token", jwtUtil.createToken("userid", user.getUserid()));
				result.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			}else {
				result.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		}catch (Exception e) {
			result.put("message", FAIL);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String,Object>>(result, status);
	}
	
	//회원탈퇴
	//회원탈퇴하고 리뷰 삭제하는 거 추가해야함
	@DeleteMapping("/{userid}")
	public ResponseEntity<String> delete(@PathVariable String userid, String pw) throws Exception{
		if(userService.singOut(userid, pw) == 1) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	//아이디 찾기
	@GetMapping("/find-id")
	public ResponseEntity<String> findId(String nickname, String email) throws Exception{
		//입력된 이메일로 정보 조회
		String userid = userService.findId(email);
		//이메일로 아이디 찾을 수 없으면 예외처리
		if(userid == null)
			throw new UserNotFoundException();
		//아이디 찾을 수 있으면 닉네임 동일한지 검사
		User user = userService.selectOneById(userid);
		//동일하지 않으면 예외 처리
		if(!user.getNickname().equals(nickname))
			throw new WrongInfoException("닉네임이");
		return new ResponseEntity<String>(userid, HttpStatus.OK);
	}
	
	//비밀번호 재설정 자격 검증 페이지
	@GetMapping("/change-pw/auth")
	public ResponseEntity<String> changPwAuth(User user) throws Exception {
		//아이디, 닉네임, 이메일을 입력받고 동일한지 검사
		User member = userService.selectOneById(user.getUserid());
		//아이디로 멤버 찾을 수 없으면 예외 처리
		if(member == null)
			throw new UserNotFoundException();
		//닉네임이 동일하지 않으면 예외 처리
		if(!member.getNickname().equals(user.getNickname()))
			throw new WrongInfoException("닉네임이");
		//이메일이 동일하지 않으면 예외 처리
		if(!member.getEmail().equals(user.getEmail()))
			throw new WrongInfoException("이메일이");
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
	
	//비밀번호 재설정
	@PutMapping("/change-pw/{userid}")
	public ResponseEntity<String> changePw(@PathVariable String userid, String pw) throws Exception{
		userService.changePw(userid, pw);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
	
	//회원 정보 수정
	@PutMapping("/info/{userid}")
	public ResponseEntity<String> update(User newUser, @PathVariable String userid){
		userService.changeUserInfo(newUser, userid);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
}
