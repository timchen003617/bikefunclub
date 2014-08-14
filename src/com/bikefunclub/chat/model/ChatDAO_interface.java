package com.bikefunclub.chat.model;

import java.util.*;

public interface ChatDAO_interface {
	public void insert(ChatVO chatVO);
	public void update(ChatVO chatVO);
	public void delete(Integer chatno);
	public ChatVO findByPrimaryKey(Integer chatno);
	public List<ChatVO> getAll();
}
