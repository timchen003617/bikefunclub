package com.bikefunclub.gpcls.model;

import java.util.List;

public class GpclsService {
	private GpclsDAO_interface dao;
	
	public GpclsService() {
		dao=new GpclsDAO();
	}

	public GpclsVO insert(GpclsVO gpclsVO) {
		dao.insert(gpclsVO);
		return gpclsVO;
	}

	public GpclsVO update(GpclsVO gpclsVO) {
		dao.update(gpclsVO);
		return gpclsVO;
	}

	public void delete(Integer gpclsno) {
		dao.delete(gpclsno);
	}

	public GpclsVO findByPrimaryKey(Integer gpclsno) {
		return dao.findByPrimaryKey(gpclsno);
	}

	public List<GpclsVO> getAll() {
		return dao.getAll();
	}
}
