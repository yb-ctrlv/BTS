package com.kh.bts.model.biz;

public interface MemberCountBiz {
	public int previousReg();
	public int todayReg();
	public int weeklyReg();
	public int monthlyReg();
	public int totalReg();
	
}
