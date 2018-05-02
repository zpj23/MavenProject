package com.zpj.materials.service;


import com.zpj.common.MyPage;
import com.zpj.materials.entity.Maintain;

public interface MaintainService {
	MyPage findPageData(String username,String starttime,String endtime, Integer page, Integer limit);
	
	void saveInfo(Maintain info);

	void delete(String deleteID);
	
	public Maintain findById(String id);
	
}
