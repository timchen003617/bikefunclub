package com.bikefunclub.Ann.model;

import java.sql.Date;
import java.sql.Timestamp;

public class AnnVO implements java.io.Serializable {
	private Integer annno;
	private Integer empno;
	private String anntitle;
	private String anncontent;
	private Timestamp anndate;
	private byte[] annfile;
	private String annfilename;
	private String annextname;

	public Integer getAnnno() {
		return annno;
	}

	public void setAnnno(Integer annno) {
		this.annno = annno;
	}

	public Integer getEmpno() {
		return empno;
	}

	public void setEmpno(Integer empno) {
		this.empno = empno;
	}

	public String getAnntitle() {
		return anntitle;
	}

	public void setAnntitle(String anntitle) {
		this.anntitle = anntitle;
	}

	public String getAnncontent() {
		return anncontent;
	}

	public void setAnncontent(String anncontent) {
		this.anncontent = anncontent;
	}

	public Timestamp getAnndate() {
		return anndate;
	}

	public void setAnndate(Timestamp anndate) {
		this.anndate = anndate;
	}

	public byte[] getAnnfile() {
		return annfile;
	}

	public void setAnnfile(byte[] annfile) {
		this.annfile = annfile;
	}

	public String getAnnfilename() {
		return annfilename;
	}

	public void setAnnfilename(String annfilename) {
		this.annfilename = annfilename;
	}

	public String getAnnextname() {
		return annextname;
	}

	public void setAnnextname(String annextname) {
		this.annextname = annextname;
	}

}
