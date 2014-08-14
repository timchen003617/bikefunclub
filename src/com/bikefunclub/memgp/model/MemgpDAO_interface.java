package com.bikefunclub.memgp.model;

import java.util.List;


public interface MemgpDAO_interface {
  public void insert(MemgpVO memgpVO);
  public void delete(Integer memno,Integer gpno);
  public MemgpVO findByPrimaryKey(Integer memno,Integer gpno);
  //帶入揪團編號，查詢出所有參加會員
  public List<MemgpVO> findBygpno(Integer gpno);
  public List<MemgpVO> getAll();
}
