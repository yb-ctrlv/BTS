package com.kh.bts.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.kh.bts.dto.MemberDto;
import com.kh.bts.dto.RoomDto;
@Repository
public class RoomDaoImpl implements RoomDao {

	private Logger logger = LoggerFactory.getLogger(RoomDaoImpl.class);
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public List<RoomDto> roomList() {
		List<RoomDto> list = null;
		
		try {
			list = sqlSession.selectList(namespace+"roomList");
		} catch (Exception e) {
			logger.info("ROOMDAO ROOMLIST ERROR");
			e.printStackTrace();
		}
				
		return list;
	}
	
	@Override
	@Transactional
	public int makeRoom(RoomDto dto) {
		int res = 0;
		
		try {
			res = sqlSession.insert(namespace+"makeRoom",dto);
		} catch (Exception e) {
			logger.debug("ROOMDAO MAKEROOM ERROR");
			e.printStackTrace();
		}
		return res;
	}
	
	@Override
	@Transactional
	public int updateRoom(int room_no) {
		int res = 0;
		
		try {
			res = sqlSession.update(namespace+"updateRoom",room_no);
		} catch (Exception e) {
			logger.debug("ROOMDAO UPDATEROOM ERROR");
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public RoomDto checkRoom(int room_no) {
		
		RoomDto dto = null;
		
		try {
			dto = sqlSession.selectOne(namespace+"checkRoom",room_no);
		} catch (Exception e) {
			logger.debug("ROOMDAO CHECKROOM ERROR");
			e.printStackTrace();
		}
		
		return dto;
	}

	@Override
	@Transactional
	public int outRoom(String room_addr) {
		
		int res = 0;
		
		try {
			res = sqlSession.update(namespace+"outRoom",room_addr);
		} catch (Exception e) {
			logger.debug("ROOMDAO OUTROOM ERROR");
			e.printStackTrace();
		}
		
		return res;
	}

	@Override
	public int checkRoomVal(String room_addr) {
		
		int res = 0;
		
		try {
			res = sqlSession.selectOne(namespace+"checkRoomVal",room_addr);
		} catch (Exception e) {
			logger.debug("ROOMDAO CHECKROOMVAL ERROR");
			e.printStackTrace();
		}
		
		return res;
	}

	@Override
	public int deleteRoom(String room_addr) {
		
		int res = 0;
		
		try {
			res = sqlSession.delete(namespace+"deleteRoom",room_addr);
		} catch (Exception e) {
			logger.debug("ROOMDAO DELETEROOM ERROR");
			e.printStackTrace();
		}
		
		return res;
	}
	
	
	


}
