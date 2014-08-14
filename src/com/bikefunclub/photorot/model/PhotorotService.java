package com.bikefunclub.photorot.model;

import java.util.List;

public class PhotorotService {
	private PhotoRootDAO_interface dao;
	
	
	public PhotorotService() {
		dao = new PhotoRootDAO();
	}

	public PhotoRootVO insert(PhotoRootVO photoRootVO) {
		dao.insert(photoRootVO);
		return photoRootVO;
	}

	public PhotoRootVO update(PhotoRootVO photoRootVO) {
		dao.update(photoRootVO);
		return photoRootVO;
	}

	public void delete(Integer photono,Integer riderrecordno) {
		dao.delete(photono, riderrecordno);
	}

	public PhotoRootVO findByPrimaryKey(Integer photono,Integer riderrecordno) {
		return dao.findByPrimaryKey(photono, riderrecordno);
	}

	public List<PhotoRootVO> getAll() {
		return dao.getAll();
	}
}
