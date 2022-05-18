package com.ssafy.web.model.service;

import java.util.List;

import com.ssafy.web.model.dto.Video;

public interface VideoService {

	//모든 영상을 가져온다
	List<Video> getVideo();
	
	//시청한 영상을 가져온다
	List<Video> getWatched(int id);
	
	//찜한 영상을 가져온다
	List<Video> getLiked(int id);
	
	//선택한 동영상을 가져온다.
	Video getVideoById(int id);
	
	// 제목으로 검색한 결과를 반환하다.
	List<Video> searchByTitle(String title);
	
	// viewCnt 증가
	void updateViewCnt(int id);
	
}
