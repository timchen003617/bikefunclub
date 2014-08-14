package com.bikefunclub.photoGP.model;

import java.util.List;

public class PhotogpService {
	
	private PhotoGPDAO_interface dao;
	public PhotogpService() {
		dao=new PhotoGPDAO();
	}

	public PhotoGPVO insert(PhotoGPVO photoGPVO) {
		dao.insert(photoGPVO);
		return photoGPVO;
	}

	public PhotoGPVO update(PhotoGPVO photoGPVO) {
		dao.update(photoGPVO);
		return photoGPVO;
	}

	public void delete(Integer gpno,Integer photono) {
		dao.delete(gpno, photono);
	}

	public PhotoGPVO findByPrimaryKey(Integer gpno,Integer photono) {
		return dao.findByPrimaryKey(gpno, photono);
	}

	public List<PhotoGPVO> getAll() {
		return dao.getAll();
	}
}
