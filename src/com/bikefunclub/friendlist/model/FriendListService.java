package com.bikefunclub.friendlist.model;


import java.util.List;

public class FriendListService {

	private FriendListDAO_interface dao;

	public FriendListService() {
		dao = new FriendListDAO();
	}
	public FriendListVO addFriendList(Integer memno,Integer youno,String isblack,String isfriend) {

		FriendListVO friendlistVO = new FriendListVO();
		friendlistVO.setMemno(memno);
		friendlistVO.setYouno(youno);
		friendlistVO.setIsblack(isblack);
		friendlistVO.setIsfriend(isfriend);
		dao.insert(friendlistVO);

		return friendlistVO;
	}

	// 預留給 Struts 2 用的
	public void addFriendList(FriendListVO friendlistVO) {
		dao.insert(friendlistVO);
	}

	public FriendListVO updateFriendList(Integer memno,Integer youno,String isblack,String isfriend) {

		FriendListVO friendlistVO = new FriendListVO();
		friendlistVO.setMemno(memno);
		friendlistVO.setYouno(youno);
		friendlistVO.setIsblack(isblack);
		friendlistVO.setIsfriend(isfriend);
		dao.insert(friendlistVO);		
		dao.update(friendlistVO);

		return dao.findByPrimaryKey(memno,youno);
	}

	// 預留給 Struts 2 用的
	public void updateFriendList(FriendListVO friendListvo) {
		dao.update(friendListvo);
	}

	public void deleteFriendList(Integer memno, Integer youno) {
		dao.delete(memno,youno);
	}

	public FriendListVO getOneFriendList(Integer memno,Integer youno) {
		return dao.findByPrimaryKey(memno,youno);
	}
	public List<FriendListVO> getAllFriendList(Integer memno) {
		return dao.findAllFriends(memno);
	}
	public List<FriendListVO> getFriendListByname(Integer memno,String friname){
		return dao.findByfriname(memno,friname);
	}
	public List<FriendListVO> getAll() {
		return dao.getAll();
	}
}
