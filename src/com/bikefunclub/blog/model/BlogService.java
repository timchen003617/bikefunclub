package com.bikefunclub.blog.model;

import java.sql.Timestamp;
import java.util.List;

public class BlogService {

	private BlogDAO_interface dao;

	public BlogService() {
		dao = new BlogDAO();
	}
	//新增不要建立時間,建立時間sysdate
	public BlogVO addBlog(Integer memno , Integer blogclsno , String  bgtitle , String  bgtext , String  authname) {

		BlogVO blogVO = new BlogVO();

		blogVO.setMemno(memno);
		blogVO.setBlogclsno(blogclsno);
		blogVO.setBgtitle(bgtitle);
		blogVO.setBgtext(bgtext);
		blogVO.setAuthname(authname);
		
		dao.insert(blogVO);

		return blogVO;
	}

	public BlogVO updateBlog(Integer blogno , Integer memno , Integer blogclsno , String  bgtitle , String  bgtext , 
			                 String  authname , Timestamp bgtime) {

		BlogVO blogVO = new BlogVO();

		blogVO.setBlogno(blogno);
		blogVO.setMemno(memno);
		blogVO.setBlogclsno(blogclsno);
		blogVO.setBgtitle(bgtitle);
		blogVO.setBgtext(bgtext);
		blogVO.setAuthname(authname);
		blogVO.setBgtime(bgtime);
		
		dao.update(blogVO);

		return blogVO;
	}
    
	//先刪除關係，再刪除網誌本身
	public void deleteBlog(Integer blogno) {
		dao.delete(blogno);
	}

	public BlogVO getOneBlog(Integer blogno) {
		return dao.findByPrimaryKey(blogno);
	}

	public List<BlogVO> getAll() {
		return dao.getAll();
	}
	
	public List<BlogVO> getBlogs_frommemno(Integer memno){
	     return	dao.getBlogs_frommemno(memno);
	    }
	
	public BlogVO findByPrimaryKey(Integer blogno) {
	return dao.findByPrimaryKey(blogno);
	}
	//先刪關係後刪網誌
    public void delete(Integer blogno) {
		dao.delete(blogno);
		
	}
	
	
	
}
