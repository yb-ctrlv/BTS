package com.kh.bts.model.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.bts.dto.MemberDto;
import com.kh.bts.model.dao.MemberDao;

@Service
public class MemberBizImpl implements MemberBiz {

	@Autowired
	private MemberDao dao;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public MemberDto login(String member_id) {
		return dao.login(member_id);
	}

	@Override
	public List<MemberDto> selectList() {
		return dao.selectList();
	}

	@Override
	public MemberDto selectone(int member_no) {
		return dao.selectone(member_no);
	}

	@Override
	@Transactional
	public int insert(MemberDto dto) {
		dto.setMember_pw(passwordEncoder.encode(dto.getPassword()));
		return dao.insert(dto);
	}

	@Override
	@Transactional
	public int update(MemberDto dto) {
		if(dto.getPassword()!=null && dto.getPassword()!="") {
			dto.setMember_pw(passwordEncoder.encode(dto.getPassword()));
		}
		return dao.update(dto);
	}

	@Override
	@Transactional
	public int delete(int member_no) {
		return dao.delete(member_no);
	}

	@Override
	public int idchk(String id) {
		return dao.idchk(id);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MemberDto dto = null;
		
		dto = dao.login(username);
		
		if(dto == null) {
			throw new BadCredentialsException("해당 하는 ID를 찾을 수 없습니다.");
		}
		
		return dto;
	}

	@Override
	public boolean pwchk(String id, String pw) {
		return dao.pwchk(id, pw);
	}
	
	@Override
	public int emailck(String email) {
		return dao.emailck(email);
	}

	@Override
	public String findid(String email) {
		return dao.findid(email);
	}

	@Override
	public int pwtemp(String email, String pw) {
		return dao.pwtemp(email, pw);
	}

}
