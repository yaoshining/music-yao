package com.musicyao.po;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.xml.bind.annotation.XmlRootElement;

import org.jaudiotagger.audio.AudioHeader;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;

@Entity
@XmlRootElement
public class Music implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -484491443243051848L;
	@Id
	@GeneratedValue
	private int id;
	@Embedded
	private MusicHeader header;
	private String artist;
	private String album;
	private String title;
	@Lob
	private String comment;
	private String year;
	private String track;
	private String discNo;
	private String composer;
	private String artistSort;
	private String path;
	
	public Music() {}
	
	public Music(Tag tag) {
		setTag(tag);
	}
	
	public void setTag(Tag tag) {
		if(tag==null) return;
		this.artist = tag.getFirst(FieldKey.ARTIST);
		this.album = tag.getFirst(FieldKey.ALBUM);
		this.title = tag.getFirst(FieldKey.TITLE);
		this.comment = tag.getFirst(FieldKey.COMMENT);
		this.year = tag.getFirst(FieldKey.YEAR);
		this.track = tag.getFirst(FieldKey.TRACK);
		this.discNo = tag.getFirst(FieldKey.DISC_NO);
		this.composer = tag.getFirst(FieldKey.COMPOSER);
		this.artistSort = tag.getFirst(FieldKey.ARTIST_SORT);
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getArtist() {
		return artist;
	}
	public void setArtist(String artist) {
		this.artist = artist;
	}
	public String getAlbum() {
		return album;
	}
	public void setAlbum(String album) {
		this.album = album;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getTrack() {
		return track;
	}
	public void setTrack(String track) {
		this.track = track;
	}
	public String getDiscNo() {
		return discNo;
	}
	public void setDiscNo(String discNo) {
		this.discNo = discNo;
	}
	public String getComposer() {
		return composer;
	}
	public void setComposer(String composer) {
		this.composer = composer;
	}
	public String getArtistSort() {
		return artistSort;
	}
	public void setArtistSort(String artistSort) {
		this.artistSort = artistSort;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public MusicHeader getHeader() {
		return header;
	}
	public void setHeader(MusicHeader header) {
		this.header = header;
	}
	
	
}
