package com.ssafy.web.model.dao;

import java.util.HashMap;
import java.util.List;

import com.ssafy.web.model.dto.Review;

public interface ReviewDao {
	//리뷰 추가
	void insertReview(Review review);
	
	//리뷰 수정
	int updateReview(Review review);
	
	//리뷰 삭제 : 댓글없을 때 부모만 삭제
	//param: review id
	int deleteReview(int id);
	
	//비디오 id에 맞는 리뷰 select
	//param: video id
	List<Review> selectByVId(int vid);
	
	//비디오 id에 맞는 부모리뷰 select 
	//param: HashMap<video id, depth>
	List<Review> selectParRev(HashMap<Integer, Integer> params);
	
	//비디오 id에 맞는 자식리뷰 select
	//param: HashMap<video id, depth>
	List<Review> selectChildRev(HashMap<Integer, Integer> params);
	
	//댓글 수정을 위한 리뷰 하나 select
	Review selectOne(int id);
	
	
	
	//유저 탈퇴시 리뷰 전체 삭제 기능 : 댓글 없을 때 
	//param: user id
	void userDelete(int uid);

	//자식도 삭제하기 위한 정보 얻어옴 (video id, re_id)
	HashMap<Integer, Integer> informId(int uid);
	
	
	
	/* 대댓글 기능 */
	//1. 부모 댓글일 때 부모 id와 re_id는 동일하게 함
	void parentCheck(int id);
	
	//2. 부모 댓글이 삭제되면 그에 딸린 모든 자식 테이블이 삭제
	//param: HashMap<video id, re_id>
	int deleteRevGroup(HashMap<Integer, Integer> params);
	
	//3. 대댓글 쓰기
	void insertReply(Review review);
	
	//4. 대댓글 수정
	int updateReply(Review review);

}
