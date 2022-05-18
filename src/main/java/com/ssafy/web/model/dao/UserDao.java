package com.ssafy.web.model.dao;

import java.util.List;

import com.ssafy.web.model.dto.User;

public interface UserDao{
	//회원가입
	void insert(User user);
	//회원정보수정
	void update(User user);
	//회원탈퇴
	void delete(String userid);
	//로그인
	User selectOne(String userid);
	//아이디 중복 체크
	int idDuplicateCheck(String userid);
	//이메일 중복 체크
	int emailDuplicateCheck(String email);
	
}