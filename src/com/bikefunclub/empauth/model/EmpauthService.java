package com.bikefunclub.empauth.model;

import java.util.List;

public class EmpauthService {

	private Empauth_interface dao;

	public EmpauthService() {
		dao = new EmpauthDAO();
	}

	public EmpauthVO addEmp(Integer empno, String authno) {

		EmpauthVO empauthVO = new EmpauthVO();

		empauthVO.setEmpno(empno);
		empauthVO.setAuthno(authno);
		
		dao.insert(empauthVO);

		return empauthVO;
	}

	public EmpauthVO updateEmp(Integer empno,String authno) {

		EmpauthVO empauthVO = new EmpauthVO();

		empauthVO.setEmpno(empno);
		empauthVO.setAuthno(authno);
		dao.update(empauthVO);

		return empauthVO;
	}

	public void deleteEmp(Integer empno) {
		dao.delete(empno);
	}

	public EmpauthVO getOneEmp(Integer empno) {
		return dao.findByPrimaryKey(empno);
	}

	public List<EmpauthVO> getAll() {
		return dao.getAll();
	}
}
