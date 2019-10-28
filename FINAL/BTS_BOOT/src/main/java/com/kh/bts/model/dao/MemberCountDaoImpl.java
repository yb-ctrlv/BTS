package com.kh.bts.model.dao;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberCountDaoImpl implements MemberCountDao {

	private Logger logger = LoggerFactory.getLogger(MemberCountDaoImpl.class);
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public int previousReg() {
		int res = 0;
		try {
			res = sqlSession.selectOne(namespace+"previousReg");
		} catch (Exception e) {
			logger.info("MEMBERCOUNTDAO PREVIOUSREG ERROR");
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int todayReg() {
		int res = 0;
		try {
			res = sqlSession.selectOne(namespace+"todayReg");
		} catch (Exception e) {
			logger.info("MEMBERCOUNTDAO TODAYREG ERROR");
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int weeklyReg() {
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Calendar sunday = Calendar.getInstance();
		Calendar saturday = Calendar.getInstance();
		sunday.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		saturday.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("sunday", formatter.format(sunday.getTime()));
		map.put("saturday", formatter.format(saturday.getTime()));
		
		int res = 0;
		try {
			res = sqlSession.selectOne(namespace+"weeklyReg", map);
		} catch (Exception e) {
			logger.info("MEMBERCOUNTDAO WEEKLYREG ERROR");
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int monthlyReg() {
		
		DecimalFormat df = new DecimalFormat("00");
	    Calendar cal = Calendar.getInstance();
	    String stratYear = Integer.toString(cal.get(Calendar.YEAR));
	    String startMonth = df.format(cal.get(Calendar.MONTH) + 1);
	    String minDD = df.format(cal.getMinimum(Calendar.DATE));
	    String maxDD = df.format(cal.getActualMaximum(Calendar.DAY_OF_MONTH));
	    String startDate = stratYear + "-" + startMonth + "-" + minDD;
	    String endDate = stratYear + "-" + startMonth + "-" + maxDD;
	    
	    Map<String, String> map = new HashMap<String, String>();
		map.put("startDate", startDate);
		map.put("endDate", endDate);
	    
		int res = 0;
		try {
			res = sqlSession.selectOne(namespace+"monthlyReg", map);
		} catch (Exception e) {
			logger.info("MEMBERCOUNTDAO MONTHLYREG ERROR");
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int totalReg() {
		int res = 0;
		try {
			res = sqlSession.selectOne(namespace+"totalReg");
		} catch (Exception e) {
			logger.info("MEMBERCOUNTDAO TOTALREG ERROR");
			e.printStackTrace();
		}
		return res;
	}

}
