package com.kh.bts.model.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.bts.dto.MessageDto;
import com.kh.bts.model.dao.MessageDao;

@Service
public class MessageBizImpl implements MessageBiz {

	@Autowired
	private MessageDao dao;
	
	@Override
	public List<MessageDto> seletList(int chatroom_no) {
		return dao.seletList(chatroom_no);
	}

	@Override
	public MessageDto insert(MessageDto dto) {
		return dao.insert(dto);
	}

	@Override
	public int delete(int message_no) {
		return dao.delete(message_no);
	}

}
