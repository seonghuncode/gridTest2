package com.ysh.jqGri.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ysh.jqGri.dto.UserDto;
import com.ysh.jqGri.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public void doJoin(UserDto userDto) {
		String encoderPassword = passwordEncoder.encode(userDto.getPassword()); //비밀번호 암호화
		userDto.setPassword(encoderPassword);
		userRepository.doJoin(userDto);
	}

}
