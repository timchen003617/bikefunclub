package com.bikefunclub.photoGP.model;

import java.util.List;



public interface PhotoGPDAO_interface {
	public int insert(PhotoGPVO photoGPVO);

	public int update(PhotoGPVO photoGPVO);

	public int delete(Integer gpno,Integer photono);

	public PhotoGPVO findByPrimaryKey(Integer gpno,Integer photono);

	public List<PhotoGPVO> getAll();
}
