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
public class MemberDaoImpl implements MemberDao {

	private Logger logger = LoggerFactory.getLogger(MemberDaoImpl.class);
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public MemberDto login(String id) {
		MemberDto dto = null;
		try {
			dto =sqlSession.selectOne(namespace+"login", id);
		} catch (Exception e) {
			logger.info("MEMBERDAO LOGIN ERROR");
			e.printStackTrace();
		}
		return dto;
	}

	@Override
	public List<MemberDto> selectList() {
		List<MemberDto> list = null;
		
		try {
			list = sqlSession.selectList(namespace+"selectList");
		} catch (Exception e) {
			logger.info("MEMBERDAO SELECTLIST ERROR");
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public MemberDto selectone(int member_no) {
		MemberDto dto = null;
		
		try {
			dto = sqlSession.selectOne(namespace+"selectOne",member_no);
		} catch (Exception e) {
			logger.info("MEMBERDAO SELECTONE ERROR");
			e.printStackTrace();
		}
		return dto;
	}

	@Override
	@Transactional
	public int insert(MemberDto dto) {
		int res = 0;
		
		
		try {
			res = sqlSession.insert(namespace+"insert",dto);
		} catch (Exception e) {
			logger.debug("MEMBERDAO INSERT ERROR");
			e.printStackTrace();
		}
		return res;
	}

	@Override
	@Transactional
	public int update(MemberDto dto) {
		int res = 0;
		
		try {
			res = sqlSession.update(namespace+"update",dto);
		} catch (Exception e) {
			logger.info("MEMBERDAO UPDATE ERROR");
			e.printStackTrace();
		}
		
		return res;
	}

	@Override
	@Transactional
	public int delete(int member_no) {
		int res = 0;
		
		try {
			res = sqlSession.update(namespace+"delete",member_no);
		} catch (Exception e) {
			logger.info("MEMBERDAO DELETE ERROR");
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public List<MemberDto> memberListPaging(int start, int end) {
		List<MemberDto> list = null;
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("start", start);
		map.put("end", end);
		
		try {
			list = sqlSession.selectList(namespace+"memberListPaging", map);
		} catch (Exception e) {
			logger.info("MEMBERDAO MEMBERLISTPAGING ERROR");
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public int findIdCount(String id) {
		int res = 0;
		
		try {
			res = sqlSession.selectOne(namespace+"findIdCount",id);
		} catch (Exception e) {
			logger.info("MEMBERDAO FINDIDCOUNT ERROR");
			e.printStackTrace();
		}
		
		return res;
	}

	@Override
	public List<MemberDto> findId(int start, int end, String id) {
		List<MemberDto> list = null;
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", start);
		map.put("end", end);
		map.put("id", id);
		
		try {
			list = sqlSession.selectList(namespace+"findId", map);
		} catch (Exception e) {
			logger.info("MEMBERDAO MEMBERLISTPAGING ERROR");
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	@Transactional
	public int multiEnable(String[] member_no) {
		int res = 0;
		
		Map<String, String[]> map = new HashMap<String, String[]>();
		map.put("member_no", member_no);
		
		try {
			res = sqlSession.update(namespace+"multiEnable", map);
		} catch (Exception e) {
			logger.info("MEMBERDAO MULTIENABLE ERROR");
			e.printStackTrace();
		}
		return res;
	}

	@Override
	@Transactional
	public int multiDisable(String[] member_no) {
		int res = 0;
		
		Map<String, String[]> map = new HashMap<String, String[]>();
		map.put("member_no", member_no);
		
		try {
			res = sqlSession.update(namespace+"multiDisable", map);
		} catch (Exception e) {
			logger.info("MEMBERDAO MULTIDISABLE ERROR");
			e.printStackTrace();
		}
		
		return res;
	}


}
