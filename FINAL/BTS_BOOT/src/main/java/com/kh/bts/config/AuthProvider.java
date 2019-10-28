package com.kh.bts.config;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.kh.bts.dto.MemberDto;

@Component("authProvider")
public class AuthProvider implements AuthenticationProvider {

	@Autowired
	UserDetailsService userService;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String id = (String)authentication.getPrincipal();
		String password = (String)authentication.getCredentials();
		System.out.println("입력받은 ID : " + id);
		System.out.println("입력받은 PASSWORD " + password);
		System.out.println("PASSWORDENCONDER:"+passwordEncoder);
		MemberDto dto = (MemberDto)userService.loadUserByUsername(id);
		System.out.println(dto);
		if(!passwordEncoder.matches(password, dto.getPassword())) {
			System.out.println("비밀번호가 일치하지않았따!");
			throw new BadCredentialsException("비밀번호가 일치하지 않습니다.");
		}
		if(!dto.isEnabled()) {
			throw new BadCredentialsException("활성화 되지 않은 계정입니다.");
		}
		return new UsernamePasswordAuthenticationToken(dto, password, dto.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return true;
	}

}
