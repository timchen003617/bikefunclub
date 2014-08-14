package com.bikefunclub.rotcls.model;

import java.util.*;

public interface RotclsDAO_interface {
	public int insert(RotclsVO rotclsVO);

	public int update(RotclsVO rotclsVO);

	public int delete(Integer rotclsno);

	public RotclsVO findByPrimaryKey(Integer rotclsno);

	public List<RotclsVO> getAll();
}
