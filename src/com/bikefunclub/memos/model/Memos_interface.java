package com.bikefunclub.memos.model;

import java.util.*;

public interface Memos_interface {
          public void insert(MemosVO memosVO);
          public void update(MemosVO memosVO);
          public void delete(Integer memosVO);
          public MemosVO findByPrimaryKey(Integer memosVO);
          public List<MemosVO> getAll();
          //�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
//        public List<EmpVO> getAll(Map<String, String[]> map); 
}
