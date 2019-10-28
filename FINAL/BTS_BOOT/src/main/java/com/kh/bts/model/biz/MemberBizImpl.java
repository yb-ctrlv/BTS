package com.kh.bts.model.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.bts.dto.MemberDto;
import com.kh.bts.model.dao.MemberDao;

@Service
public class MemberBizImpl implements MemberBiz,UserDetailsService {

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
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MemberDto dto = dao.login(username);
		if(dto ==null) {
			throw new BadCredentialsException("유저ID가 올바르지 않습니다.");
		}
		return dto;
	}

	@Override
	public List<MemberDto> memberListPaging(int start, int end) {
		return dao.memberListPaging(start, end);
	}

	@Override
	public int findIdCount(String id) {
		return dao.findIdCount(id);
	}

	@Override
	public List<MemberDto> findId(int start, int end, String id) {
		return dao.findId(start, end, id);
	}

	@Override
	public int multiEnable(String[] member_no) {
		return dao.multiEnable(member_no);
	}

	@Override
	public int multiDisable(String[] member_no) {
		return dao.multiDisable(member_no);
	}

}
