package com.ssafy.web.model.dao;

import java.util.List;

import com.ssafy.web.model.dto.Video;

public interface VideoDao {
	
	// 모든 비디오 정보를 가져옴
	List<Video> selectAll();
	
	//id로 비디오 검색
	Video selectById(Video video);
	
	//title로 비디오 검색
	Video selectByTitle(String title);
	
	//part로 비디오 검색
	Video selectByPart(String part);
	
	// 비디오 정보 수정
	void update(Video video);
	
	// 비디오 삽입
	void insert(Video video);
	
	
	 
	
}
