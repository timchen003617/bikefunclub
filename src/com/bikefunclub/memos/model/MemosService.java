package com.bikefunclub.memos.model;

import java.util.List;

public class MemosService {

	private Memos_interface dao;

	public MemosService() {
		dao = new MemosJDBCDAO();
	}

	public MemosVO addEmp(String memcoo, Integer memno) {

		MemosVO memosVO = new MemosVO();

		memosVO.setMemcoo(memcoo);
		memosVO.setMemno(memno);
		dao.insert(memosVO);

		return memosVO;
	}

	public MemosVO updateEmp(Integer memno, String memcoo) {

		MemosVO memosVO = new MemosVO();

		memosVO.setMemno(memno);
		memosVO.setMemcoo(memcoo);
		dao.update(memosVO);

		return memosVO;
	}

	public void deleteEmp(Integer memosVO) {
		dao.delete(memosVO);
	}

	public MemosVO getOneEmp(Integer memosVO) {
		return dao.findByPrimaryKey(memosVO);
	}

	public List<MemosVO> getAll() {
		return dao.getAll();
	}
}
