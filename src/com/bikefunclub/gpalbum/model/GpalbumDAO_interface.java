package com.bikefunclub.gpalbum.model;

import java.util.*;





public interface GpalbumDAO_interface {
          public void insert(GpalbumVO gpalbumVO);
        //public void update(GpalbumVO gpalbumVO);
          public void delete(Integer albno,Integer photono);
          public GpalbumVO findByAlbno(Integer albno);
          public List<GpalbumVO> getAll();
        //查詢某部門的員工(一對多)(回傳 Set)
//        public List<EmpVO> getAll(Map<String, String[]> map); 
          public void insert2 (Integer albno , Integer photono , java.sql.Connection con);
}
