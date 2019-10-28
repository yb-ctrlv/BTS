package com.kh.bts.dto;

import java.util.Date;

public class ChatRoomDto {
	
	private int chatroom_no;
	private Date chatroom_regdate;
	
	
	
	
	public ChatRoomDto() {
		super();
	}
	public int getChatroom_no() {
		return chatroom_no;
	}
	public void setChatroom_no(int chatroom_no) {
		this.chatroom_no = chatroom_no;
	}
	public Date getChatroom_regdate() {
		return chatroom_regdate;
	}
	public void setChatroom_regdate(Date chatroom_regdate) {
		this.chatroom_regdate = chatroom_regdate;
	}
	@Override
	public String toString() {
		return "ChatRoomDto [chatroom_no=" + chatroom_no + ", chatroom_regdate=" + chatroom_regdate + "]";
	}
	
	

}
