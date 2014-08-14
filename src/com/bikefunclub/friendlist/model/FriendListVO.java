package com.bikefunclub.friendlist.model;

public class FriendListVO implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer memno;
	private Integer youno;
	private String isblack;
	private String isfriend;
	public Integer getMemno() {
		return memno;
	}
	public void setMemno(Integer memno) {
		this.memno = memno;
	}
	public Integer getYouno() {
		return youno;
	}
	public void setYouno(Integer youno) {
		this.youno = youno;
	}
	public String getIsblack() {
		return isblack;
	}
	public void setIsblack(String isblack) {
		this.isblack = isblack;
	}
	public String getIsfriend() {
		return isfriend;
	}
	public void setIsfriend(String isfriend) {
		this.isfriend = isfriend;
	}

}
