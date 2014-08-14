package com.bikefunclub.blogcom.model;

import java.util.*;

public interface BlogcomDAO_interface {
          public void insert(BlogcomVO blogcomVO);
          public void update(BlogcomVO blogcomVO);
          public void delete(Integer bgcomno);
          public BlogcomVO findByPrimaryKey(Integer bgcomno);
          public List<BlogcomVO> getAll();
          //萬用複合查詢(傳入參數型態Map)(回傳 List)
//        public List<EmpVO> getAll(Map<String, String[]> map); 
}
