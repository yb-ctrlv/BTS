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

import com.kh.bts.dto.MemberDto;

@Repository
public class FriendDaoImpl implements FriendDao {
	
	private Logger logger = LoggerFactory.getLogger(FriendDaoImpl.class);
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public List<MemberDto> selectList(int member_no) {
		List<MemberDto> list = null;
		
		try {
			list = sqlSession.selectList(namespace+"selectList",member_no);
		} catch (Exception e) {
			logger.error("FRIENDDAO SELECTLIST ERROR",e);
		}
		
		return list;
	}

	@Override
	@Transactional
	public int insert(int member_no, int friend_no) {
		Map<String, Integer> param = new HashMap<String, Integer>();
		param.put("member_no", member_no);
		param.put("friend_no", friend_no);
		int res = 0 ;
		
		try {
			res = sqlSession.insert(namespace+"insert", param);
		} catch (Exception e) {
			logger.error("FRIENDDAO INSERT ERROR",e);
		}
		
		return res;
	}

	@Override
	@Transactional
	public int delete(int member_no, int friend_no) {
		Map<String, Integer> param = new HashMap<String, Integer>();
		param.put("member_no", member_no);
		param.put("friend_no", friend_no);
		int res = 0;
		
		try {
			res = sqlSession.delete(namespace+"delete",param);
		} catch (Exception e) {
			logger.error("FRIENDDAO DELETE ERROR",e);
		}
		return res;
	}

}
