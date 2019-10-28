package com.kh.bts.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class Config {

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
		SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
		
		sqlSessionFactory.setDataSource(dataSource);
		sqlSessionFactory.setTypeAliasesPackage("com.kh.bts.dto");
		sqlSessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:/mappers/*.xml"));
		
		return (SqlSessionFactory)sqlSessionFactory.getObject();
	}
	
	@Bean
	public SqlSessionTemplate sqlSession(SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
	
}
