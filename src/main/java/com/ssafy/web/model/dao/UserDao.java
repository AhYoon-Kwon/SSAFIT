package com.ssafy.web.model.dao;

import java.util.List;

import com.ssafy.web.model.dto.User;

public interface UserDao{
	//회원가입
	void insert(User user);
	//회원정보수정
	void update(User user);
	//회원탈퇴
	void delete(int id);
	//아이디로 유저 찾기
	User selectOneById(String userid);
	//이메일로 유저 찾기
	User selectOneByEmail(String email);
	//유저아이디로 id 찾기
	int selectIdByUserid(String userid);
	//아이디 중복 체크
	int idDuplicateCheck(String userid);
	//이메일 중복 체크
	int emailDuplicateCheck(String email);
}