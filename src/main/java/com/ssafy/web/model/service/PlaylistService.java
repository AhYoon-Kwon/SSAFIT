package com.ssafy.web.model.service;

import java.util.HashMap;
import java.util.List;

import com.ssafy.web.model.dto.Video;

public interface PlaylistService {
	//플레이리스트에 담은 비디오를 반환
	List<Video> getPlayList(int id);
	
	// 플레이리스트 추가
	void addPlayList(HashMap<String, Integer> map);
		
	// 플레이리스트 삭제
	void deletePlayList(HashMap<String, Integer> map);
}
