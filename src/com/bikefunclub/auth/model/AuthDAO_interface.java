package com.bikefunclub.auth.model;

import java.util.List;

public interface AuthDAO_interface {
	
	public void insert(AuthVO authVO);
	public void update(AuthVO authVO);
	public void delete(String authno);
	public AuthVO findByPrimaryKey(String authno);
	public List<AuthVO> getAll();
	
//	authno
//	empacc
//	belongauthno
//	authlevel
}
