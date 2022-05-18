package com.ssafy.web.model.service;

import java.util.Map;

import org.springframework.validation.Errors;

import com.ssafy.web.model.dto.User;

public interface UserService {
	//아이디 중복 체크
	int idDuplicateCheck(String userid);
	//이메일 중복 체크
	int emailDuplicateCheck(String email);
	//유효성 검사
	public Map<String, String> validateHandling(Errors errors);
	//회원가입
	void join(User user);
	//로그인
	int login(String userId, String pw);
	//회원정보 수정
	void changeUserInfo(User newUser);
	//아이디 찾기
	String findId(String email);
	//비밀번호 재설정
	User changePw(String userid, String email);
	//회원 탈퇴
	void singOut(String userid, String pw);
}
