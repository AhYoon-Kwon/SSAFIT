package com.ssafy.web.model.dao;

import java.util.List;

import com.ssafy.web.model.dto.Review;

public interface ReviewDao {
	//리뷰 추가
	void insertReview(Review review);
	
	//리뷰 수정
	void updateReview(Review review);
	
	//리뷰 삭제 
	//param: review id
	void deleteReview(int id);
	
	//비디오 id에 맞는 리뷰 select
	//param: video id;
	List<Review> selectByVId(int vid);
}
