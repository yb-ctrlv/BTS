package com.kh.bts.model.dao;

public interface MemberCountDao {
	
	String namespace= "memberCount.";

	public int previousReg();
	public int todayReg();
	public int weeklyReg();
	public int monthlyReg();
	public int totalReg();
	

}
