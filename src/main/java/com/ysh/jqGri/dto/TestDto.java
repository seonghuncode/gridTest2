package com.ysh.jqGri.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestDto {
	
	private int id;
	private String test_name;
	private String test_email;
	private Date test_date;
	
	

}
