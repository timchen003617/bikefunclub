package com.bikefunclub.chat.model;

import java.sql.Timestamp;

public class ChatVO{
	private Integer chatno;
	private Integer senderno;
	private Integer receiveno;
	private String chattext;
	private Timestamp chatdate;
    
	public Integer getChatno() {
		return chatno;
	}

	public void setChatno(Integer chatno) {
		this.chatno = chatno;
	}

	public Integer getSenderno() {
		return senderno;
	}

	public void setSenderno(Integer senderno) {
		this.senderno = senderno;
	}

	public Integer getReceiveno() {
		return receiveno;
	}

	public void setReceiveno(Integer receiveno) {
		this.receiveno = receiveno;
	}

	public String getChattext() {
		return chattext;
	}

	public void setChattext(String chattext) {
		this.chattext = chattext;
	}

	public Timestamp getChatdate() {
		return chatdate;
	}

	public void setChatdate(Timestamp chatdate) {
		this.chatdate = chatdate;
	}
}
