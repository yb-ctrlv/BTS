package com.kh.bts.common.aop;

import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogAop {
	public void before(JoinPoint join) {
		Logger logger = LoggerFactory.getLogger(join.getTarget() + "");

		logger.info("===========================Logger Start========================");
		Object args[] = join.getArgs();
		if (args != null) {
			logger.info("Method : " + join.getSignature().getName());
			for (int i = 0; i < args.length; i++) {
				logger.info(i + "번째 :" + args[i]);
			}
		}
	}

	public void after(JoinPoint join) {
		Logger logger = LoggerFactory.getLogger(join.getTarget() + "");
		logger.info("===========================Logger End=========================");
	}

	public void afterThrowing(JoinPoint join) {
		Logger logger = LoggerFactory.getLogger(join.getTarget() + "");
		logger.info("error : " + join.getArgs());
		logger.info("error : " + join.toString());
	}
}
