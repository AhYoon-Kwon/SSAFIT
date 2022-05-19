package com.ssafy.web.model.dao;

import java.util.List;

import com.ssafy.web.model.dto.Interest;
import com.ssafy.web.model.dto.Video;

public interface VideoDao {
	
	// 모든 비디오 정보를 가져옴
	List<Video> selectAll();
	
	// 시청한 비디오를 가져옴
	// user의 id를 인자로 받음
	List<Video> selectWatched(int id);

	// 찜한 비디오를 가져옴
	// user의 id를 인자로 받음
	List<Video> selectLiked(int id);
	
	//id로 비디오 검색
	Video selectById(int id);
	
	//title로 비디오 검색
	List<Video> selectByTitle(String title);
	
	//part로 비디오 검색
	List<Video> selectByPart(String part);
	
	// 비디오 정보 수정
	void update(Video video);
	
	// 비디오 삽입
	void insert(Video video);
	
	// 관심도를 반환
	List<Interest> getInterest(int id);
	
	
	 
	
}
