package com.bikefunclub.gp.model;
import java.sql.Timestamp;
public class GpVO {
	private Integer gpno;
	private Integer memno;
	private Integer gpclsno;
	private String  gptitle;
	private String  gpdesc;
	private String  gpnote;
	private Timestamp gpbuilddate;
	private Timestamp joinstartdate;
	private Timestamp joinenddate;
	private Timestamp gpstartdate;
	private Timestamp gpenddate;
	private Integer gpmaxnum;
	private Integer rotno;
	private String  gpauth;
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
	public Integer getGpclsno() {
		return gpclsno;
	}
	public void setGpclsno(Integer gpclsno) {
		this.gpclsno = gpclsno;
	}
	public String getGptitle() {
		return gptitle;
	}
	public void setGptitle(String gptitle) {
		this.gptitle = gptitle;
	}
	public String getGpdesc() {
		return gpdesc;
	}
	public void setGpdesc(String gpdesc) {
		this.gpdesc = gpdesc;
	}
	public String getGpnote() {
		return gpnote;
	}
	public void setGpnote(String gpnote) {
		this.gpnote = gpnote;
	}
	public Timestamp getGpbuilddate() {
		return gpbuilddate;
	}
	public void setGpbuilddate(Timestamp gpbuilddate) {
		this.gpbuilddate = gpbuilddate;
	}
	public Timestamp getJoinstartdate() {
		return joinstartdate;
	}
	public void setJoinstartdate(Timestamp joinstartdate) {
		this.joinstartdate = joinstartdate;
	}
	public Timestamp getJoinenddate() {
		return joinenddate;
	}
	public void setJoinenddate(Timestamp joinenddate) {
		this.joinenddate = joinenddate;
	}
	public Timestamp getGpstartdate() {
		return gpstartdate;
	}
	public void setGpstartdate(Timestamp gpstartdate) {
		this.gpstartdate = gpstartdate;
	}
	public Timestamp getGpenddate() {
		return gpenddate;
	}
	public void setGpenddate(Timestamp gpenddate) {
		this.gpenddate = gpenddate;
	}
	public Integer getGpmaxnum() {
		return gpmaxnum;
	}
	public void setGpmaxnum(Integer gpmaxnum) {
		this.gpmaxnum = gpmaxnum;
	}
	public Integer getRotno() {
		return rotno;
	}
	public void setRotno(Integer rotno) {
		this.rotno = rotno;
	}
	public String getGpauth() {
		return gpauth;
	}
	public void setGpauth(String gpauth) {
		this.gpauth = gpauth;
	}

}
