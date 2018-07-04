package com.zpj.sys.service;

import java.util.Map;

import com.zpj.sys.entity.DictionaryType;

public interface DictionaryService {
		
	

	/**
	 * 查询树子节点
	 * @Title findJson
	 * @param param
	 * @return
	 * @author zpj
	 * @time 2018年4月26日 下午3:06:31
	 */
	public String findJson(Map<String, String> param);
	
	/**
	 * 查询树顶层结构
	 * @Title findTopJson
	 * @return
	 * @author zpj
	 * @time 2018年4月26日 下午3:06:20
	 */
	public String findTopJson();
	
	/**
	 * 根据id查询分类类型
	 * @Title findDicTypeId
	 * @param id
	 * @return
	 * @author zpj
	 * @time 2018年7月4日 下午2:28:53
	 */
	public DictionaryType findDicTypeId(String id);
	
	/**
	 * 保存字典类型
	 * @Title saveDictionaryType
	 * @param dt
	 * @author zpj
	 * @time 2018年7月4日 下午3:16:41
	 */
	public void saveDictionaryType(DictionaryType dt);
	
	/**
	 * 删除字典类型
	 * @Title delDictionaryType
	 * @param id
	 * @author zpj
	 * @time 2018年7月4日 下午4:06:13
	 */
	public void delDictionaryType(String id);
}
