package com.zpj.common;

import java.util.List;

public class MyPage {

	
	public List<?> d;
	
	public int total;

	
	
	public List<?> getD() {
		return d;
	}

	public void setD(List<?> d) {
		this.d = d;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
	public MyPage(){
		
	}
	public MyPage(List<?> d, int total) {
		super();
		this.d = d;
		this.total = total;
	}
	
	
	
}
