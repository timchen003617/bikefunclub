package com.bikefunclub.Ann.model;

import java.util.*;

public interface AnnDAO_interface {
          public void insert(AnnVO annVO);
          
          public void update(AnnVO annVO);
          
          public void delete(Integer annVO);
          
          public AnnVO findByPrimaryKey(Integer annVO);
          
          public List<AnnVO> getAll();
          //�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
//        public List<EmpVO> getAll(Map<String, String[]> map); 
}
