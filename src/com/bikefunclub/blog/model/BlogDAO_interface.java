package com.bikefunclub.blog.model;

import java.util.*;

public interface BlogDAO_interface {
          public void insert(BlogVO blogVO);
          public void update(BlogVO blogVO);
          public void delete(Integer blogno);
          public BlogVO findByPrimaryKey(Integer blogno);
          public List<BlogVO> getAll();
          public List<BlogVO> getBlogs_frommemno(Integer memno);
          //萬用複合查詢(傳入參數型態Map)(回傳 List)
//        public List<EmpVO> getAll(Map<String, String[]> map); 
}
