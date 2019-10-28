package com.kh.bts.model.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.bts.dto.ChatInfoDto;
import com.kh.bts.model.dao.ChatInfoDao;
@Service
public class ChatInfoBizImpl implements ChatInfoBiz {

	@Autowired
	private ChatInfoDao dao;
	
	@Override
	public List<ChatInfoDto> selectListToMember(int member_no) {
		return dao.selectListToMember(member_no);
	}

	@Override
	public List<ChatInfoDto> selectListToChatRoom(int chatroom_no) {
		return dao.selectListToChatRoom(chatroom_no);
	}

	@Override
	public int insert(int chatroom_no, int member_no) {
		return dao.insert(chatroom_no, member_no);
	}

	@Override
	public int updateCountUp(int chatroom_no, int member_no) {
		return dao.updateCountUp(chatroom_no, member_no);
	}

	@Override
	public int updateCountZero(int chatroom_no, int member_no) {
		return dao.updateCountZero(chatroom_no, member_no);
	}

	@Override
	public int delete(int chatroom_no, int member_no) {
		return dao.delete(chatroom_no, member_no);
	}

	@Override
	public int updateCountUp(int chatroom_no) {
		return dao.updateCountUp( chatroom_no);
	}

}
