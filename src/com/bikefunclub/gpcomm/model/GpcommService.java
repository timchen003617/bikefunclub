package com.bikefunclub.gpcomm.model;

import java.util.List;

public class GpcommService {
	private GpcommDAO_interface dao;
	public GpcommService() {
		dao=new GpcommDAO();
	}
	public GpcommVO insert(GpcommVO gpcommVO) {
		dao.insert(gpcommVO);
		return gpcommVO;
	}
	  public void delete(Integer gpcommno) {
		  dao.delete(gpcommno);
	}
	  public GpcommVO findByPrimaryKey(Integer gpcommno) {
		return dao.findByPrimaryKey(gpcommno);
	}
	  public List<GpcommVO> getGpcommsBygpno(Integer gpno) {
		return dao.getGpcommsBygpno(gpno);
	}
	  public List<GpcommVO> getAll() {
		return dao.getAll();
	}
}
