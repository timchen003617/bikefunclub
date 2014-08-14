package com.bikefunclub.memrot.model;
import java.util.*;


public interface MemrotDAO_interface {
	
    public void insert(MemrotVO memrotVO);
//    public void update(MemrotVO memrotVO);
    public void delete(Integer memno,Integer rotno);
    public MemrotVO findByPrimaryKey(Integer memno,Integer rotno);
    public List<MemrotVO> getAll();
}
