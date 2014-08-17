package com.bikefunclub.blog.model;

import java.util.*;

import com.bikefunclub.gp.model.GpVO;

public interface BlogDAO_interface {
          public void insert(BlogVO blogVO);
          public void update(BlogVO blogVO);
          public void delete(Integer blogno);
          public BlogVO findByPrimaryKey(Integer blogno);
          public List<BlogVO> getAll();
          public List<BlogVO> getBlogs_frommemno(Integer memno);
          public List<GpVO> getBlogsBymemno(Integer memno);//會員發起的網誌
          public List<GpVO> getBlogsByblogclsno(Integer blogclsno);//從網誌分類找網誌  
          public List<GpVO> getBlogsBymemnoFromMemblog(Integer memno);//參加的揪團.
          //萬用複合查詢(傳入參數型態Map)(回傳 List)
//        public List<EmpVO> getAll(Map<String, String[]> map); 
}
