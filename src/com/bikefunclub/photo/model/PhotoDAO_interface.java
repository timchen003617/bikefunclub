package com.bikefunclub.photo.model;

import java.util.*;


public interface PhotoDAO_interface {
          public void insert(PhotoVO photoVO);
          public void update(PhotoVO photoVO);
          public void delete(Integer photono);
          public PhotoVO findByPrimaryKey(Integer photono);
          public List<PhotoVO> getAll();
          //萬用複合查詢(傳入參數型態Map)(回傳 List)
//        public List<EmpVO> getAll(Map<String, String[]> map); 
        //同時新增相片與相片所屬相簿 (實務上並不常用, 但,可用在訂單主檔與明細檔一次新增成功)
	      public void insertWithGpalbum(PhotoVO photoVO , Integer albno);
}
