package com.kh.bts.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.bts.dto.ChatRoomDto;

@Repository
public class ChatRoomDaoImpl implements ChatRoomDao {

	private Logger logger = LoggerFactory.getLogger(ChatRoomDaoImpl.class);
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public int insertAndRoomNoReturn() {
		int res = 0;
		ChatRoomDto dto = new ChatRoomDto();
		try {
			res = sqlSession.insert(namespace+"insert",dto);
			if(res>0) {
				logger.info("INSERT 성공! CHATROOM_NO를 RES값에 담습니다.");
				res = dto.getChatroom_no();
			}
		} catch (Exception e) {
			logger.error("CHATROOMDAO INSERTANDROOMNORETURN ERROR",e);
		}
		
		return res;
	}

	@Override
	public int updateRegdate(int chatroom_no) {
		int res = 0;
		
		try {
			res = sqlSession.update(namespace+"updateRegdate",chatroom_no);
		} catch (Exception e) {
			logger.error("CHATROOMDAO UPDATEREGDATE ERROR",e);
		}
		return res;
	}

}
