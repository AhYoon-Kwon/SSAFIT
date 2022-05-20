package com.ssafy.web.model.service;

import java.util.Map;

import org.springframework.validation.Errors;

import com.ssafy.web.model.dto.User;

public interface UserService {
	//아이디 중복 체크
	int idDuplicateCheck(String userid);
	//이메일 중복 체크
	int emailDuplicateCheck(String email);
	//회원가입
	void join(User user) throws Exception;
	//로그인
	int login(String userId, String pw) throws Exception;
	//회원정보 수정
	void changeUserInfo(User newUser, String userid);
	//아이디 찾기
	String findId(String email) throws Exception;
	//비밀번호 재설정
	void changePw(String userid, String newPw) throws Exception;
	//회원 탈퇴
	int singOut(String userid, String pw) throws Exception;
	//아이디로 회원 불러오기
	User selectOneById(String userid) throws Exception;
}
