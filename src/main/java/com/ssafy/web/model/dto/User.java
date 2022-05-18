package com.ssafy.web.model.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

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
	
	@NotBlank(message = "아이디를 입력해주세요.")
	private String userId;
	
	@NotBlank(message = "닉네임을 입력해주세요.")
	@Pattern(regexp = "^[ㄱ-ㅎ가-힣a-z0-9-_]{2,8}$", message = "닉네임은 특수문자를 제외한 2~8자리여야 합니다.")
	private String nickName;
	
	@NotBlank(message = "비밀번호를 입력해주세요.")
	@Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}", message = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
	private String pw;
	
	@Pattern(regexp = "^(?:\\w+\\.?)*\\w+@(?:\\w+\\.)+\\w+$", message = "이메일 형식이 올바르지 않습니다.")
	@NotBlank(message = "이메일을 입력해주세요.")
	private String email;
}
