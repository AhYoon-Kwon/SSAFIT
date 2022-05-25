package com.ssafy.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.web.model.dto.Interest;
import com.ssafy.web.model.dto.Review;
import com.ssafy.web.model.dto.User;
import com.ssafy.web.model.dto.Video;
import com.ssafy.web.model.service.ReviewService;
import com.ssafy.web.model.service.UserService;
import com.ssafy.web.model.service.VideoService;
import com.ssafy.web.util.JWTUtil;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/video")
public class VideoController {

	@Autowired
	VideoService videoService;

	@Autowired
	ReviewService reviewService;

	@Autowired
	UserService userService;

	@Autowired
	JWTUtil jwtUtil;

	static String SUCCESS = "SUCCESS";
	static String FAIL = "FAIL";

	/*
	 * 전체 비디오를 반환
	 */
	@ApiOperation(value = "모든 비디오의 정보를 조회")
	@GetMapping("/all")
	public ResponseEntity<List<Video>> video() {
		List<Video> video = videoService.getVideoRand();

		return new ResponseEntity<List<Video>>(video, HttpStatus.OK);
	}

	/*
	 * id에 해당하는 비디오를 반환 시청기록에 추가 viewCnt 증가
	 */
	@ApiOperation(value = "특정 id에 대한 비디오 정보 반환", notes = "비디오의 정보와, 비디오의 평점, 좋아요 회수를 반환한다")
	@GetMapping("/detail/{id}")
	public ResponseEntity<Video> videoId(@PathVariable int id, HttpServletRequest req) {
		videoService.updateViewCnt(id);
		Video video = videoService.getVideoById(id);

		String token = req.getHeader("access-token");
		int userId = 0;
		try {
			userId = jwtUtil.getInfo(token).getId();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*
		 * 시청기록이 존재하지 않으면 watched table에 추가
		 */
		if (!videoService.checkWatched(userId, id)) {
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			map.put("id", id);
			map.put("uid", userId);
			videoService.setWatched(map);
		}

		/*
		 * id에 해당하는 video의 리뷰를 가져 온 뒤 평균을 계산하여 객체 정보에 저장
		 */
		List<Review> reviews = reviewService.getReviewList(id);
		video.setAvgRate(getAvgRate(reviews));

		/*
		 * 좋아요 한 횟수를 가져온 뒤 객체 정보에 저장
		 */
		video.setLikes(videoService.getLikeCnt(id));

		return new ResponseEntity<Video>(video, HttpStatus.OK);
	}

	/*
	 * 시청한 비디오를 반환
	 */
	@ApiOperation(value = "회원의 시청기록을 반환")
	@GetMapping("/watched")
	public ResponseEntity<List<Video>> videoWatched(HttpServletRequest req) {

		/*
		 * USERID는 토큰에서 얻어옴
		 */
		String token = req.getHeader("access-token");
		int userId = 0;
		try {
			userId = jwtUtil.getInfo(token).getId();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(userId);

		List<Video> video = videoService.getWatched(userId);
		/*
		 * NULL 처리
		 */

		return new ResponseEntity<List<Video>>(video, HttpStatus.OK);
	}

	/*
	 * 찜한 비디오를 반환
	 */
	@ApiOperation(value = "회원의 찜 기록을 반환")
	@GetMapping("/liked")
	public ResponseEntity<List<Video>> videoLiked(HttpServletRequest req) {

		/*
		 * USERID는 토큰에서 얻어옴
		 */
		String token = req.getHeader("access-token");
		int userId = 0;
		try {
			userId = jwtUtil.getInfo(token).getId();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		List<Video> video = videoService.getLiked(userId);

		/*
		 * NULL 처리
		 */

		return new ResponseEntity<List<Video>>(video, HttpStatus.OK);
	}

	/*
	 * user 추천 비디오를 반환
	 */
	@ApiOperation(value = "추천 비디오를 반환")
	@GetMapping("/recommended")
	public ResponseEntity<List<Video>> videoRecommended(HttpServletRequest req) {

		List<Video> video = null;

		/*
		 * USERID는 토큰에서 얻어옴
		 */
		String token = req.getHeader("access-token");
		if(token == null) {
			video = videoService.getVideoRand();
			return new ResponseEntity<List<Video>>(video, HttpStatus.OK);
		}
		
		try {
			int userId = 0;
			userId = jwtUtil.getInfo(token).getId();
			
			System.out.println("object token : " + userId);
			
			List<Interest> interests = videoService.getInterest(userId);
			
			// 시청하지 않은 동영상 중 관심도가 높은 동영상 순으로 동영상 배열을 설정
			if (interests != null) {
				video = new ArrayList<Video>();
				System.out.println("not null 부분");
				for (Interest i : interests) {
					HashMap<String, String> map = new HashMap<String, String>();
					map.put("part", i.getPart());
					map.put("id", Integer.toString(userId));
					video.addAll(videoService.searchNotWatchedByPart(map));
				}
				
			}
			// 관심분야가 없을 경우 랜덤으로 비디오 반환
			else {
				System.out.println("null 부분");
				video = videoService.getNotWatchedVideoRand(userId);
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return new ResponseEntity<List<Video>>(video, HttpStatus.OK);
		
		
	}

	/*
	 * 좋아요를 누른경우
	 */
	@ApiOperation(value = "좋아요 누르기")
	@PostMapping("/likes")
	public ResponseEntity<String> insertLikes(@RequestParam int id, HttpServletRequest req) {

		/*
		 * USERID는 토큰에서 얻어옴
		 */

		String token = req.getHeader("access-token");
		int userId = 0;
		try {
			userId = jwtUtil.getInfo(token).getId();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*
		 * 이미 좋아요를 누른경우??? 예외처리 어떻게?
		 */
		HashMap<String, Integer> map = new HashMap<String, Integer>();

		map.put("uid", userId);
		map.put("id", id);

		System.out.println(map.get("uid") + " " + map.get("id"));

		videoService.setLikes(map);

		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}

	@ApiOperation(value = "좋아요 취소")
	@DeleteMapping("/likes/{id}")
	public ResponseEntity<String> deleteLikes(@PathVariable int id, HttpServletRequest req) {

		/*
		 * USERID는 토큰에서 얻어옴
		 */
		String token = req.getHeader("access-token");
		int userId = 0;
		try {
			userId = jwtUtil.getInfo(token).getId();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		/*
		 * 좋아요 누르지 않은 id에 대해 요청을 보낼 경우 예외처리를 해줘야한다.
		 */
		HashMap<String, Integer> map = new HashMap<String, Integer>();

		map.put("uid", userId);
		map.put("id", id);

		System.out.println(map.get("uid") + " " + map.get("id"));

		videoService.deleteLikes(map);

		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}

	/*
	 * 부위의 종류를 반환한다
	 */
	@ApiOperation(value = "부위의 종류 반환")
	@GetMapping("/part")
	public ResponseEntity<List<String>> getParts() {
		List<String> list = videoService.getPartAll();

		return new ResponseEntity<List<String>>(list, HttpStatus.OK);
	}

	/*
	 * 리뷰의 평점을 계산함
	 */
	public double getAvgRate(List<Review> reviews) {
		double rate = 0;
		int cnt = 0;
		for (Review review : reviews) {
			if (review.getDepth() == 0) {
				cnt++;
				rate += review.getRate();
			}
		}
		rate /= cnt;

		return rate;
	}
	
}
