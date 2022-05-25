package com.ssafy.web.model.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "ReviewDTO", description = "리뷰 정보를 표현한다")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Review {
	private int id; //review id
	private int vid; //video id
	private int uid; //user id
	private int rate;
	private String content;
	private String time;
	private int depth;  //댓글의 깊이 , 일반 댓글은 0, 대댓글은 1
	private int re_id; //대댓글(depth=1)일 경우 모댓글의 id값을 저장하여 누구의 대댓글인지 확인 할 수 있게
	private String writer;
	private int profile;
}
