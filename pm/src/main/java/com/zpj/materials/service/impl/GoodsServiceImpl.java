package com.zpj.materials.service.impl;

import java.util.*;

import com.zpj.materials.entity.Maintain;
import com.zpj.sys.entity.LogInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zpj.common.BaseDao;
import com.zpj.common.MyPage;
import com.zpj.common.aop.Log;
import com.zpj.materials.entity.Goods;
import com.zpj.materials.service.GoodsService;
@Service
public class GoodsServiceImpl implements GoodsService {

	@Autowired
	private BaseDao<Goods> goodsDao;
	@Autowired
	private BaseDao<LogInfo> logDao;

	private String tablename="jl_material_goods_info";
	
	@Override
	public MyPage findPageData(Map canshu, Integer page, Integer limit) {
		Map param=new HashMap();
		if(null!=canshu.get("name")&&!"".equalsIgnoreCase((String)canshu.get("name"))){
			param.put("name-like", canshu.get("name"));
		}
		if(null!=canshu.get("supplierId")&&!"".equalsIgnoreCase((String)canshu.get("supplierId"))){
			param.put("supplierId-like", canshu.get("supplierId"));
		}
		if(null!=canshu.get("goodsType")&&!"".equalsIgnoreCase((String)canshu.get("goodsType"))){
			param.put("goodsType-like", canshu.get("goodsType"));
		}
		Map px=new HashMap();
	    px.put("supplierId", "desc");
		px.put(" RIGHT(name,8) ", "desc");
		return goodsDao.findPageDateSqlT(tablename, param,px , page, limit, Goods.class);
	}

//	@Log(type="保存",remark="保存商品信息")
	public void saveInfo(Goods info) {
			Goods user=this.findById(info.getId());
			if(null!=user){
				goodsDao.merge(info, String.valueOf(info.getId()));
			}else{
				goodsDao.add(info);
			}
			LogInfo loginfo=new LogInfo();
			loginfo.setId(UUID.randomUUID().toString());
			loginfo.setUsername("朱培军");
			loginfo.setCreatetime(new Date());
			loginfo.setType("保存商品记录");
			loginfo.setDescription(info.toString());
			logDao.add(loginfo);
	}

//	@Log(type="删除",remark="删除商品信息")
	public void delete(String deleteID) {
		String[] ids=deleteID.split(",");
		StringBuffer sb=new StringBuffer(500);
		for (int m=0;m<ids.length;m++) {
			if(m>0){
				sb.append(",");
			}
			sb.append("'"+ids[m]+"'");
		}

		List list=goodsDao.findBySqlT(" select *  from "+tablename+" where id in ("+sb+")", Goods.class);
		Goods goods=null;
		if(null!=list&&list.size()>0) {
			for (int m = 0; m < list.size(); m++) {
				goods = (Goods) list.get(m);
				LogInfo loginfo = new LogInfo();
				loginfo.setId(UUID.randomUUID().toString());
				loginfo.setUsername("朱培军");
				loginfo.setCreatetime(new Date());
				loginfo.setType("删除商品记录");
				loginfo.setDescription(goods.toString());
				logDao.add(loginfo);
			}
		}
		goodsDao.executeSql(" delete from "+tablename+" where id in ("+sb+")");
	}

	public Goods findById(String id) {
		return goodsDao.get(id,Goods.class);
	}	

}
