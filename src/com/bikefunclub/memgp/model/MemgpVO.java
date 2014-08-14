package com.bikefunclub.memgp.model;


import java.sql.Timestamp;

public class MemgpVO {
	private Integer memno;
	private Integer gpno;
	private Timestamp adddate;
	private String memname;
	public Integer getMemno() {
		return memno;
	}
	public void setMemno(Integer memno) {
		this.memno = memno;
	}
	public Integer getGpno() {
		return gpno;
	}
	public void setGpno(Integer gpno) {
		this.gpno = gpno;
	}
	public Timestamp getAdddate() {
		return adddate;
	}
	public void setAdddate(Timestamp adddate) {
		this.adddate = adddate;
	}
	public String getMemname() {
		return memname;
	}
	public void setMemname(String memname) {
		this.memname = memname;
	}
}
