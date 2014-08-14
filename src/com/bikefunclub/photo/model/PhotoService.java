package com.bikefunclub.photo.model;

import java.sql.Timestamp;
import java.util.List;

import com.bikefunclub.gpalbum.model.GpalbumVO;

public class PhotoService {

	private PhotoDAO_interface dao;

	public PhotoService() {
		dao = new PhotoDAO();
	}

	public PhotoVO addPhoto(Integer memno , Integer phass , String  phfilename , String  phextname
			                 , byte[] phfile) {

		PhotoVO photoVO = new PhotoVO();

		photoVO.setMemno(memno);
		photoVO.setPhass(phass);
		photoVO.setPhfilename(phfilename);
		photoVO.setPhextname(phextname);
		photoVO.setPhfile(phfile);
		
		dao.insert(photoVO);

		return photoVO;
	}

	public PhotoVO updatePhoto(Integer photono , Integer memno , Integer phass , String  phfilename , String  phextname
            , Timestamp phup , byte[] phfile) {

		PhotoVO photoVO = new PhotoVO();

		photoVO.setPhotono(photono);
		photoVO.setMemno(memno);
		photoVO.setPhass(phass);
		photoVO.setPhfilename(phfilename);
		photoVO.setPhextname(phextname);
		photoVO.setPhup(phup);
		photoVO.setPhfile(phfile);
		
		dao.update(photoVO);

		return photoVO;
	}

	public void insertWithGpalbum(PhotoVO photoVO , Integer albno){
		dao.insertWithGpalbum(photoVO,albno);		
	}
	
	public void deletePhoto(Integer photono) {
		dao.delete(photono);
	}

	public PhotoVO getOnePhoto(Integer photono) {
		return dao.findByPrimaryKey(photono);
	}

	public List<PhotoVO> getAll() {
		return dao.getAll();
	}
}
