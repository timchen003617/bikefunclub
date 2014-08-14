package com.bikefunclub.photo.model;

import java.sql.Timestamp;


public class PhotoVO implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer photono;
	private Integer memno;
	private String  phcoo;
	private Integer phass;
	private String  phfilename;
	private String  phextname;
	private Timestamp    phup;
	private byte[]  phfile;
	public Integer getPhotono() {
		return photono;
	}
	public void setPhotono(Integer photono) {
		this.photono = photono;
	}
	public Integer getMemno() {
		return memno;
	}
	public void setMemno(Integer memno) {
		this.memno = memno;
	}
	public String getPhcoo() {
		return phcoo;
	}
	public void setPhcoo(String phcoo) {
		this.phcoo = phcoo;
	}
	public Integer getPhass() {
		return phass;
	}
	public void setPhass(Integer phass) {
		this.phass = phass;
	}
	public String getPhfilename() {
		return phfilename;
	}
	public void setPhfilename(String phfilename) {
		this.phfilename = phfilename;
	}
	public String getPhextname() {
		return phextname;
	}
	public void setPhextname(String phextname) {
		this.phextname = phextname;
	}
	public Timestamp getPhup() {
		return phup;
	}
	public void setPhup(Timestamp phup) {
		this.phup = phup;
	}
	public byte[] getPhfile() {
		return phfile;
	}
	public void setPhfile(byte[] phfile) {
		this.phfile = phfile;
	}
}