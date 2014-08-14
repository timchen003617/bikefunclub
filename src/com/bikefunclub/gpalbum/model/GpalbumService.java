package com.bikefunclub.gpalbum.model;

import java.sql.Connection;
import java.util.List;

public class GpalbumService {

	private GpalbumDAO_interface dao;

	public GpalbumService() {
		dao = new GpalbumDAO();
	}

	public GpalbumVO addGpalbum(Integer albno, Integer photono) {

		GpalbumVO gpalbumVO = new GpalbumVO();

		gpalbumVO.setAlbno(albno);
		gpalbumVO.setPhotono(photono);
		
		dao.insert(gpalbumVO);

		return gpalbumVO;
	}

//	public GpalbumVO updeGpalbum(Integer albno , Integer photono) {
//
//		GpalbumVO gpalbumVO = new GpalbumVO();
//
//		gpalbumVO.setAlbno(albno);
//		gpalbumVO.setPhotono(photono);
//		
//		dao.update(gpalbumVO);
//
//		return gpalbumVO;
//	}

	public void deleteGpalbum(Integer albno , Integer photono) {
		dao.delete(albno , photono);
	}

	public GpalbumVO getOneAlbno(Integer albno) {
		return dao.findByAlbno(albno);
	}

	public List<GpalbumVO> getAll() {
		return dao.getAll();
	}
	
	 public void insert2(Integer albno , Integer photono , Connection con){
		 dao.insert2 (albno , photono , con);
	 }
}
