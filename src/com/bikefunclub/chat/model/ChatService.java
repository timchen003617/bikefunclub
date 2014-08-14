package com.bikefunclub.chat.model;

import java.sql.Timestamp;
import java.util.List;


public class ChatService {

	private ChatDAO_interface dao;

	public ChatService() {
		dao = new ChatDAO();
	}
	public ChatVO addChat(Integer senderno, Integer receiveno,
			String chattext, Timestamp chatdate) {

		ChatVO chatVO = new ChatVO();

		chatVO.setSenderno(senderno);
		chatVO.setReceiveno(receiveno);
		chatVO.setChattext(chattext);
		chatVO.setChatdate(chatdate);
		dao.insert(chatVO);

		return chatVO;
	}

	// 預留給 Struts 2 用的
	public void addChat(ChatVO chatVO) {
		dao.insert(chatVO);
	}

	public ChatVO updateChat(Integer chatno, Integer senderno, Integer receiveno,
			String chattext, Timestamp chatdate) {

		ChatVO chatVO = new ChatVO();
		
		chatVO.setChatno(chatno);
		chatVO.setSenderno(senderno);
		chatVO.setReceiveno(receiveno);
		chatVO.setChattext(chattext);
		chatVO.setChatdate(chatdate);
		dao.update(chatVO);

		return dao.findByPrimaryKey(chatno);
	}

	// 預留給 Struts 2 用的
	public void updateChat(ChatVO chatVO) {
		dao.update(chatVO);
	}

	public void deleteChat(Integer chatno) {
		dao.delete(chatno);
	}

	public ChatVO getOneChat(Integer chatno) {
		return dao.findByPrimaryKey(chatno);
	}

	public List<ChatVO> getAll() {
		return dao.getAll();
	}
}
