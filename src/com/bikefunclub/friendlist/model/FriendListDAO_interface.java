package com.bikefunclub.friendlist.model;

import java.util.*;

public interface FriendListDAO_interface {
	public void insert(FriendListVO friendListVo);
	public void update(FriendListVO friendListVo);
	//刪除好友間的單筆關係
	public void delete(Integer memno,Integer youno);
	//查詢會員的單一好友，帶入登入會員編號，好友會員編號
	public FriendListVO findByPrimaryKey(Integer memno,Integer youno);
	//查詢會員的好友，帶入會員編號、好友會員姓名(模糊比對)
	public List<FriendListVO> findByfriname(Integer memno,String friname);	
	//查詢會員的所有好友
	public List<FriendListVO> findAllFriends(Integer memno);
	//查詢所有會員的所有好友
	public List<FriendListVO> getAll();
}
