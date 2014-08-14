package com.bikefunclub.albcls.model;

import java.util.List;

import com.bikefunclub.album.model.AlbumVO;


public class AlbclsService {

	private AlbclsDAO_interface dao;

	public AlbclsService() {
		dao = new AlbclsDAO();
	}

	public AlbclsVO addAlbcls(String  albclsname) {

		AlbclsVO albclsVO = new AlbclsVO();

		albclsVO.setAlbclsname(albclsname);
		dao.insert(albclsVO);

		return albclsVO;
	}

	public AlbclsVO updateAlbcls(Integer albclsno , String  albclsname) {

		AlbclsVO albclsVO = new AlbclsVO();

		albclsVO.setAlbclsno(albclsno);
		albclsVO.setAlbclsname(albclsname);
		dao.update(albclsVO);

		return albclsVO;
	}

	public void deleteAlbcls(Integer albclsno) {
		dao.delete(albclsno);
	}

	public AlbclsVO getOneAlbcls(Integer albclsno) {
		return dao.findByPrimaryKey(albclsno);
	}

	public List<AlbclsVO> getAll() {
		return dao.getAll();
	}
	
	public List<AlbumVO> findByAlbum(Integer albclsno){
		return dao.findByAlbum(albclsno);
	}
}
