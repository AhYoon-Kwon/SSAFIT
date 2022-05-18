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
	private int id;
	private int rate;
	private String content;
	private String time;
	private int depth;
	private int re_id;
	
	//다른 dao에서 조인할것들
	private int vid;
	private int uid;
}
