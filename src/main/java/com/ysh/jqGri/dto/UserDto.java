package com.ysh.jqGri.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
	
	private long id;
	private String email;
	private String password;
	private boolean enabled;
	private String role;
}
