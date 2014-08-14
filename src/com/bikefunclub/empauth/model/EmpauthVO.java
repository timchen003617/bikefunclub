package com.bikefunclub.empauth.model;
import java.sql.Date;

public class EmpauthVO implements java.io.Serializable{
     private Integer empno;
     private String  authno;
	public Integer getEmpno() {
		return empno;
	}
	public void setEmpno(Integer empno) {
		this.empno = empno;
	}
	public String getAuthno() {
		return authno;
	}
	public void setAuthno(String authno) {
		this.authno = authno;
	}

	
	
}
