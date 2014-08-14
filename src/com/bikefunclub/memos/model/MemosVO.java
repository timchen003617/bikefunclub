package com.bikefunclub.memos.model;
import java.sql.Date;

public class MemosVO implements java.io.Serializable{
	private  Integer  memno;
	private  String   memcoo;
	public Integer getMemno() {
		return memno;
	}
	public void setMemno(Integer memno) {
		this.memno = memno;
	}
	public String getMemcoo() {
		return memcoo;
	}
	public void setMemcoo(String memcoo) {
		this.memcoo = memcoo;
	}
	
	
}
