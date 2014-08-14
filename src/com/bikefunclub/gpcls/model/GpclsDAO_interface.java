package com.bikefunclub.gpcls.model;

import java.util.*;

public interface GpclsDAO_interface {
	public int insert(GpclsVO gpclsVO);

	public int update(GpclsVO gpclsVO);

	public int delete(Integer gpclsno);

	public GpclsVO findByPrimaryKey(Integer gpclsno);

	public List<GpclsVO> getAll();
}
