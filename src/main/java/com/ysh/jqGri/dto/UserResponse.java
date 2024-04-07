package com.ysh.jqGri.dto;

import java.util.List;

public class UserResponse {
	private List<TestDto> rows;
	private Integer total;
	private Integer records;
	private Integer page;

	public List<TestDto> getRows() {
		return rows;
	}

	public void setRows(List<TestDto> rows) {
		this.rows = rows;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getRecords() {
		return records;
	}

	public void setRecords(Integer records) {
		this.records = records;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}
}
