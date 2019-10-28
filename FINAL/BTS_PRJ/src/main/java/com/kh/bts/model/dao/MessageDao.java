package com.kh.bts.model.dao;

import java.util.List;

import com.kh.bts.dto.MessageDto;

public interface MessageDao {
	
	String namespace = "message.";
	
	public List<MessageDto> seletList(int chatroom_no);
	public MessageDto insert(MessageDto dto);
	public int delete(int message_no);
}
