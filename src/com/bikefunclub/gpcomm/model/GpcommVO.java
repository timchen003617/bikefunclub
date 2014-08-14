package com.bikefunclub.gpcomm.model;

import java.sql.Date;

public class GpcommVO {
	private Integer gpcommno;
	private Integer gpno;
	private Integer memno;
	private String gpcomm;
	private Date gpcommdate;
	public Integer getGpcommno() {
		return gpcommno;
	}
	public void setGpcommno(Integer gpcommno) {
		this.gpcommno = gpcommno;
	}
	public Integer getGpno() {
		return gpno;
	}
	public void setGpno(Integer gpno) {
		this.gpno = gpno;
	}
	public Integer getMemno() {
		return memno;
	}
	public void setMemno(Integer memno) {
		this.memno = memno;
	}
	public String getGpcomm() {
		return gpcomm;
	}
	public void setGpcomm(String gpcomm) {
		this.gpcomm = gpcomm;
	}
	public Date getGpcommdate() {
		return gpcommdate;
	}
	public void setGpcommdate(Date gpcommdate) {
		this.gpcommdate = gpcommdate;
	}
}
