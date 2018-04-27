package com.zpj.sys.service;

import com.zpj.common.MyPage;
import com.zpj.sys.entity.Supplier;

public interface SupplierService {
	MyPage findPageData(String username, Integer page, Integer limit);
	
	void saveInfo(Supplier info);

	void delete(String deleteID);
	
	public Supplier findById(int id);
}
