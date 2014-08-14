package com.bikefunclub.rot.model;

import java.util.*;

public interface RotDAO_interface {
    public int insert(RotVO rotVO);
    public void update(RotVO rotVO);
    public void delete_ByMem(Integer rotno);//設rotstatus為''
    public void delete_Mem(Integer rotno);//設編輯人員為''
    public void delete(Integer rotno);//先刪關係後刪路線
    public RotVO findByPrimaryKey(Integer rotno);//從路線編號搜尋
    public List<RotVO> getRotsBymemno(Integer memno);//從編輯人員搜尋
    public List<RotVO> getRotsByrotclsno(Integer rotclsno);//從類別搜尋  
    public List<RotVO> getrotsBymemnoFromMemrot(Integer memno);//會員收藏的路線
    public List<RotVO> getRotsByrotclsnofromback(Integer rotclsno);
    public List<RotVO> getAll();
    public List<RotVO> getAllSg();
}
