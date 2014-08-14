package com.bikefunclub.report.model;

import java.sql.*;

public class ReportVO {
	private Integer repno;
	private Integer memno;
	private Integer empno;
	private Integer byrepno;
	private String reptext;
	private String repcls;
	private Integer repclsno;
	private Date repdate;
	private Date replydate;
	private String repprogress;
	private String represult;

	public Integer getRepno() {
		return repno;
	}

	public void setRepno(Integer repno) {
		this.repno = repno;
	}

	public Integer getMemno() {
		return memno;
	}

	public void setMemno(Integer memno) {
		this.memno = memno;
	}

	public Integer getEmpno() {
		return empno;
	}

	public void setEmpno(Integer empno) {
		this.empno = empno;
	}

	public Integer getByrepno() {
		return byrepno;
	}

	public void setByrepno(Integer byrepno) {
		this.byrepno = byrepno;
	}

	public String getReptext() {
		return reptext;
	}

	public void setReptext(String reptext) {
		this.reptext = reptext;
	}

	public String getRepcls() {
		return repcls;
	}

	public void setRepcls(String repcls) {
		this.repcls = repcls;
	}

	public Integer getRepclsno() {
		return repclsno;
	}

	public void setRepclsno(Integer repclsno) {
		this.repclsno = repclsno;
	}

	public Date getRepdate() {
		return repdate;
	}

	public void setRepdate(Date repdate) {
		this.repdate = repdate;
	}

	public Date getReplydate() {
		return replydate;
	}

	public void setReplydate(Date replydate) {
		this.replydate = replydate;
	}

	public String getRepprogress() {
		return repprogress;
	}

	public void setRepprogress(String repprogress) {
		this.repprogress = repprogress;
	}

	public String getRepresult() {
		return represult;
	}

	public void setRepresult(String represult) {
		this.represult = represult;
	}

}
