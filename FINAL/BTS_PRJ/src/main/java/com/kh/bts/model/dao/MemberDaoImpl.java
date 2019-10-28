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
@Repository
public class MemberDaoImpl implements MemberDao {

	private Logger logger = LoggerFactory.getLogger(MemberDaoImpl.class);
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Autowired 
	private BCryptPasswordEncoder passwordEncoder;
	
	
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
	public int idchk(String id) {
		int res = 0;
		
		try {
			res = sqlSession.selectOne(namespace+"idchk",id);
		} catch (Exception e) {
			logger.info("MEMBERDAO IDCHK ERROR");
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public boolean pwchk(String username, String pw) {
		MemberDto dto = null;
		
		try {
			dto = sqlSession.selectOne(namespace+"login",username);
		} catch (Exception e) {
			logger.info("MEMBERDAO PWCHK ERROR");
			e.printStackTrace();
		}
		return passwordEncoder.matches(pw, dto.getMember_pw());
	}
	
	@Override
	public int emailck(String email) {
		int res = 0;
		
		try {
			res = sqlSession.selectOne(namespace+"emailck",email);
		} catch (Exception e) {
			logger.info("MEMBERDAO EMAILCK ERROR");
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public String findid(String email) {
		
		String id = "";
		
		try {
			id = sqlSession.selectOne(namespace+"findid", email);
		} catch (Exception e) {
			logger.info("MEMBERDAO FINDID ERROR");
			e.printStackTrace();
		}
				
		return id;
	}

	@Override
	public int pwtemp(String email, String password) {
		
		int res = 0;
		MemberDto dto = new MemberDto();
		dto.setMember_email(email);
		dto.setMember_pw(passwordEncoder.encode(password));
		
		try {
			res = sqlSession.update(namespace+"pwtemp", dto);
		} catch (Exception e) {
			logger.info("MEMBERDAO PWTEMP ERROR");
			e.printStackTrace();
		}
		
		return res;
	}



}
