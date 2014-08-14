package com.bikefunclub.ad.model;

import java.util.*;

public interface AdDAO_interface {
	public void insert(AdVO advo);

	public void update(AdVO advo);

	public void delete(Integer advo);

	public AdVO findByPrimaryKey(Integer adVO);

	public List<AdVO> getAll();
}
