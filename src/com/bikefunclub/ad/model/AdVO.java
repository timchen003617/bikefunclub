package com.bikefunclub.ad.model;

public class AdVO implements java.io.Serializable {
	private Integer adno;
	private String adname;
	private String adesc;
	private byte[] adfile;
	private String filename;
	private String extname;
	private String adurl;

	public Integer getAdno() {
		return adno;
	}

	public void setAdno(Integer adno) {
		this.adno = adno;
	}

	public String getAdname() {
		return adname;
	}

	public void setAdname(String adname) {
		this.adname = adname;
	}

	public String getAdesc() {
		return adesc;
	}

	public void setAdesc(String adesc) {
		this.adesc = adesc;
	}

	public byte[] getAdfile() {
		return adfile;
	}

	public void setAdfile(byte[] adfile) {
		this.adfile = adfile;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getExtname() {
		return extname;
	}

	public void setExtname(String extname) {
		this.extname = extname;
	}

	public String getAdurl() {
		return adurl;
	}

	public void setAdurl(String adurl) {
		this.adurl = adurl;
	}

}
