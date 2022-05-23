package com.ssafy.web.model.dto;

import lombok.Data;

@Data
public class Chat {
	private int id;
	private int uid;
	private String writer;
	private String content;
	private String regDate;
	
}
