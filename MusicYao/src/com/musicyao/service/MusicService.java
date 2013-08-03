package com.musicyao.service;

import java.io.File;
import java.util.List;

import com.musicyao.po.Music;
import com.yao.dao.DAO;

public interface MusicService extends DAO{
	public int initMusics(File dirFile);
	public int initMusics(String dirPath);
	public List<Music> random(int num);
}
