package com.bikefunclub.blogcls.model;

import java.util.List;

public class BlogclsService {

	private BlogclsDAO_interface dao;

	public BlogclsService() {
		dao = new BlogclsDAO();
	}

	public BlogclsVO addBlogcls(String  blogclsname) {

		BlogclsVO blogclsVO = new BlogclsVO();

		blogclsVO.setBlogclsname(blogclsname);
		
		dao.insert(blogclsVO);

		return blogclsVO;
	}

	public BlogclsVO updateBlogcls(String  blogclsname , Integer blogclsno) {

		BlogclsVO blogclsVO = new BlogclsVO();

		blogclsVO.setBlogclsname(blogclsname);
		blogclsVO.setBlogclsno(blogclsno);
		
		dao.update(blogclsVO);

		return blogclsVO;
	}

	public void deleteBlogcls(Integer blogclsno) {
		dao.delete(blogclsno);
	}

	public BlogclsVO getOneBlogcls(Integer blogclsno) {
		return dao.findByPrimaryKey(blogclsno);
	}

	public List<BlogclsVO> getAll() {
		return dao.getAll();
	}
}
