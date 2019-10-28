package com.kh.bts.dto;

import java.util.Date;

public class MessageDto {
	
	private int message_no;
	private int chatroom_no;
	private int member_no;
	private String message_content;
	private Date message_regdate;
	private String message_sender;
	public MessageDto() {
		super();
	}
	public int getMessage_no() {
		return message_no;
	}
	public void setMessage_no(int message_no) {
		this.message_no = message_no;
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
	public String getMessage_content() {
		return message_content;
	}
	public void setMessage_content(String message_content) {
		this.message_content = message_content;
	}
	public Date getMessage_regdate() {
		return message_regdate;
	}
	public void setMessage_regdate(Date message_regdate) {
		this.message_regdate = message_regdate;
	}
	
	public String getMessage_sender() {
		return message_sender;
	}
	public void setMessage_sender(String message_sender) {
		this.message_sender = message_sender;
	}
	@Override
	public String toString() {
		return "MessageDto [message_no=" + message_no + ", chatroom_no=" + chatroom_no + ", member_no=" + member_no
				+ ", message_content=" + message_content + ", message_regdate=" + message_regdate + ", message_sender="
				+ message_sender + "]";
	}
	
	
	
	

}
