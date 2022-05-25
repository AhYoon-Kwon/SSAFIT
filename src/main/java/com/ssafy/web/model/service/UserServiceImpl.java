package com.ssafy.web.model.service;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.aop.ThrowsAdvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import com.ssafy.web.exception.UserNotFoundException;
import com.ssafy.web.model.dao.ReviewDao;
import com.ssafy.web.model.dao.UserDao;
import com.ssafy.web.model.dto.User;
import com.ssafy.web.util.SHA256;

@Service("userService")
public class UserServiceImpl implements UserService{
	@Autowired
	private UserDao userDao;
	@Autowired
	private ReviewDao reviewDao;
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

	//회원가입
	@Transactional
	@Override
	public void join(User user) throws Exception {
		System.out.println(user.toString());
		//비밀번호 암호화해서 저장
		user.setPw( new SHA256().getHash(user.getPw()) );
		userDao.insert(user);
	}
	
	//로그인
	@Transactional
	@Override
	public int login(String userId, String pw) throws Exception {
		//동일한 알고리즘으로 암호화
		pw = new SHA256().getHash(pw);
		//존재하지 않는 아이디로 로그인 하거나
		//아이디는 존재하지만 비밀번호가 틀릴 경우 로그인 실패
		if(userDao.selectOneById(userId) == null || !userDao.selectOneById(userId).getPw().equals(pw))
			return 0;
		return 1;
	}
	
	//회원 정보 수정
	@Transactional
	@Override
	public void changeUserInfo(User newUser, String userid) {
		User user = userDao.selectOneById(userid);
		userDao.update(newUser);
	}
	
	//아이디 찾기
	@Override
	public String findId(String email) throws Exception {
		User user = userDao.selectOneByEmail(email);
		if(user == null)
			throw new UserNotFoundException();
		return user.getUserid();
	}
	
	//회원 탈퇴
	@Transactional
	@Override
	public int signOut(String userid) throws Exception {
		int id = userDao.selectIdByUserid(userid);
		reviewDao.userDelete(userid);
		userDao.delete(id);
		return 1;
	}
	
	//비밀번호 재설정
	@Transactional
	@Override
	public void changePw(String userid, String newPw) throws Exception {
		User user = userDao.selectOneById(userid);
		//새 비밀번호 저장하기
		user.setPw(new SHA256().getHash(newPw));
		userDao.update(user);
	}
	
	//userid로 id찾기
	@Override
	public User selectOneById(String userid) throws Exception {
		User user = userDao.selectOneById(userid);
		if(user == null)
			throw new UserNotFoundException();
		return user;
	}
	
}
