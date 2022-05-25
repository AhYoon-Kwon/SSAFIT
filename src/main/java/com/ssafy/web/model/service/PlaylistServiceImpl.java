package com.ssafy.web.model.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.web.model.dao.PlaylistDao;
import com.ssafy.web.model.dto.Video;

@Service("playlistService")
public class PlaylistServiceImpl implements PlaylistService{

	@Autowired
	private PlaylistDao playlistDao;
	
	@Override
	public List<Video> getPlayList(int id) {
		return playlistDao.getPlayList(id);
	}

	@Override
	public void addPlayList(HashMap<String, Integer> map) {
		playlistDao.insertPlayList(map);
	}

	@Override
	public void deletePlayList(HashMap<String, Integer> map) {
		playlistDao.deletePlayList(map);
	}

}
