package com.bikefunclub.memrot.model;

import java.sql.Timestamp;

public class MemrotVO {
	private Integer memno;
	private Integer rotno;
	private Timestamp clttime;
	public Integer getMemno() {
		return memno;
	}
	public void setMemno(Integer memno) {
		this.memno = memno;
	}
	public Integer getRotno() {
		return rotno;
	}
	public void setRotno(Integer rotno) {
		this.rotno = rotno;
	}
	public Timestamp getClttime() {
		return clttime;
	}
	public void setClttime(Timestamp timestamp) {
		this.clttime = timestamp;
	}

}
