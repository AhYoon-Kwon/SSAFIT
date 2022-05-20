package com.ssafy.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.web.model.dto.Interest;
import com.ssafy.web.model.dto.Video;
import com.ssafy.web.model.service.VideoService;
import com.ssafy.web.util.JWTUtil;

@RestController
@RequestMapping("/")
public class VideoController {

	@Autowired
	VideoService videoService;

	@GetMapping("video")
	public ResponseEntity<List<Video>> video() {
		List<Video> video = videoService.getVideo();

		return new ResponseEntity<List<Video>>(video, HttpStatus.OK);
	}

	@GetMapping("video/{id}")
	public ResponseEntity<Video> videoId(@PathVariable int id) {
		videoService.updateViewCnt(id);
		Video video = videoService.getVideoById(id);

		/*
		 * id에 해당하는 video의 리뷰를 가져 온 뒤 평균을 계산하여 객체 정보에 저장
		 */

		/*
		 * 좋아요 한 회수를 가져온 뒤 객체 정보에 저장
		 */

		/*
		 * watched table에 시청기록이 존재하면 count 1증가 시청기록이 존재하지 않으면 watched table에 추가
		 */

		return new ResponseEntity<Video>(video, HttpStatus.OK);
	}

	@GetMapping("video/watched")
	public ResponseEntity<List<Video>> videoWatched() {

		int userId = 0;

		/*
		 * USERID는 토큰에서 얻어옴
		 */

		List<Video> video = videoService.getWatched(userId);

		/*
		 * NULL 처리
		 */

		return new ResponseEntity<List<Video>>(video, HttpStatus.OK);
	}

	@GetMapping("video/liked")
	public ResponseEntity<List<Video>> videoLiked() {

		int userId = 0;

		/*
		 * USERID는 토큰에서 얻어옴
		 */

		List<Video> video = videoService.getLiked(userId);

		/*
		 * NULL 처리
		 */

		return new ResponseEntity<List<Video>>(video, HttpStatus.OK);
	}

	@GetMapping("video/recommended")
	public ResponseEntity<List<Video>> videoRecommended() {

		int userId = 0;

		List<Video> video = null;

		List<Interest> interests = videoService.getInterest(userId);
		
		/*
		 * USERID는 토큰에서 얻어옴
		 */
		
		
		// 시청하지 않은 동영상 중 관심도가 높은 동영상 순으로 동영상 배열을 설정
		if (interests != null) {
			video = new ArrayList<Video>();
			
			for (Interest i : interests) {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("part", i.getPart());
				map.put("id", Integer.toString(userId));
				video.addAll(videoService.searchNotWatchedByPart(map));
			}
		}
		// 관심분야가 없을 경우 랜덤으로  비디오 반환
		else {
			video = videoService.getNotWatchedVideoRand(userId);
		}
		

	

		return new ResponseEntity<List<Video>>(video, HttpStatus.OK);
	}
}