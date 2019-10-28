package com.kh.bts.model.biz;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kh.bts.model.dao.MemberCountDao;

@Service
public class MemberCountBizImpl implements MemberCountBiz {

	@Autowired
	private MemberCountDao dao;
	
	@Override
	public int previousReg() {
		return dao.previousReg();
	}

	@Override
	public int todayReg() {
		return dao.todayReg();
	}

	@Override
	public int weeklyReg() {
		return dao.weeklyReg();
	}

	@Override
	public int monthlyReg() {
		return dao.monthlyReg();
	}

	@Override
	public int totalReg() {
		return dao.totalReg();
	}

}
