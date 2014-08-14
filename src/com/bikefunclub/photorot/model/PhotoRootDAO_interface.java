package com.bikefunclub.photorot.model;

import java.util.List;

public interface PhotoRootDAO_interface {
	public int insert(PhotoRootVO photoRootVO);

	public int update(PhotoRootVO photoRootVO);

	public int delete(Integer photono,Integer riderrecordno);

	public PhotoRootVO findByPrimaryKey(Integer photono,Integer riderrecordno);

	public List<PhotoRootVO> getAll();
}
