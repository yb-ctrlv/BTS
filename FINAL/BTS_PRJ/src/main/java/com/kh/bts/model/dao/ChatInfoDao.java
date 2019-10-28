package com.kh.bts.model.dao;

import java.util.List;

import com.kh.bts.dto.ChatInfoDto;

public interface ChatInfoDao {
	
	String namespace = "chatinfo.";
	
	public List<ChatInfoDto> selectListToMember(int member_no);
	public List<ChatInfoDto> selectListToChatRoom(int chatroom_no);
	public int insert(int chatroom_no, int member_no);
	public int updateCountUp(int chatroom_no,int member_no);
	public int updateCountUp(int chatroom_no);
	public int updateCountZero(int chatroom_no,int member_no);
	public int delete(int chatroom_no, int member_no);

}
