package com.kh.bts.model.biz;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.bts.model.dao.ChatRoomDao;

@Service
public class ChatRoomBizImpl implements ChatRoomBiz {

	@Autowired
	ChatRoomDao dao;

	@Override
	public int insertAndRoomNoReturn() {
		return dao.insertAndRoomNoReturn();
	}

	@Override
	public int updateRegdate(int Chatroom_no) {
		return dao.updateRegdate(Chatroom_no);
	}

}
