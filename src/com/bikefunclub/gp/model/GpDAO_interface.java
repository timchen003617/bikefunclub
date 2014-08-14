package com.bikefunclub.gp.model;

import java.util.List;

public interface GpDAO_interface {
    public int insert(GpVO gpVO);
    public void update(GpVO gpVO);
    public int updateGpByGpclsno(Integer gpclsno,Integer gpno);    
    public void delete_ByMem(Integer gpno);//發起人改成''
    public void delete(Integer gpno);//先刪關係後刪揪團
    public GpVO findByPrimaryKey(Integer gpno);    
    public List<GpVO> getGpsBymemno(Integer memno);//會員發起的揪團
    public List<GpVO> getGpsBygpclsno(Integer gpclsno);//從揪團分類找揪團  
    public List<GpVO> getGpsBymemnoFromMemgp(Integer memno);//參加的揪團.
    public List<GpVO> getGpsFromrep();//列出被檢舉的揪團.
    public List<GpVO> getAll();
}
