package com.ssafy.web.model.service;

import com.ssafy.web.model.dto.User;

public interface UserService {
	//회원가입
	void join(User user);
	//로그인
	User login(String userId, String pw);
	//비밀번호 변경
	void changePassword(String oldPw, String newPw);
	//프론트 계획 페이지 보니까 다른 회원정보 수정도 있길래 만들어봄
	void changeUserInfo(User oldUser, User newUser);
	//아이디 찾기
	String findId(String email);
	//비번 찾기
	//근데 hash로 저장하면 비밀번호 찾는 것보다
	//바로 비밀번호 변경 페이지로 넘어가는 게 나을지 고민중
	String findPw(String email, String id);
	//회원 탈퇴
	void singOut(String userId, String pw);
}
