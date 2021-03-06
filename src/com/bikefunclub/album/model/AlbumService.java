package com.bikefunclub.album.model;

import java.sql.Timestamp;
import java.util.List;

import com.bikefunclub.albcls.model.AlbclsVO;
import com.bikefunclub.photo.model.PhotoVO;

public class AlbumService {

	private AlbumDAO_interface dao;

	public AlbumService() {
		dao = new AlbumDAO();
	}
	//新增不要建立時間,建立時間sysdate
	public AlbumVO addAlbum(Integer memno , Integer albclsno , String  authname , 
			String  albtitle , String  albdesc) {

		AlbumVO albumVO = new AlbumVO();

		albumVO.setMemno(memno);
		albumVO.setAlbclsno(albclsno);
		albumVO.setAuthname(authname);
		albumVO.setAlbtitle(albtitle);
		albumVO.setAlbdesc(albdesc);
		
		dao.insert(albumVO);

		return albumVO;
	}

	public AlbumVO updateAlbum(Integer albno , Integer memno , Integer albclsno , String  authname , 
			String  albtitle , String  albdesc , Timestamp albtime) {

		AlbumVO albumVO = new AlbumVO();

		albumVO.setAlbno(albno);
		albumVO.setMemno(memno);
		albumVO.setAlbclsno(albclsno);
		albumVO.setAuthname(authname);
		albumVO.setAlbtitle(albtitle);
		albumVO.setAlbdesc(albdesc);
		albumVO.setAlbtime(albtime);
		
		dao.update(albumVO);

		return albumVO;
	}

	public void deleteAlbum(Integer albno) {
		dao.delete(albno);
	}

	public AlbumVO getOneAlbum(Integer albno) {
		return dao.findByPrimaryKey(albno);
	}

	public List<AlbumVO> getAll() {
		return dao.getAll();
	}
	
	public List<PhotoVO> getAlbno(Integer albno) {
		return dao.getAlbno(albno);
	}
	public void deleteGpalbum(Integer photono) {
		dao.deleteGpalbum(photono);		
	}
	public String getAlbtitle(Integer albno){
		AlbumVO albumVO = dao.findByPrimaryKey(albno);
		return albumVO.getAlbtitle();
	}
	//取得該分類所有相簿;新增相簿後,轉送到該分類所有相簿
	public List<AlbumVO> getAlbclsno(Integer albclsno){
		return dao.getAlbclsno(albclsno);
	}
	//查詢相簿所屬會員
	public List<AlbumVO> getAlbumbymemno(Integer memno){
		return dao.getAlbumbymemno(memno);
	}
	//查詢相簿類別所屬會員
	public List<AlbumVO> getAlbumclsbymemno(Integer memno,Integer albclsno){
		return dao.getAlbumclsbymemno(memno, albclsno);
	}
}
