package com.totoro.core.utils;

import java.util.List;

public class MyPage {

	
	public List<?> rows;
	
	public int total;

	
	
	public List<?> getList() {
		return rows;
	}

	public void setList(List<?> rows) {
		this.rows = rows;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public MyPage(List<?> rows, int total) {
		super();
		this.rows = rows;
		this.total = total;
	}
	
	
	
}
