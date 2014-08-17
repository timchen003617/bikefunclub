package com.bikefunclub.member.model;

import java.util.*;

public interface Mem_interface {
	//會員註冊
	public void insert(MemVO memVO);
	//會員修改個人資料
	public void update(MemVO memVO);
	//信箱認證
	public void update_getmailyn(MemVO memVO);
	
	public void delete(Integer memno);

	public MemVO findByPrimaryKey(Integer memVO);

	public MemVO findByAccount(String Account);

	public List<MemVO> getAll();
	//查詢未加入好友的會員，包含自己
	public List<MemVO> findByAllfornewfri(Integer memno);
	//帶入會員姓名，查詢未加入好友的會員
	public List<MemVO> findByAllfornewfriByname(Integer memno,String memname);
	// 萬用複合查詢(傳入參數型態Map)(回傳 List)
	// public List<EmpVO> getAll(Map<String, String[]> map);
}
