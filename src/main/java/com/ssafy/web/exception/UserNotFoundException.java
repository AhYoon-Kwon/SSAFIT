package com.ssafy.web.exception;

public class UserNotFoundException extends Exception{
	public UserNotFoundException() {
		super("사용자를 찾을 수 없습니다.");
	}
}
