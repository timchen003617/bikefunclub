package com.bikefunclub.blogcls.model;

import java.util.*;

public interface BlogclsDAO_interface {
          public void insert(BlogclsVO blogclsVO);
          public void update(BlogclsVO blogclsVO);
          public void delete(Integer blogclsno);
          public BlogclsVO findByPrimaryKey(Integer blogclsno);
          public List<BlogclsVO> getAll();
        //萬用複合查詢(傳入參數型態Map)(回傳 List)
//        public List<EmpVO> getAll(Map<String, String[]> map); 
}
