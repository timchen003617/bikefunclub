package com.bikefunclub.gp.model;

import java.util.List;

public class GpService {
	
	private GpDAO_interface dao;
    public GpService() {
    	dao=new GpDAO();
	}
	public GpVO insert(GpVO gpVO) {
		Integer gpno;
		gpno=dao.insert(gpVO);
		return dao.findByPrimaryKey(gpno);
	}
    public GpVO update(GpVO gpVO) {
		dao.update(gpVO);
    	return gpVO;
	}
    public int updateGpByGpclsno(Integer gpclsno,Integer gpno) {
    	return dao.updateGpByGpclsno(gpclsno, gpno);
	}
    public void delete_ByMem(Integer gpno) {
    	dao.delete_ByMem(gpno);
    }//發起人改成''
    public void delete(Integer gpno) {
    	dao.delete(gpno);
    }//先刪關係後刪揪團
    public GpVO findByPrimaryKey(Integer gpno) {
		return dao.findByPrimaryKey(gpno);
	}
    public List<GpVO> getGpsBymemno(Integer memno) {
		return dao.getGpsBymemno(memno);
	}//會員發起的揪團
    public List<GpVO> getGpsBygpclsno(Integer gpclsno) {
		return dao.getGpsBygpclsno(gpclsno);
	}//從揪團分類找揪團  
    public List<GpVO> getGpsBymemnoFromMemgp(Integer memno) {
		return dao.getGpsBymemnoFromMemgp(memno);
	}//參加的揪團.

    public List<GpVO> getAll() {
		return dao.getAll();
	}
	public List<GpVO> getGpsFromRep() {
		return dao.getGpsFromrep();
	}
}
