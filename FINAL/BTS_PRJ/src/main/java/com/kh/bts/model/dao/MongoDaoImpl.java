package com.kh.bts.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.kh.bts.dto.MongoDto;

@Repository
public class MongoDaoImpl implements MongoDao{

	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Override
	public List<MongoDto> getMongoList(){
		List<MongoDto> lst = null;
		System.out.println(mongoTemplate);
		try {
			lst = mongoTemplate.findAll(MongoDto.class,"bookstore");
			System.out.println(lst.size());
			return lst;
		} catch (Exception e) {
			e.printStackTrace();
			return lst;
		}
	}
}
