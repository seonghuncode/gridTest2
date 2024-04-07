package com.ysh.jqGri.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import com.ysh.jqGri.dto.TestDto;

@Repository
public class TestRepository {
	
	@Autowired
	private SqlSession sqlSession;
	
	public List<TestDto> getTestData(){
//		return sqlSession.selectList("com.ysh.jqGri.repository.TestRepository.getTestData");
		return sqlSession.selectList("com.ysh.jqGri.repository.TestRepository.getTestData");
	}
	
	public List<TestDto> getAllUser(Map<String, Object> map){
		return sqlSession.selectList("com.ysh.jqGri.repository.TestRepository.getAllUser", map);
	}

	public Map<String, Object> getTotalData(){
		return sqlSession.selectOne("com.ysh.jqGri.repository.TestRepository.getTotalData");
	}
	
	public List<TestDto> getDataByPaging(Map<String, Object> map){
		return sqlSession.selectList("com.ysh.jqGri.repository.TestRepository.getDataByPaging", map);
	}
	
}
