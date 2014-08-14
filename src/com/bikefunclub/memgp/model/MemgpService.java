package com.bikefunclub.memgp.model;

import java.util.List;

public class MemgpService {
	private MemgpDAO_interface dao;
	
	public MemgpService() {
		dao=new MemgpDAO();
	}
	public MemgpVO insert(MemgpVO memgpVO) {
		dao.insert(memgpVO);
		return memgpVO;
	}
	public void delete(Integer memno,Integer gpno) {
		dao.delete(memno, gpno);
	}
	public MemgpVO findByPrimaryKey(Integer memno,Integer gpno) {
		return dao.findByPrimaryKey(memno, gpno);
	}
	public List<MemgpVO> findBygpno(Integer gpno){
		return dao.findBygpno(gpno);
	}
	public List<MemgpVO> getAll() {
		return dao.getAll();
	}
}
