package com.kh.bts.model.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.bts.dto.MemberDto;
import com.kh.bts.model.dao.FriendDao;
@Service
public class FriendBizImpl implements FriendBiz{

	@Autowired
	private FriendDao dao;
	
	@Override
	public List<MemberDto> selectList(int member_no) {
		return dao.selectList(member_no);
	}

	@Override
	public int insert(int member_no, int friend_no) {
		return dao.insert(member_no, friend_no);
	}

	@Override
	public int delete(int member_no, int friend_no) {
		return dao.delete(member_no, friend_no);
	}

}
