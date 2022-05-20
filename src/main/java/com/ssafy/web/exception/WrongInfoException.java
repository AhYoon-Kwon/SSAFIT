package com.ssafy.web.exception;

public class WrongInfoException extends Exception{
	public WrongInfoException(String msg) {
		super(msg+" 일치하지 않습니다.");
	}
}
