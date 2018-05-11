package com.zpj.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zpj.common.BaseDao;
import com.zpj.sys.entity.LogInfo;
import com.zpj.sys.service.LogInfoService;
@Service
public class LogInfoServiceImpl implements LogInfoService {
	@Autowired
	private BaseDao<LogInfo> logDao;
	
	public void saveLog(LogInfo log){
		logDao.add(log);
	}
	
}
