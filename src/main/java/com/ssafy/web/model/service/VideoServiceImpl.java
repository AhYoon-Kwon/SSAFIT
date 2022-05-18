package com.ssafy.web.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.web.model.dao.VideoDao;
import com.ssafy.web.model.dto.Video;

@Service
public class VideoServiceImpl implements VideoService{

	@Autowired
	VideoDao videoDao;
	
	@Override
	public List<Video> getVideo() {
		
		return videoDao.selectAll();
	}

	@Override
	public List<Video> getWatched(int id) {
		
		return videoDao.selectWatched(id);
	}

	@Override
	public List<Video> getLiked(int id) {
		
		return videoDao.selectLiked(id);
	}

	@Override
	public Video getVideoById(int id) {
		
		return videoDao.selectById(id);
	}

	@Override
	public List<Video> searchByTitle(String title) {
		
		return videoDao.selectByTitle(title);
	}
	
	@Override
	public void updateViewCnt(int id) {
		// TODO Auto-generated method stub
		Video video = videoDao.selectById(id);
		video.setViewCnt(video.getViewCnt() + 1);
		System.out.println(video);
		videoDao.update(video);
	}
}
