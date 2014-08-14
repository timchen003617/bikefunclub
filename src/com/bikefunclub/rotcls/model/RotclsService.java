package com.bikefunclub.rotcls.model;

import java.util.List;

public class RotclsService {
	private RotclsDAO_interface dao;
	public RotclsService() {
		dao=new RotclsDAO();
	}

	public RotclsVO insert(RotclsVO rotclsVO) {
		dao.insert(rotclsVO);
		return rotclsVO;
	}

	public RotclsVO update(RotclsVO rotclsVO) {
		dao.update(rotclsVO);
		return rotclsVO;
	}

	public void delete(Integer rotclsno) {
		dao.delete(rotclsno);
	}

	public RotclsVO findByPrimaryKey(Integer rotclsno) {
		return dao.findByPrimaryKey(rotclsno);
	}

	public List<RotclsVO> getAll() {
		return dao.getAll();
	}
}
