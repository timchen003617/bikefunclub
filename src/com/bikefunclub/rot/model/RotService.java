package com.bikefunclub.rot.model;

import java.util.List;

public class RotService {
	private RotDAO_interface dao;
	public RotService(){
		dao=new RotDAO();
	}
    public RotVO insert(RotVO rotVO) {
		Integer rotno;
    	rotno=dao.insert(rotVO);
  	
    	return dao.findByPrimaryKey(rotno);
	}
    public RotVO update(RotVO rotVO) {
    	dao.update(rotVO);
		return rotVO;
	}
    public void delete_ByMem(Integer rotno) {
    	dao.delete_ByMem(rotno);
	}//設狀態為'0'
    public void delete_Mem(Integer rotno) {
    	dao.delete_Mem(rotno);
	}//設MEMNO=''
    public void delete(Integer rotno) {
    	dao.delete(rotno);
	}//先刪關係後刪路線
    public RotVO findByPrimaryKey(Integer rotno) {
		return dao.findByPrimaryKey(rotno);
	}//從路線編號搜尋
    public List<RotVO> getRotsBymemno(Integer memno) {
		return dao.getRotsBymemno(memno);
	}//從編輯人員搜尋
    public List<RotVO> getRotsByrotclsno(Integer rotclsno) {
		return dao.getRotsByrotclsno(rotclsno);
	}//從類別搜尋  
    public List<RotVO> getRotsByrotclsnofromback(Integer rotclsno) {
		return dao.getRotsByrotclsnofromback(rotclsno);
	}//從類別搜尋 
    public List<RotVO> getrotsBymemnoFromMemrot(Integer memno) {
		return dao.getrotsBymemnoFromMemrot(memno);
	}//會員收藏的路線
    public List<RotVO> getAll() {
		return dao.getAll();
	}
    public List<RotVO> getAllSg(){
		return dao.getAllSg();
	}
}
