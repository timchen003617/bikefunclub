package com.bikefunclub.album.model;
	
import java.sql.*;


public class AlbumVO implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer      albno;
	private Integer      memno;
	private Integer      albclsno;
	private String       authname;
    private String       albtitle;
    private String       albdesc;
    private Timestamp    albtime;
	public Integer getAlbno() {
		return albno;
	}
	public void setAlbno(Integer albno) {
		this.albno = albno;
	}
	public Integer getMemno() {
		return memno;
	}
	public void setMemno(Integer memno) {
		this.memno = memno;
	}
	public Integer getAlbclsno() {
		return albclsno;
	}
	public void setAlbclsno(Integer albclsno) {
		this.albclsno = albclsno;
	}
	public String getAuthname() {
		return authname;
	}
	public void setAuthname(String authname) {
		this.authname = authname;
	}
	public String getAlbtitle() {
		return albtitle;
	}
	public void setAlbtitle(String albtitle) {
		this.albtitle = albtitle;
	}
	public String getAlbdesc() {
		return albdesc;
	}
	public void setAlbdesc(String albdesc) {
		this.albdesc = albdesc;
	}
	public Timestamp getAlbtime() {
		return albtime;
	}
	public void setAlbtime(Timestamp albtime) {
		this.albtime = albtime;
	}
	
}