package com.musicyao.bean;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@SessionScoped
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class MusicInitCounter implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7438152686237253277L;
	@XmlElement
	private int total;
	@XmlElement
	private int finished;
	@XmlElement
	private long percent;
	
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getFinished() {
		return finished;
	}
	public void setFinished(int finished) {
		this.finished = finished;
	}
	public void increment() {
		finished++;
		if(total>0)
			percent = (long)finished/(long)total;
	}
	public long getPercent() {
		return percent;
	}
	public void setPercent(long percent) {
		this.percent = percent;
	}
	
}
