package com.ssafy.web.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.web.model.dto.Video;
import com.ssafy.web.model.service.VideoService;

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
		
		return new ResponseEntity<Video>(video, HttpStatus.OK);
	}
	
	@GetMapping("video/watched/{id}")
	public ResponseEntity<List<Video>> videoWatched(int id) {
		List<Video> video = videoService.getWatched(id);
		
		return new ResponseEntity<List<Video>>(video, HttpStatus.OK);
	}
	
	@GetMapping("video/liked/{id}")
	public ResponseEntity<List<Video>> videoLiked(int id) {
		List<Video> video = videoService.getLiked(id);
		
		return new ResponseEntity<List<Video>>(video, HttpStatus.OK);
	}
}
