package com.kh.bts.model.biz;

import java.util.List;

import com.kh.bts.dto.MessageDto;

public interface MessageBiz {

	public List<MessageDto> seletList(int chatroom_no);
	public MessageDto insert(MessageDto dto);
	public int delete(int message_no);
}
