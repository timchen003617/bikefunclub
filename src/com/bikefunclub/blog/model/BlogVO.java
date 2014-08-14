package com.bikefunclub.blog.model;

import java.sql.Timestamp;

public class BlogVO implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer blogno;
	private Integer memno;
	private Integer blogclsno;
    private String  bgtitle;
    private String  bgtext;
    private String  authname;
    private Timestamp    bgtime;
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
	public Integer getBlogclsno() {
		return blogclsno;
	}
	public void setBlogclsno(Integer blogclsno) {
		this.blogclsno = blogclsno;
	}
	public String getBgtitle() {
		return bgtitle;
	}
	public void setBgtitle(String bgtitle) {
		this.bgtitle = bgtitle;
	}
	public String getBgtext() {
		return bgtext;
	}
	public void setBgtext(String bgtext) {
		this.bgtext = bgtext;
	}
	public Timestamp getBgtime() {
		return bgtime;
	}
	public void setBgtime(Timestamp bgtime) {
		this.bgtime = bgtime;
	}
	public String getAuthname() {
		return authname;
	}
	public void setAuthname(String authname) {
		this.authname = authname;
	}
}