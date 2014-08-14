package com.bikefunclub.riderecord.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class RideRecordVO implements Serializable{
	private Integer riderecordno;
	private Integer memno;
	private Integer rotno;
	private Timestamp  stamp;
	private Timestamp  recordtime;
	private Double recorddistence;
	private Long ridetime;
	private Integer newrotno;
	public Integer getRiderecordno() {
		return riderecordno;
	}
	public void setRiderecordno(Integer riderecordno) {
		this.riderecordno = riderecordno;
	}
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
	public Timestamp getStamp() {
		return stamp;
	}
	public void setStamp(Timestamp stamp) {
		this.stamp = stamp;
	}
	public Timestamp getRecordtime() {
		return recordtime;
	}
	public void setRecordtime(Timestamp recordtime) {
		this.recordtime = recordtime;
	}
	public Double getRecorddistence() {
		return recorddistence;
	}
	public void setRecorddistence(Double recorddistence) {
		this.recorddistence = recorddistence;
	}
	public Long getRidetime() {
		return ridetime;
	}
	public void setRidetime(Long ridetime) {
		this.ridetime = ridetime;
	}
	public Integer getNewrotno() {
		return newrotno;
	}
	public void setNewrotno(Integer newrotno) {
		this.newrotno = newrotno;
	}
	
}
