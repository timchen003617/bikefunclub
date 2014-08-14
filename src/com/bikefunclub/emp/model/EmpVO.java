package com.bikefunclub.emp.model;

import java.sql.Timestamp;


public class EmpVO implements java.io.Serializable{
	private Integer empno;
	private String  empacc;
	private String  emppw;
	private String  empname;
	private String  empemail;
	private String  empaddr;
	private String  empid;
	private Timestamp  emprgdate;
	private String  emptel;
	//get and set
	public Integer getEmpno() {
		return empno;
	}
	public void setEmpno(Integer empno) {
		this.empno = empno;
	}
	public String getEmpacc() {
		return empacc;
	}
	public void setEmpacc(String empacc) {
		this.empacc = empacc;
	}
	public String getEmppw() {
		return emppw;
	}
	public void setEmppw(String emppw) {
		this.emppw = emppw;
	}
	public String getEmpname() {
		return empname;
	}
	public void setEmpname(String empname) {
		this.empname = empname;
	}
	public String getEmpemail() {
		return empemail;
	}
	public void setEmpemail(String empemail) {
		this.empemail = empemail;
	}
	public String getEmpaddr() {
		return empaddr;
	}
	public void setEmpaddr(String empaddr) {
		this.empaddr = empaddr;
	}
	public String getEmpid() {
		return empid;
	}
	public void setEmpid(String empid) {
		this.empid = empid;
	}
	public Timestamp getEmprgdate() {
		return emprgdate;
	}
	public void setEmprgdate(Timestamp emprgdate) {
		this.emprgdate = emprgdate;
	}
	public String getEmptel() {
		return emptel;
	}
	public void setEmptel(String emptel) {
		this.emptel = emptel;
	}
   
	
	
}
