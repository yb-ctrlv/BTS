package com.kh.bts.model.biz;

import java.util.List;

import com.kh.bts.dto.MemberDto;

public interface FriendBiz {
	
	
	public List<MemberDto> selectList(int member_no);
	public int insert(int member_no, int friend_no);
	public int delete(int member_no, int friend_no);
}
