package com.ssafy.web.exception;

public class DuplicateUserException extends RuntimeException{
	public DuplicateUserException(String msg) {
		super(msg);
	}
}
