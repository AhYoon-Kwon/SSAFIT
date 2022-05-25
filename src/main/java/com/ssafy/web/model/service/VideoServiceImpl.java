package com.ssafy.web.model.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.web.model.dao.VideoDao;
import com.ssafy.web.model.dto.Interest;
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
		videoDao.update(video);
	}
	
	@Override
	public List<Interest> getInterest(int id) {
		
		return videoDao.getInterest(id); 
	}
	
	@Override
	public List<Video> searchByPart(String part) {
	
		return videoDao.selectByPart(part);
	}
	
	@Override
	public List<Video> searchNotWatchedByPart(HashMap<String, String> map) {
		// TODO Auto-generated method stub
		return videoDao.selectNotWatchedByPart(map);
	}
	
	@Override
	public List<Video> getVideoRand() {
		// TODO Auto-generated method stub
		return videoDao.selectAllRand();
	}
	
	@Override
	public List<Video> getNotWatchedVideoRand(int id) {
		// TODO Auto-generated method stub
		return videoDao.selectNotWatchedAllRand(id);
	}
	
	@Override
	public int getLikeCnt(int id) {
		// TODO Auto-generated method stub
		return videoDao.selectLiked(id).size();
	}
	
	@Override
	public boolean checkWatched(int userId, int id) {
		// TODO Auto-generated method stub
		List<Video> watched = videoDao.selectWatched(userId);
		if(watched == null)
			return false;
		else {
			for(Video v : watched) {
				if(v.getId() == id)
					return true;
			}
		}
		return false;
	}
	
	@Override
	public void setWatched(HashMap<String, Integer> map) {
		// TODO Auto-generated method stub
		videoDao.insertWatched(map);
	}
	
	@Override
	public void setLikes(HashMap<String, Integer> map) {
		// TODO Auto-generated method stub
		videoDao.insertLikes(map);
		
	}
	
	@Override
	public void deleteLikes(HashMap<String, Integer> map) {
		// TODO Auto-generated method stub
		
		videoDao.deleteLikes(map);
	}
	
	@Override
	public List<String> getPartAll() {
		// TODO Auto-generated method stub
		return videoDao.selectPartAll();
	}

	@Override
	public List<Video> getPlayList(int id) {
		return videoDao.getPlayList(id);
	}

	@Override
	public void addPlayList(HashMap<String, Integer> map) {
		videoDao.insertPlayList(map);
	}

	@Override
	public void deletePlayList(HashMap<String, Integer> map) {
		videoDao.deletePlayList(map);
	}
	
}
