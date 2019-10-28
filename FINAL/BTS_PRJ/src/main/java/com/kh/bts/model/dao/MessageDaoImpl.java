package com.kh.bts.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.bts.dto.MessageDto;

@Repository
public class MessageDaoImpl implements MessageDao {

	private Logger logger = LoggerFactory.getLogger(MessageDaoImpl.class);
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public List<MessageDto> seletList(int chatroom_no) {
		List<MessageDto> list = null;
		
		try {
			list = sqlSession.selectList(namespace+"selectList", chatroom_no);
		} catch (Exception e) {
			logger.error("MESSAGEDAO SELECTLIST ERROR",e);
		}
		return list;
	}

	@Override
	public MessageDto insert(MessageDto dto) {
		int res = 0;
		
		try {
			res = sqlSession.insert(namespace+"insert",dto);
			if(res>0) {
				logger.info("MESSAGEDAO INSERT 성공!");
			}
		} catch (Exception e) {
			logger.error("MESSAGEDAO INSERT ERROR",e);
		}
		return dto;
	}

	@Override
	public int delete(int message_no) {
		int res = 0;
		
		try {
			res = sqlSession.delete(namespace+"delete",message_no);
		} catch (Exception e) {
			logger.error("MESSAGEDAO DELETE ERROR",e);
		}
		return res;
	}


}
