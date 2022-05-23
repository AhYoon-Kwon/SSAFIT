package com.ssafy.web.util;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.web.model.dto.User;
import com.ssafy.web.model.service.UserService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/*
 * 로그인을 위한 토큰을 설정하는 클래스 입니다
*/
@Component
public class JWTUtil {
	@Autowired
	private UserService userService;
	
	private static String SALT = "HEALTH";
	
	//토큰 유효시간 30분
	private long tokenValidTime = 30 * 60 * 1000L;
	
	//JWT 토큰 생성(User 객체를 담아 전송)
	public String createToken(String claimId) throws Exception {
		Date now = new Date();
		User user = userService.selectOneById(claimId);
		return Jwts.builder()
				.setHeaderParam("alg", "HS256")
				.setHeaderParam("typ", "JWT")
				.claim("user", user) //토큰에 담기는 데이터
				.setExpiration(new Date(now.getTime() + tokenValidTime)) //토큰 유효시간 설정
				.setIssuedAt(new Date(now.getTime())) // 발급 시간 기록
				.signWith(SignatureAlgorithm.HS256, SALT.getBytes("UTF-8"))
				.setSubject("userToken") //토큰 제목
				.compact();
	}
	
	// 토큰에 담긴 정보를 가져오기
	public User getInfo(String token) throws Exception {
		Jws<Claims> claims = null;
		User user = null;
		try {
			claims = Jwts.parser().setSigningKey(SALT.getBytes())
					.parseClaimsJws(token); // SALT를 사용하여 복호화
			ObjectMapper obj = new ObjectMapper();
			user = obj.convertValue(claims.getBody().get("user"), User.class);
			
		} catch(Exception e) {
			throw new Exception();
		}
	
		
		return user;
	}
	
	// interceptor에서 토큰 유효성을 검증하기 위한 메서드
	public void checkValid(String token) {
		Jwts.parser().setSigningKey(SALT.getBytes()).parseClaimsJws(token);
	}

}
