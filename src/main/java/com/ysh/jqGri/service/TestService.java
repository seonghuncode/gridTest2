package com.ysh.jqGri.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.ysh.jqGri.dto.TestDto;
import com.ysh.jqGri.repository.TestRepository;

@Service
public class TestService {
	
	
	@Autowired
	private TestRepository testRepository;
	
	public List<TestDto> getTestData(){
		return testRepository.getTestData();
	}
	
	public List<TestDto> getAllUser(String page, String rows){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("page", page);
		map.put("rows", rows);
		return testRepository.getAllUser(map);
	}
	
	public Map<String, Object> getTotalData(){
		return testRepository.getTotalData();
	}
	
	public List<TestDto> getDataByPaging(Map<String, Object> map){
		return testRepository.getDataByPaging(map);
	}
	


}
