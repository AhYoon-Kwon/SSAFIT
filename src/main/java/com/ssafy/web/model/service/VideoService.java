package com.ssafy.web.model.service;

import java.util.HashMap;
import java.util.List;

import com.ssafy.web.model.dto.Interest;
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
	
	// 부위별로 결과를 반환한다.
	List<Video> searchByPart(String part);
	
	// viewCnt 증가
	void updateViewCnt(int id);
	// likes 증가
	void updatelikes(int id);
	// reviewCnt 증가
	void updateReviewCnt(int id);
	// Rate avg 수정
	void updateRateAvg(int id, int rate);
	// User의 부위별 관심도를 반환
	List<Interest> getInterest(int id);
	
	// 특정 부위에 해당하는 동영상 중 안 본것을 반환
	List<Video> searchNotWatchedByPart(HashMap<String, String> map);
	
	// 랜덤한 순서로 비디오 반환
	List<Video> getVideoRand();
	
	// 시청하지 않은 비디오를 랜덤한 순서로 반환
	List<Video> getNotWatchedVideoRand(int id);
	
	// 비디오의 좋아요 개수 반환
	int getLikeCnt(int id);
	
	// 시청했는지 확인
	boolean checkWatched(int userId, int id);
	
	// watched에 시청기록 추가
	void setWatched(HashMap<String, Integer> map);
	
	// likes에 좋아요 추가
	void setLikes(HashMap<String, Integer> map);
	
	// likes에 좋아요 취소
	void deleteLikes(HashMap<String, Integer> map);
	
	// 운동 부위를 반환
	List<String> getPartAll();
	
}
