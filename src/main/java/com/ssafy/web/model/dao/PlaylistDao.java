package com.ssafy.web.model.dao;

import java.util.HashMap;
import java.util.List;

import com.ssafy.web.model.dto.Video;

public interface PlaylistDao {
	//플레이리스트 반환
	//user id를 받음
	List<Video> getPlayList(int id);
	
	//플레이리스트 추가
	void insertPlayList(HashMap<String, Integer> map);
		
	//플레이리스트 삭제
	void deletePlayList(HashMap<String, Integer> map);
}
