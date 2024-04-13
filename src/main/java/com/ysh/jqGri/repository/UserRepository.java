package com.ysh.jqGri.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ysh.jqGri.dto.UserDto;

@Repository
public class UserRepository {
	
	@Autowired
	private SqlSession sqlSession;
	
	public void doJoin(UserDto userDto) {
		sqlSession.insert("com.ysh.jqGri.repository.UserRepository.doJoin", userDto);
	}

}
