package com.ssafy.web.model.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.web.model.dao.ReviewDao;
import com.ssafy.web.model.dto.Review;

@Service
public class ReviewServiceImpl implements ReviewService {
	
	@Autowired
	private ReviewDao reviewDao;

	//리뷰 쓰기 (부모댓글)
	@Override
	public void writeReview(Review review) {
		boolean isRoot = review.getDepth() == 0;
		
		if(isRoot) {
			reviewDao.insertReview(review);
			reviewDao.parentCheck(review.getId());
		}
		else {
			reviewDao.insertReply(review);
		}
	}

	//리뷰 수정 : 내용, 평점 수정
	@Override
	public boolean modifyReview(Review review) {
		Review originReview = reviewDao.selectOne(review.getId());
		
		boolean isRoot = review.getDepth() == 0;
		if(isRoot) {
			originReview.setContent(review.getContent());
			originReview.setRate(review.getRate());			
			return reviewDao.updateReview(originReview) == 1;
		}
		else {
			originReview.setContent(review.getContent());
			return reviewDao.updateReply(originReview) == 1;
		}
	}

	//리뷰 삭제 : 댓글 없을 시
	@Override
	public boolean deleteReview(int id) {
		return reviewDao.deleteReview(id) == 1;
	}

	//선택한 비디오의 전체 리뷰 리스트
	@Override
	public List<Review> getReviewList(int vid) {
		return reviewDao.selectByVId(vid);
	}

	@Override
	public List<Review> getUserReviewList(int vid, int uid) {
		return reviewDao.selectByUID(vid, uid);
	}

	//선택한 비디오의 부모 댓글 리스트
	@Override
	public List<Review> getParRev(int vid) {
		return reviewDao.selectParRev(vid);
	}
	
	//선택한 비디오의 자식 댓글 리스트
	@Override
	public List<Review> getChildRev(int vid) {
		return reviewDao.selectChildRev(vid);
	}
	
	//리뷰 아이디로 가져오기
	@Override
	public Review getReviewById(int id) {
		return reviewDao.selectOne(id);
	}

	//리뷰 상세정보
	@Override
	public Review readReview(int id) {
		return reviewDao.selectOne(id);
	}

	/* 대댓 하단 기능 없어도 되는지 확인필요 */
	//리뷰 댓글 쓰기
	@Override
	public void writeReply(Review review) {
		reviewDao.insertReply(review);
	}

	//댓글 수정 : 내용 수정
	@Override
	public boolean modifyReply(Review review) {
		Review originReview = reviewDao.selectOne(review.getId());
		originReview.setContent(review.getContent());
		return reviewDao.updateReply(originReview) == 1;
	}

	//리뷰 삭제: 댓굴 있을 때
	@Override
	public boolean deleteRevGroup(HashMap<Integer, Integer> params) {
		return reviewDao.deleteRevGroup(params) == 1;
	}

}
