package com.zpj.sys.service;

import java.util.Map;

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
}
