package com.ssafy.web.model.dto;

import java.util.Collection;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import io.swagger.annotations.ApiModel;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "UserDTO", description="유저 정보를 표현합니다.")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder //user 객체를 생성할 때 가독성이 좋게 코드를 짤 수 있다.
public class User {
	
	private int id;
	private String userId;
	private String nickName;
	private String pw;
	private String email;
}
