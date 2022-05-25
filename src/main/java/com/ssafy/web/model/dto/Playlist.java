package com.ssafy.web.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Playlist {
	private int id; //고유값
	private int uid;
	private int vid;
	private String title;
	private int num; //플레이리스트 개수 cnt
}
