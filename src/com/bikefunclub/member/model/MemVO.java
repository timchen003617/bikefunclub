package com.bikefunclub.member.model;

import java.sql.Date;
import java.sql.Timestamp;

public class MemVO implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3921844258819247565L;
	private Integer memno;
	private String memacc;
	private String mempw;
	private String memname;
	private String memid;
	private Date membirth;
	private String memnickname;
	private byte[] memfile;
	private String memfilename;
	private String memextname;
	private String mememail;
	private String memsex;
	private String memzip;
	private String memaddr;
	private String memtelh;
	private String memtelo;
	private String memtelm;
	private String memgetmailyn;
	private Timestamp memrgdate;
	
	public Integer getMemno() {
		return memno;
	}
	public void setMemno(Integer memno) {
		this.memno = memno;
	}
	public String getMemacc() {
		return memacc;
	}
	public void setMemacc(String memacc) {
		this.memacc = memacc;
	}
	public String getMempw() {
		return mempw;
	}
	public void setMempw(String mempw) {
		this.mempw = mempw;
	}
	public String getMemname() {
		return memname;
	}
	public void setMemname(String memname) {
		this.memname = memname;
	}
	public String getMemid() {
		return memid;
	}
	public void setMemid(String memid) {
		this.memid = memid;
	}
	public Date getMembirth() {
		return membirth;
	}
	public void setMembirth(Date membirth) {
		this.membirth = membirth;
	}
	public String getMemnickname() {
		return memnickname;
	}
	public void setMemnickname(String memnickname) {
		this.memnickname = memnickname;
	}
	public byte[] getMemfile() {
		return memfile;
	}
	public void setMemfile(byte[] memfile) {
		this.memfile = memfile;
	}
	public String getMemfilename() {
		return memfilename;
	}
	public void setMemfilename(String memfilename) {
		this.memfilename = memfilename;
	}
	public String getMemextname() {
		return memextname;
	}
	public void setMemextname(String memextname) {
		this.memextname = memextname;
	}
	public String getMememail() {
		return mememail;
	}
	public void setMememail(String mememail) {
		this.mememail = mememail;
	}
	public String getMemsex() {
		return memsex;
	}
	public void setMemsex(String memsex) {
		this.memsex = memsex;
	}
	public String getMemzip() {
		return memzip;
	}
	public void setMemzip(String memzip) {
		this.memzip = memzip;
	}
	public String getMemaddr() {
		return memaddr;
	}
	public void setMemaddr(String memaddr) {
		this.memaddr = memaddr;
	}
	public String getMemtelh() {
		return memtelh;
	}
	public void setMemtelh(String memtelh) {
		this.memtelh = memtelh;
	}
	public String getMemtelo() {
		return memtelo;
	}
	public void setMemtelo(String memtelo) {
		this.memtelo = memtelo;
	}
	public String getMemtelm() {
		return memtelm;
	}
	public void setMemtelm(String memtelm) {
		this.memtelm = memtelm;
	}
	public String getMemgetmailyn() {
		return memgetmailyn;
	}
	public void setMemgetmailyn(String memgetmailyn) {
		this.memgetmailyn = memgetmailyn;
	}
	public Timestamp getMemrgdate() {
		return memrgdate;
	}
	public void setMemrgdate(Timestamp memrgdate) {
		this.memrgdate = memrgdate;
	}



}
