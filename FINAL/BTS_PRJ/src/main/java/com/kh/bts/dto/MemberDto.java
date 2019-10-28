package com.kh.bts.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class MemberDto implements UserDetails {
	
	private int member_no;
	private String member_id;
	private String member_pw;
	private boolean member_enable;
	private String member_authority;
	private String member_nickname;
	private String member_email;
	private String member_image;
	private Date member_regdate;

	

	public int getMember_no() {
		return member_no;
	}

	public void setMember_no(int member_no) {
		this.member_no = member_no;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public String getMember_pw() {
		return member_pw;
	}

	public void setMember_pw(String member_pw) {
		this.member_pw = member_pw;
	}

	public boolean isMember_enable() {
		return member_enable;
	}

	public void setMember_enable(boolean member_enable) {
		this.member_enable = member_enable;
	}

	public String getMember_authority() {
		return member_authority;
	}

	public void setMember_authority(String member_authority) {
		this.member_authority = member_authority;
	}

	public String getMember_nickname() {
		return member_nickname;
	}

	public void setMember_nickname(String member_nickname) {
		this.member_nickname = member_nickname;
	}

	public String getMember_email() {
		return member_email;
	}

	public void setMember_email(String member_email) {
		this.member_email = member_email;
	}

	public String getMember_image() {
		return member_image;
	}

	public void setMember_image(String member_image) {
		this.member_image = member_image;
	}

	public Date getMember_regdate() {
		return member_regdate;
	}

	public void setMember_regdate(Date member_regdate) {
		this.member_regdate = member_regdate;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		ArrayList<GrantedAuthority> auth = new ArrayList<GrantedAuthority>();
		auth.add(new SimpleGrantedAuthority(member_authority));
		return auth;
	}

	@Override
	public String getPassword() {
		return member_pw;
	}

	@Override
	public String getUsername() {
		return member_id;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return member_enable;
	}

	@Override
	public String toString() {
		return "MemberDto [member_no=" + member_no + ", member_id=" + member_id + ", member_pw=" + member_pw
				+ ", member_enable=" + member_enable + ", member_authority=" + member_authority + ", member_nickname="
				+ member_nickname + ", member_email=" + member_email + ", member_image=" + member_image
				+ ", member_regdate=" + member_regdate + "]";
	}

	

}
