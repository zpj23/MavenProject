package com.zpj.materials.service;


import java.util.Map;

import com.zpj.common.MyPage;
import com.zpj.materials.entity.Maintain;

public interface MaintainService {
	MyPage findPageData(Map params, Integer page, Integer limit);
	
	void saveInfo(Maintain info);

	void delete(String deleteID);
	
	public Maintain findById(String id);
	
}
