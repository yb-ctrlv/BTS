package com.kh.bts.dto;

public class ChatInfoDto {

	private int chatroom_no;
	private int member_no;
	private int count;
	private String chat_title;
	public ChatInfoDto() {
		super();
	}
	public int getChatroom_no() {
		return chatroom_no;
	}
	public void setChatroom_no(int chatroom_no) {
		this.chatroom_no = chatroom_no;
	}
	public int getMember_no() {
		return member_no;
	}
	public void setMember_no(int member_no) {
		this.member_no = member_no;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	public String getChat_title() {
		return chat_title;
	}
	public void setChat_title(String chat_title) {
		this.chat_title = chat_title;
	}
	@Override
	public String toString() {
		return "ChatInfoDto [chatroom_no=" + chatroom_no + ", member_no=" + member_no + ", count=" + count
				+ ", chat_title=" + chat_title + "]";
	}
	
	
}
