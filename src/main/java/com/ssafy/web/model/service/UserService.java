package com.ssafy.web.model.service;

import com.ssafy.web.model.dto.User;

public interface UserService {
	//회원가입
	void join(User user);
	//로그인
	User login(String userId, String pw);
	//비밀번호 변경
	void changePassword(String oldPw, String newPw);
	//기타 회원정보 수정
	void changeUserInfo(User oldUser, User newUser);
	//아이디 찾기
	String findId(String email);
	//비밀번호 재설정
	User changePw(String userid, String email);
	//회원 탈퇴
	void singOut(String userid, String pw);
}
