package com.ssafy.web.model.service;

import java.util.HashMap;
import java.util.List;

import com.ssafy.web.model.dto.Review;

public interface ReviewService {
	//리뷰쓰기
	void writeReview(Review review);
	//리뷰수정
	boolean modifyReview(Review review);
	//리뷰삭제
	boolean deleteReview(int id);
	
	//1안. 부모리뷰, 자식댓글 따로 가져오기
	//부모 댓글 가져오기
	List<Review> getParRev(int vid);
	//자식 댓글 가져오기
	List<Review> getChildRev(int vid);
	
	//2안. 다른 창으로 리뷰 세부정보 가져가기
	//전체 리뷰
	List<Review> getReviewList(int vid);
	//리뷰 아이디 얻어오기
	Review getReviewById(int id);
	//리뷰 읽기
	Review readReview(int id);
	
	/* 대댓글 */
	//댓글 쓰기
	void writeReply(Review review);
	//댓글 수정
	boolean modifyReply(Review review);
	//원글 수정시 댓글까지 삭제
	boolean deleteRevGroup(HashMap<Integer, Integer> params);
}
