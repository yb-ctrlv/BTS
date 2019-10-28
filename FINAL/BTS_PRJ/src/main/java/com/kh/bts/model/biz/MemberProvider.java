package com.kh.bts.model.biz;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.kh.bts.dto.MemberDto;
import com.kh.bts.model.dao.MemberDao;

public class MemberProvider implements AuthenticationProvider {

	@Autowired private UserDetailsService userSer;
	@Autowired private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		String username = (String)authentication.getPrincipal();//입력받은 ID
		String password = (String)authentication.getCredentials();//입력받은 PW
		
		
		MemberDto dto = (MemberDto)userSer.loadUserByUsername(username);
		
		ArrayList<GrantedAuthority> auth = (ArrayList<GrantedAuthority>) dto.getAuthorities();
		
		if(!matchPassword(password, dto.getPassword())) {
			throw new BadCredentialsException("password가 일치하지 않습니다.");
		}
		if(!dto.isEnabled()) {
			throw new BadCredentialsException("비활성화 된 계정입니다.");
		}
		UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(dto, password, auth);
		return result;
	}
	

	@Override
	public boolean supports(Class<?> authentication) {
		return true;
	}
	
	private boolean matchPassword(String password, String db_password) {
		
		return passwordEncoder.matches(password, db_password);
	}

}
