package com.ssafy.web.model.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "UserDTO", description="유저 정보를 표현합니다.")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	private int id;
	private String UserId;
	private String pw;
	private String email;
	private String nickname;
}
