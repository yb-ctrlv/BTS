package com.kh.bts.model.dao;

public interface ChatRoomDao {

	String namespace = "chatroom.";
	
	public int insertAndRoomNoReturn();
	public int updateRegdate(int Chatroom_no);
}
