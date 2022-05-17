package com.ssafy.web.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Video {
	private String vid;
	private String url;
	private String title;
	private String part;
	private int view;
	private int likes;
}
