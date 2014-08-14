package com.bikefunclub.empauth.model;

import java.util.*;

public interface Empauth_interface {
          public void insert(EmpauthVO empauthVO);
          public void update(EmpauthVO empauthVO);
          public void delete(Integer empauthVO);
          public EmpauthVO findByPrimaryKey(Integer empno);
          public List<EmpauthVO> getAll();
          //�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
//        public List<EmpVO> getAll(Map<String, String[]> map); 
}
