package com.zpj.materials.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zpj.common.BaseDao;
import com.zpj.common.MyPage;
import com.zpj.materials.entity.Maintain;
import com.zpj.materials.service.MaintainService;
@Service
public class MaintainServiceImpl implements MaintainService {

	@Autowired
	private BaseDao<Maintain> maintainDao;
	
	private String tablename="jl_material_maintain_info";

	@Override
	public MyPage findPageData(String username,String starttime,String endtime, Integer page, Integer limit) {
		Map<String,Object> param=new HashMap<String,Object>();
		param.put("username-like", username);
		if(null!=starttime&&!"".equalsIgnoreCase(starttime)){
			param.put("registertime-self", " registertime >='"+starttime+"' ");
		}
		if(null!=endtime&&!"".equalsIgnoreCase(endtime)){
			param.put("registertime-self", " registertime <='"+endtime+"' ");
		}
		Map px=new HashMap();
	    px.put("createtime", "desc");
		return maintainDao.findPageDateSqlT(tablename, param,px , page, limit, Maintain.class);
	}

	@Override
	public void saveInfo(Maintain info) {
		if(null!=info.getId()&&!"".equalsIgnoreCase(info.getId())){
			Maintain user=this.findById(info.getId());
			if(null!=user){
				maintainDao.merge(info,info.getId());
			}else{
				maintainDao.add(info);
			}
		}else{
			maintainDao.add(info);
		}
		
	}

	@Override
	public void delete(String deleteID) {
		String[] ids=deleteID.split(",");
		StringBuffer sb=new StringBuffer(500);
		for (int m=0;m<ids.length;m++) {
			if(m>0){
				sb.append(",");
			}
			sb.append("'"+ids[m]+"'");
		}
		maintainDao.executeSql(" delete from "+tablename+" where id in ("+sb+")");
	}

	public Maintain findById(String id) {
		return maintainDao.get(id,Maintain.class);
	}	
}