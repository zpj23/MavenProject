package com.zpj.materials.service;

import com.zpj.common.MyPage;
import com.zpj.materials.entity.Goods;

public interface GoodsService {
	
	MyPage findPageData(String username, Integer page, Integer limit);
	
	void saveInfo(Goods info);

	void delete(String deleteID);
	
	public Goods findById(int id);
}
