package com.ssafy.web.util;

import java.util.Base64;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

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
	
	//JWT 토큰 생성
	public String createToken(String claimId, String data) throws Exception {
		Date now = new Date();
		return Jwts.builder()
				.setHeaderParam("alg", "HS256")
				.setHeaderParam("typ", "JWT")
				.claim(claimId, data)
				.setExpiration(new Date(now.getTime() + tokenValidTime)) //토큰 유효시간 설정
				.signWith(SignatureAlgorithm.HS256, SALT.getBytes("UTF-8"))
				.compact();
	}
	
	// JWT 토큰에서 인증 정보 조회
//    public Authentication getAuthentication(String token) throws Exception {
//        UserDetails userDetails = userService.selectOneById(this.getUserPk(token));
//        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
//    }

    // 토큰에서 회원 정보 추출
    public String getUserPk(String token) {
        return Jwts.parser().setSigningKey(SALT).parseClaimsJws(token).getBody().getSubject();
    }

    // Request의 Header에서 token 값을 가져옵니다. "Authorization" : "TOKEN값'
    public String resolveToken(HttpServletRequest request) {
        return request.getHeader("Authorization");
    }

    // 토큰의 유효성 + 만료일자 확인
    public boolean validateToken(String jwtToken) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(SALT).parseClaimsJws(jwtToken);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }
}
