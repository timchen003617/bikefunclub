package com.bikefunclub.auth.model;

import java.util.List;

public class AuthService {

	private AuthDAO_interface dao;

	public AuthService() {
		dao = new AuthDAO();
	}
	public AuthVO addAuth(String authno, String authname, String authurl,
			String belongauthno,String authlevel) {

		AuthVO authVO = new AuthVO();
		
		authVO.setAuthno(authno);
		authVO.setAuthname(authname);
		authVO.setAuthurl(authurl);
		authVO.setBelongauthno(belongauthno);
		authVO.setAuthlevel(authlevel);
		dao.insert(authVO);

		return authVO;
	}

	// 預留給 Struts 2 用的
	public void addAuth(AuthVO authVO) {
		dao.insert(authVO);
	}

	public AuthVO updateAuth(String authno, String authname, String authurl,
			String belongauthno,String authlevel) {

		AuthVO authVO = new AuthVO();
		
		authVO.setAuthno(authno);
		authVO.setAuthname(authname);
		authVO.setAuthurl(authurl);
		authVO.setBelongauthno(belongauthno);
		authVO.setAuthlevel(authlevel);
		dao.update(authVO);

		return dao.findByPrimaryKey(authno);
	}

	// 預留給 Struts 2 用的
	public void updateAuth(AuthVO authVO) {
		dao.update(authVO);
	}

	public void deleteAuth(String authno) {
		dao.delete(authno);
	}

	public AuthVO getOneAuth(String authno) {
		return dao.findByPrimaryKey(authno);
	}

	public List<AuthVO> getAll() {
		return dao.getAll();
	}
}
