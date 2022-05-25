package com.ssafy.web.model.dao;

import java.util.HashMap;
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
	
	// 특정 부위의 동영상 중 시청하지 않은 것을 반환
	List<Video> selectNotWatchedByPart(HashMap<String, String> map);
	
	//모든 비디오를 랜덤한 순서로 반환
	List<Video> selectAllRand();
	
	// 모든 동영상 중 시청하지 않은 것을 반환
	List<Video> selectNotWatchedAllRand(int id);
	 
	//Watched table에 시청기록 추가
	void insertWatched(HashMap<String, Integer> map);
	
	// likes table에 찜 추가
	void insertLikes(HashMap<String, Integer> map);
	
	// likes table에 좋아요 삭제
	void deleteLikes(HashMap<String, Integer> map);
	
	// 모든 부위를 반환
	List<String> selectPartAll();
	
	//회원탈퇴시 찜목록 삭제
	void deleteUserLiked(int id);
	
	//회원탈퇴시 시청기록 삭제
	void deleteUserWatched(int id);
}
