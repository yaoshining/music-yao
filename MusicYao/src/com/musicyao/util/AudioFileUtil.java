package com.musicyao.util;

import java.io.File;
import java.io.IOException;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.AudioHeader;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;
import org.jaudiotagger.tag.datatype.Artwork;

import com.musicyao.po.Music;
import com.musicyao.po.MusicHeader;

public class AudioFileUtil {
	public static Music readFile(File file){
		Music music = new Music();
		try {
			AudioFile audioFile = AudioFileIO.read(file);
			AudioHeader audioHeader = audioFile.getAudioHeader();
			Tag tag = audioFile.getTag();
			if(audioHeader!=null){
				MusicHeader musicHeader = new MusicHeader(audioHeader);
				music.setHeader(musicHeader);
			}
			music.setTag(tag);
			music.setPath(file.getAbsolutePath());
		} catch (CannotReadException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TagException e) {
			e.printStackTrace();
		} catch (ReadOnlyFileException e) {
			e.printStackTrace();
		} catch (InvalidAudioFrameException e) {
			e.printStackTrace();
		}
		return music;
	}
	public static Music readFile(String pathname){
		File file = new File(pathname);
		return readFile(file);
	}
	public static int totalFileCount(File file){
		int count = 0;
		if(file.exists()){
			if(file.isDirectory()){
				for(File f : file.listFiles(new AudioFileFilter())){
					if(f.isDirectory()){
						count = count+totalFileCount(f);
					}else{
						count++;
					}
				}
			}else{
				count++;
			}
		}
		return count;
	}
	public static byte[] getPoster(File file){
		try {
			AudioFile f = AudioFileIO.read(file);
			Tag tag = f.getTag();
			Artwork artwork = tag.getFirstArtwork();
			if(artwork!=null)
				return artwork.getBinaryData();
		} catch (CannotReadException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TagException e) {
			e.printStackTrace();
		} catch (ReadOnlyFileException e) {
			e.printStackTrace();
		} catch (InvalidAudioFrameException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static byte[] getPoster(String pathname){
		File file = new File(pathname);
		return getPoster(file);
	}
}
