package com.bikefunclub.chat.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.directwebremoting.Security;

import com.bikefunclub.chat.model.ChatVO;
import com.bikefunclub.member.model.*;

public class Chat {
	private static LinkedList<ChatVO> messages = new LinkedList<ChatVO>();
	private static Map<Integer, MemVO> memMap = new LinkedHashMap<Integer, MemVO>();
//	private static Map<Set<Integer>, LinkedList<ChatVO>> msggg;
	public Chat() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Chat(Integer memno, MemVO memVO) {//參數:發訊人(登入)會員編號、會員vo
		
		Chat.memMap.put(memno, memVO);
	}

	public List<ChatVO> addMessage(Integer sendermemno, String text) {
		if (text != null && text.trim().length() > 0) {
			// 作業系統時間
			Date date = new Date(System.currentTimeMillis());
			Timestamp chatdate = Timestamp
					.valueOf(new java.text.SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss").format(date));
			// 發送人姓名+訊息內容
			text = getMemname(sendermemno) + text;

			if (text.length() > 256) {
				text = text.substring(0, 256);
			}

			text = Security.replaceXmlCharacters(text);

			ChatVO chatVO = new ChatVO();

			chatVO.setChattext(text);// 訊息內容
			chatVO.setSenderno(sendermemno);// 發訊人會員編號
			chatVO.setChatdate(chatdate);// 發送時間
			
			messages.addLast(chatVO);
			while (messages.size() > 100) {
				messages.removeLast();
			}

		}
		return messages;
	}

	public List<ChatVO> getMessages() {
		return messages;
	}

	public String getMemname(Integer sendermemno) {
		MemVO memVO= memMap.get(sendermemno);
		String memname = memVO.getMemname();
		return memname + ":";
	}
}