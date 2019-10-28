package com.kh.bts.model.biz;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.kh.bts.dto.MemberDto;

public interface MemberBiz extends UserDetailsService {
	public MemberDto login(String member_id);
	public List<MemberDto> selectList();
	public MemberDto selectone(int member_no);
	public int insert(MemberDto dto);
	public int update(MemberDto dto);
	public int delete(int member_no);
	public int idchk(String id);
	public boolean pwchk(String id, String pw);
	public int emailck(String email);
	public String findid(String email);
	public int pwtemp(String email, String pw);
}
