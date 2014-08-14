package com.bikefunclub.memrot.model;

import java.util.List;

public class MemrotService {
	private MemrotDAO_interface dao;
	
    public MemrotService() {
    	dao=new MemrotDAO();
	}
	public MemrotVO insert(MemrotVO memrotVO) {
		dao.insert(memrotVO);
		return memrotVO;
	}
  public void delete(Integer memno,Integer rotno) {
	  dao.delete(memno, rotno);
  }
  public MemrotVO findByPrimaryKey(Integer memno,Integer rotno) {
	  return dao.findByPrimaryKey(memno, rotno);
  }
  public List<MemrotVO> getAll() {
	  return dao.getAll();
  }

}
