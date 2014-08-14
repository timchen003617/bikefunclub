package com.bikefunclub.blogcom.model;

import java.sql.Timestamp;

public class BlogcomVO implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer bgcomno;
	private Integer blogno;
    private Integer memno;
    private String  bgcomtext;
    private Timestamp    bgcomtime;
	public Integer getBgcomno() {
		return bgcomno;
	}
	public void setBgcomno(Integer bgcomno) {
		this.bgcomno = bgcomno;
	}
	public Integer getBlogno() {
		return blogno;
	}
	public void setBlogno(Integer blogno) {
		this.blogno = blogno;
	}
	public Integer getMemno() {
		return memno;
	}
	public void setMemno(Integer memno) {
		this.memno = memno;
	}
	public String getBgcomtext() {
		return bgcomtext;
	}
	public void setBgcomtext(String bgcomtext) {
		this.bgcomtext = bgcomtext;
	}
	public Timestamp getBgcomtime() {
		return bgcomtime;
	}
	public void setBgcomtime(Timestamp bgcomtime) {
		this.bgcomtime = bgcomtime;
	}

	
}