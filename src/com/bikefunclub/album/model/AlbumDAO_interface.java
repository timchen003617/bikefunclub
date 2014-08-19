package com.bikefunclub.album.model;

import java.util.*;

import com.bikefunclub.albcls.model.AlbclsVO;
import com.bikefunclub.album.model.AlbumVO;
import com.bikefunclub.photo.model.PhotoVO;

public interface AlbumDAO_interface {
          public void insert(AlbumVO albumVO);
          public void update(AlbumVO albumVO);
          public void delete(Integer albno);
          public void deleteGpalbum(Integer photono);
          public AlbumVO findByPrimaryKey(Integer albno);
          public List<AlbumVO> getAll();
          public List<PhotoVO> getAlbno(Integer albno);
        //取得該分類所有相簿;新增相簿後,轉送到該分類所有相簿
          public List<AlbumVO> getAlbclsno(Integer albclsno);
          //查詢會員的相簿
          public List<AlbumVO> getAlbumbymemno(Integer memno);
          //查詢會員的分類相簿,
          public List<AlbumVO> getAlbumclsbymemno(Integer memno,Integer albno);         
        //查詢某部門的員工(一對多)(回傳 Set)
//        public List<EmpVO> getAll(Map<String, String[]> map); 
}
