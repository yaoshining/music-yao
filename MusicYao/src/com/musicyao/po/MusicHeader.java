package com.musicyao.po;

import java.io.Serializable;

import javax.persistence.Embeddable;

import org.jaudiotagger.audio.AudioHeader;

@Embeddable
public class MusicHeader implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9187469615000026795L;
	private Long bitRate = 0L;
	private String channel;
	private String encodingType;
	private String format;
	private int sampleRate;
	
	public MusicHeader() {}
	
	public MusicHeader(AudioHeader audioHeader) {
		setAudioHeader(audioHeader);
	}
	
	public void setAudioHeader(AudioHeader audioHeader) {
		this.bitRate = audioHeader.getBitRateAsNumber();
		this.channel = audioHeader.getChannels();
		this.encodingType = audioHeader.getEncodingType();
		this.format = audioHeader.getFormat();
		this.sampleRate = audioHeader.getSampleRateAsNumber();
	}
	public Long getBitRate() {
		return bitRate;
	}

	public void setBitRate(Long bitRate) {
		this.bitRate = bitRate;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getEncodingType() {
		return encodingType;
	}

	public void setEncodingType(String encodingType) {
		this.encodingType = encodingType;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public int getSampleRate() {
		return sampleRate;
	}

	public void setSampleRate(int sampleRate) {
		this.sampleRate = sampleRate;
	}
	
}
