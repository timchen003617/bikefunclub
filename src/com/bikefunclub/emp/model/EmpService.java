package com.bikefunclub.emp.model;

import java.sql.Timestamp;
import java.util.List;

import com.bikefunclub.member.model.MemVO;

public class EmpService {

	private EmpDAO_interface dao;

	public EmpService() {
		dao = new EmpDAO();
	}

	public EmpVO addEmp(String empacc, String emppw, String empname,
			String empemail, String empaddr, String empid,Timestamp emprgdate,String emptel) {

		EmpVO empVO = new EmpVO();

		empVO.setEmpacc(empacc);
		empVO.setEmppw(emppw);
		empVO.setEmpname(empname);
		empVO.setEmpemail(empemail);
		empVO.setEmpaddr(empaddr);
		empVO.setEmpid(empid);
		empVO.setEmprgdate(emprgdate);
		empVO.setEmptel(emptel);
		
	
		dao.insert(empVO);

		return empVO;
	}

	public EmpVO updateEmp(Integer empno, String empacc,/*String emppw,*/String empname,
			String empemail, String empaddr, String empid,Timestamp emprgdate,String emptel) {

		EmpVO empVO = new EmpVO();

		empVO.setEmpno(empno);
		empVO.setEmpacc(empacc);
//		empVO.setEmppw(emppw);
		empVO.setEmpname(empname);
		empVO.setEmpemail(empemail);
		empVO.setEmpaddr(empaddr);
		empVO.setEmpid(empid);
	    empVO.setEmprgdate(emprgdate);
		empVO.setEmptel(emptel);
		dao.update(empVO);

		return empVO;
	}

	public void deleteEmp(Integer empno) {
		dao.delete(empno);
	}

	public EmpVO getOneEmp(Integer empno) {
		return dao.findByPrimaryKey(empno);
	}
	
	public EmpVO findByAccount(String Account){
		return dao.findByAccount(Account);
	}

	public List<EmpVO> getAll() {
		return dao.getAll();
	}
}
