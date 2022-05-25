package com.ssafy.web.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.web.model.dto.Video;
import com.ssafy.web.model.service.PlaylistService;
import com.ssafy.web.util.JWTUtil;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/playlist")
public class PlaylistController {
	private static final String HEADER_AUTH = "access-token";
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private JWTUtil jwtUtil;
	
	@Autowired
	private PlaylistService playlistService;
	
	/*
	 * 플레이리스트에 담은 비디오를 반환
	 */
	@ApiOperation(value = "플레이리스트 목록을 반환")
	@GetMapping("/playlist")
	public ResponseEntity<List<Video>> getPlaylist(HttpServletRequest req) {

		/*
		 * USERID는 토큰에서 얻어옴
		 */
		String token = req.getHeader("access-token");
		int userId = 0;
		try {
			userId = jwtUtil.getInfo(token).getId();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		List<Video> video = playlistService.getPlayList(userId);
		/*
		 * NULL 처리
		 */
		return new ResponseEntity<List<Video>>(video, HttpStatus.OK);
	}
	
	/*
	 * 플레이리스트에 추가한 경우
	 */
	@ApiOperation(value = "플레이리스트 추가")
	@PostMapping("/playlist")
	public ResponseEntity<String> insertPlaylist(@RequestParam int id, HttpServletRequest req) {

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
		map.put("num", id);

		System.out.println(map.get("uid") + " " + map.get("id"));

		playlistService.addPlayList(map);

		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}

	@ApiOperation(value = "플레이리스트에서 삭제")
	@DeleteMapping("/playlist/{id}")
	public ResponseEntity<String> deletePlaylist(@PathVariable int id, HttpServletRequest req) {

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

		playlistService.deletePlayList(map);

		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
}
