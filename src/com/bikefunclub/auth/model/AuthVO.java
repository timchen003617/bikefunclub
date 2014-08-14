package com.bikefunclub.auth.model;

public class AuthVO implements java.io.Serializable{
	private String authno;
	private String authname;
	private String authurl;
	private String belongauthno;
	private String authlevel;
	public AuthVO() {
		super();
	}
	public String getAuthno() {
		return authno;
	}
	public void setAuthno(String authno) {
		this.authno = authno;
	}
	public String getAuthname() {
		return authname;
	}
	public void setAuthname(String authname) {
		this.authname = authname;
	}
	public String getAuthurl() {
		return authurl;
	}
	public void setAuthurl(String authurl) {
		this.authurl = authurl;
	}
	public String getBelongauthno() {
		return belongauthno;
	}
	public void setBelongauthno(String belongauthno) {
		this.belongauthno = belongauthno;
	}
	public String getAuthlevel() {
		return authlevel;
	}
	public void setAuthlevel(String authlevel) {
		this.authlevel = authlevel;
	}

}
