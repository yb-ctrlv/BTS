package com.kh.bts.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.kh.bts.dto.ChatInfoDto;

@Repository
public class ChatInfoDaoImpl implements ChatInfoDao {
	
	private Logger logger = LoggerFactory.getLogger(ChatInfoDaoImpl.class);
	@Autowired private SqlSessionTemplate sqlSession;
	

	@Override
	public List<ChatInfoDto> selectListToMember(int member_no) {
		List<ChatInfoDto> list = null;
		
		try {
			list = sqlSession.selectList(namespace+"selectListToMember",member_no);
		} catch (Exception e) {
			logger.error("CHATINFODAO SELECTLISTTOMEMBER ERROR",e);
		}
		return list;
	}

	@Override
	public List<ChatInfoDto> selectListToChatRoom(int chatroom_no) {
		List<ChatInfoDto> list = null;
		
		try {
			list = sqlSession.selectList(namespace+"selectListToChatRoom",chatroom_no);
		} catch (Exception e) {
			logger.error("CHATINFODAO SELECTLISTTOCHATROOM ERROR",e);
		}
		return list;
	}

	@Override
	@Transactional
	public int insert(int chatroom_no, int member_no) {
		Map<String, Integer> param = new HashMap<String, Integer>();
		param.put("chatroom_no", chatroom_no);
		param.put("member_no", member_no);
		
		int res = 0;
		
		try {
			res = sqlSession.insert(namespace+"insert",param);
		} catch (Exception e) {
			logger.error("CHATINFODAO INSERT ERROR",e);
		}
		return res;
	}

	@Override
	@Transactional
	public int updateCountUp(int chatroom_no,int member_no) {
		Map<String, Integer> param = new HashMap<String, Integer>();
		param.put("chatroom_no", chatroom_no);
		param.put("member_no", member_no);
		int res = 0;
		
		try {
			res = sqlSession.update(namespace+"updateCountUp",param);
		} catch (Exception e) {
			logger.error("CHATINFODAO UPDATECOUNTUP ERROR",e);
		}
		return res;
	}

	@Override
	@Transactional
	public int updateCountZero(int chatroom_no,int member_no) {
		Map<String, Integer> param = new HashMap<String, Integer>();
		param.put("chatroom_no", chatroom_no);
		param.put("member_no", member_no);
		int res = 0;
		
		try {
			res = sqlSession.update(namespace+"updateCountZero",param);
		} catch (Exception e) {
			logger.error("CHATINFODAO UPDATECOUNTZERO ERROR",e);
		}
		return res;
	}

	@Override
	@Transactional
	public int delete(int chatroom_no, int member_no) {
		Map<String, Integer> param = new HashMap<String, Integer>();
		param.put("chatroom_no", chatroom_no);
		param.put("member_no", member_no);
		
		int res = 0;
		
		try {
			res = sqlSession.delete(namespace+"delete",param);
		} catch (Exception e) {
			logger.error("CHATINFODAO DELETE ERROR",e);
		}
		return res;
	}

	@Override
	public int updateCountUp(int chatroom_no) {
		int res = 0;
		
		try {
			res = sqlSession.update(namespace+"updateCountChatroom",chatroom_no);
		} catch (Exception e) {
			logger.error("CHATINFODAO UPDATECOUNTUP CHATROOM ERROR",e);
		}
		return res;
	}

}
