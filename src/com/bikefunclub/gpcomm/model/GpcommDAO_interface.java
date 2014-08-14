package com.bikefunclub.gpcomm.model;

import java.util.List;

public interface GpcommDAO_interface {
  public void insert(GpcommVO gpcommVO);
  public void delete(Integer gpcommno);
  public GpcommVO findByPrimaryKey(Integer gpcommno);
  public List<GpcommVO> getGpcommsBygpno(Integer gpno);
  public List<GpcommVO> getAll();
}
