package com.ssafy.web.util;

import java.io.UnsupportedEncodingException;

import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.extensions.compactnotation.CompactConstructor;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/*
 * 로그인을 위한 토큰을 설정하는 클래스 입니다
*/
@Component
public class JWTUtil {
	private static final String SALT = "HEALTH";
	
	public String createToken(String claimId, String data) throws Exception {
		return Jwts.builder()
				.setHeaderParam("alg", "HS256")
				.setHeaderParam("typ", "JWT")
				.claim(claimId, data)
				.signWith(SignatureAlgorithm.HS256, SALT.getBytes("UTF-8"))
				.compact();
	}
	//유효성 검사
	public void valid(String token) throws Exception {
		Jwts.parser().setSigningKey(SALT.getBytes("UTF-8")).parseClaimsJws(token);
	}
}
