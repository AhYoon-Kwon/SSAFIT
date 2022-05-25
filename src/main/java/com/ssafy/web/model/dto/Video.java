package com.ssafy.web.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Video {
	//
	private int id;
	private String videoKey;
	private String title;
	private String url;
	private String part;
	private String channelName;
	private int viewCnt;
	
	
	private double avgRate;
	private int likes;
}
