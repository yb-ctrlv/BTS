package com.kh.bts.dto;

import org.apache.ibatis.type.Alias;

@Alias("MemberCountDto")
public class MemberCountDto {
	
	int previousReg;
	int todayReg;
	int weeklyReg;
	int monthlyReg;
	int totalReg;
	
	public MemberCountDto() {
	}

	public int getPreviousReg() {
		return previousReg;
	}

	public void setPreviousReg(int previousReg) {
		this.previousReg = previousReg;
	}

	public int getTodayReg() {
		return todayReg;
	}

	public void setTodayReg(int todayReg) {
		this.todayReg = todayReg;
	}

	public int getWeeklyReg() {
		return weeklyReg;
	}

	public void setWeeklyReg(int weeklyReg) {
		this.weeklyReg = weeklyReg;
	}

	public int getMonthlyReg() {
		return monthlyReg;
	}

	public void setMonthlyReg(int monthlyReg) {
		this.monthlyReg = monthlyReg;
	}

	public int getTotalReg() {
		return totalReg;
	}

	public void setTotalReg(int totalReg) {
		this.totalReg = totalReg;
	}
	
	
	

}
