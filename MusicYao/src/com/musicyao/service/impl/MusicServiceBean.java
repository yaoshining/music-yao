package com.musicyao.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.musicyao.bean.MusicInitCounter;
import com.musicyao.po.Music;
import com.musicyao.service.MusicService;
import com.musicyao.util.AudioFileFilter;
import com.musicyao.util.AudioFileUtil;
import com.yao.dao.jpa.JpaDaoSupport;

@Stateless
public class MusicServiceBean extends JpaDaoSupport implements MusicService{
	@Inject
	private MusicInitCounter counter;
	@Override
	public int initMusics(File root) {
		int total = AudioFileUtil.totalFileCount(root);
		counter.setTotal(total);
		init(root);
		return counter.getTotal();
	}

	@Override
	public int initMusics(String dirPath) {
		File dirFile = new File(dirPath);
		return initMusics(dirFile);
	}
	
	private void init(File dirFile) {
		if(dirFile.exists()){
			if(dirFile.isDirectory()){
				for(File file : dirFile.listFiles(new AudioFileFilter())) {
					if(file.isDirectory()){
						initMusics(file);
					}else{
						Music music = AudioFileUtil.readFile(file);
						save(music);
						counter.increment();
					}
				}
			}else{
				System.out.println("不是目录");
			}
		}else{
			System.out.println("不存在");
		}
	}

	@Override
	public List<Music> random(int num) {
		List<Music> result = new ArrayList<Music>();
		int total = findAll(Music.class).size();
		for(int i=0;i<num;i++){
			int radomIndex = (int) (Math.random()*total);
			result.addAll(find(Music.class, null, null, radomIndex, 1));
		}
		return result;
	}

}
