package com.bikefunclub.album.model;

import java.util.*;

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
        //查詢某部門的員工(一對多)(回傳 Set)
//        public List<EmpVO> getAll(Map<String, String[]> map); 
}
