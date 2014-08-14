package com.bikefunclub.emp.model;

import java.util.*;

public interface EmpDAO_interface {
          public void insert(EmpVO empVO);
          public void update(EmpVO empVO);
          public void delete(Integer empno);
          public EmpVO findByPrimaryKey(Integer empno);
          public EmpVO findByAccount(String Account);
          public List<EmpVO> getAll();
          //�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
//        public List<EmpVO> getAll(Map<String, String[]> map); 
}
