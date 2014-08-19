package com.bikefunclub.gpcomm.model;

import java.sql.Timestamp;

public class GpcommVO {
	private Integer gpcommno;
	private Integer gpno;
	private Integer memno;
	private String gpcomm;
	private Timestamp gpcommdate;
	
	
	
	public GpcommVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public GpcommVO( Integer gpno, Integer memno,
			String gpcomm, Timestamp gpcommdate) {
		
		this.gpno = gpno;
		this.memno = memno;
		this.gpcomm = gpcomm;
		this.gpcommdate = gpcommdate;
	}
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
	public Timestamp getGpcommdate() {
		return gpcommdate;
	}
	public void setGpcommdate(Timestamp gpcommdate) {
		this.gpcommdate = gpcommdate;
	}
}
