package com.zpj.sys.service;

import com.zpj.common.MyPage;
import com.zpj.sys.entity.Goods;

public interface GoodsService {
	
	MyPage findPageData(String username, Integer page, Integer limit);
	
	void saveInfo(Goods info);

	void delete(String deleteID);
	
	public Goods findById(int id);
}
