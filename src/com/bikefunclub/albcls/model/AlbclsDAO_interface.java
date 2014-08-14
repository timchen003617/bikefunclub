package com.bikefunclub.albcls.model;

import java.util.*;

import com.bikefunclub.album.model.AlbumVO;

public interface AlbclsDAO_interface {
          public void insert(AlbclsVO albclsVO);
          public void update(AlbclsVO albclsVO);
          public void delete(Integer albclsno);
          public AlbclsVO findByPrimaryKey(Integer albclsno);
          public List<AlbclsVO> getAll();
          public List<AlbumVO> findByAlbum(Integer albclsno);
          //查詢某部門的員工(一對多)(回傳 Set)
//        public List<EmpVO> getAll(Map<String, String[]> map); 
}
