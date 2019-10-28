package com.kh.bts.model.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.bts.dto.MongoDto;
import com.kh.bts.model.dao.MongoDao;

@Service
public class MongoBizImpl implements MongoBiz {
	@Autowired
	private MongoDao dao;
	@Override
	public List<MongoDto> mongolist() {
		return dao.getMongoList();
	}

}
