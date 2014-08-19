package com.bikefunclub.blogcom.model;

import java.sql.Timestamp;
import java.util.List;

public class BlogcomService {

	private BlogcomDAO_interface dao;

	public BlogcomService() {
		dao = new BlogcomDAO();
	}

	public BlogcomVO addBlogcom(Integer blogno , Integer memno , String  bgcomtext) {

		BlogcomVO blogcomVO = new BlogcomVO();

		blogcomVO.setBlogno(blogno);
		blogcomVO.setMemno(memno);
		blogcomVO.setBgcomtext(bgcomtext);
		
		dao.insert(blogcomVO);

		return blogcomVO;
	}

	public BlogcomVO updateBlogcom(Integer blogno , Integer memno , String  bgcomtext , Timestamp bgcomtime , Integer bgcomno) {

		BlogcomVO blogcomVO = new BlogcomVO();

		blogcomVO.setBlogno(blogno);
		blogcomVO.setMemno(memno);
		blogcomVO.setBgcomtext(bgcomtext);
		blogcomVO.setBgcomtime(bgcomtime);
		blogcomVO.setBgcomno(bgcomno);
		
		dao.update(blogcomVO);

		return blogcomVO;
	}

	public void deleteBlogcom(Integer bgcomno) {
		dao.delete(bgcomno);
	}
	
	public void deleteBlogcom_fromFront(Integer bgcomno) {
		dao.delete_fromFront(bgcomno);
	}


	public BlogcomVO getOneBlogcom(Integer bgcomno) {
		return dao.findByPrimaryKey(bgcomno);
	}

	public List<BlogcomVO> getAll() {
		return dao.getAll();
	}
}
