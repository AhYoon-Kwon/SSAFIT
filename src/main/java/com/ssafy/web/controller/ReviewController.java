package com.ssafy.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.web.model.dto.Review;
import com.ssafy.web.model.service.ReviewService;
import com.ssafy.web.model.service.UserService;
import com.ssafy.web.model.service.VideoService;

@RestController
@RequestMapping("/review")
public class ReviewController {
	
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private ReviewService reviewService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private VideoService videoService;
	
	//리뷰 쓰기
	@PostMapping("/write")
	public ResponseEntity<String> write(Review review){
		reviewService.writeReview(review);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.CREATED);
	}
	
	//리뷰 수정
	@PutMapping("/update")
	public ResponseEntity<String> update(Review review){
		reviewService.modifyReview(review);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
	
	/* 삭제 다시 체크 필 */
	//리뷰 삭제
	@DeleteMapping("/delete")
	public ResponseEntity<String> delete(Integer id){
		if(reviewService.deleteReview(id)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	
	//비디오 각각의 리뷰 리스트 반환
	@GetMapping("/all/{id}")
	public ResponseEntity<List<Review>> list(@PathVariable("id") int vid) {
		List<Review> review = reviewService.getReviewList(vid);
		
		return new ResponseEntity<List<Review>>(review, HttpStatus.OK);
	}
	
	//선택한 비디오의 부모 댓글 리스트
	@GetMapping("/par/{id}")
	public ResponseEntity<List<Review>> parList(@PathVariable("id") int vid) {
		List<Review> review = reviewService.getParRev(vid);
		
		return new ResponseEntity<List<Review>>(review, HttpStatus.OK);
	}
	
	//선택한 비디오의 자식 댓글 리스트
	@GetMapping("/chi/{id}")
	public ResponseEntity<List<Review>> childList(@PathVariable("id") int vid) {
		List<Review> review = reviewService.getChildRev(vid);
		
		return new ResponseEntity<List<Review>>(review, HttpStatus.OK);
	}

	
}
