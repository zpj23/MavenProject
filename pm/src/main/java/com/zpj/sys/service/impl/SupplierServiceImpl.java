package com.zpj.sys.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zpj.common.BaseDao;
import com.zpj.common.MyPage;
import com.zpj.sys.entity.Supplier;
import com.zpj.sys.service.SupplierService;
@Service
public class SupplierServiceImpl implements SupplierService {

	@Autowired
	private BaseDao<Supplier> supplierDao;

	@Override
	public MyPage findPageData(String username, Integer page, Integer limit) {
		Map<String,Object> param=new HashMap<String,Object>();
		param.put("name-like", username);
		Map px=new HashMap();
	    px.put("createtime", "desc");
		return supplierDao.findPageDateSqlT("sys_supplier_info", param,px , page, limit, Supplier.class);
	}

	@Override
	public void saveInfo(Supplier info) {
		if(info.getId()!=0){
			Supplier user=this.findById(info.getId());
			if(null!=user){
				supplierDao.merge(info, String.valueOf(info.getId()));
			}else{
				supplierDao.add(info);
			}
		}else{
			supplierDao.add(info);
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
		supplierDao.executeSql(" delete from sys_supplier_info where id in ("+sb+")");
	}

	public Supplier findById(int id) {
		return supplierDao.get(id,Supplier.class);
	}	
	
}
