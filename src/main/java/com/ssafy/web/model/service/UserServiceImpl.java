package com.ssafy.web.model.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import com.ssafy.web.model.dao.UserDao;
import com.ssafy.web.model.dto.User;

@Service("userService")
public class UserServiceImpl implements UserService{
	@Autowired
	private UserDao userDao;
	
	//아이디 중복 체크
	//1이면 중복으로 아이디 사용 불가, 아니라면 사용 가능
	@Override
	public int idDuplicateCheck(String userid) {
		return userDao.idDuplicateCheck(userid);
	}

	//이메일 중복 체크
	//1이면 중복으로 이메일 사용 불가, 아니라면 사용 가능
	@Override
	public int emailDuplicateCheck(String email) {
		return userDao.emailDuplicateCheck(email);
	}
	
	// 회원가입 시, 유효성 체크
	@Transactional(readOnly = true)
	@Override
	public Map<String, String> validateHandling(Errors errors) {
		Map<String, String> validatorResult = new HashMap<>();
		// 유효성 검사에 실패한 필드 목록을 받음 
		for (FieldError error : errors.getFieldErrors()) {
			String validKeyName = String.format("valid_%s", error.getField());
			validatorResult.put(validKeyName, error.getDefaultMessage());
		}
		return validatorResult;
	}

	//회원가입
	@Override
	public void join(User user) {
		//비밀번호 암호화해서 저장
		userDao.insert(user);
	}

	@Override
	public int login(String userId, String pw) {
		//존재하지 않는 아이디로 로그인 하거나
		//아이디는 존재하지만 비밀번호가 틀릴 경우 로그인 실패
		if(userDao.selectOne(userId) == null || userDao.selectOne(userId).getPw() != pw)
			return 0;
		return 1;
	}

	@Override
	public void changeUserInfo(User newUser) {
		userDao.update(newUser);
	}

	@Override
	public String findId(String email) {
		return null;
	}

	@Override
	public void singOut(String userid, String pw) {
		//아이디 존재하는지 확인
		
		//비밀번호 맞는지 확인
		
		//회원탈퇴
		userDao.delete(userid);
	}

	@Override
	public User changePw(String userid, String email) {
		//아이디 존재하는지 확인
		
		//이메일 맞는지 확인
		
		//새 비밀번호 저장하기
		
		return null;
	}


}
