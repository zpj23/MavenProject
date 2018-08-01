package com.zpj.materials.service.impl;

import java.util.HashMap;
import java.util.Map;

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

	private String tablename="jl_material_goods_info";
	
	@Override
	public MyPage findPageData(Map param, Integer page, Integer limit) {
		if(null!=param.get("name")&&!"".equalsIgnoreCase((String)param.get("name"))){
			param.put("name-like", param.get("name"));
		}
		if(null!=param.get("supplierId")&&!"".equalsIgnoreCase((String)param.get("supplierId"))){
			param.put("supplierId-like", param.get("supplierId"));
		}
		if(null!=param.get("goodsType")&&!"".equalsIgnoreCase((String)param.get("goodsType"))){
			param.put("goodsType-like", param.get("goodsType"));
		}
		Map px=new HashMap();
	    px.put("createtime", "desc");
		return goodsDao.findPageDateSqlT(tablename, param,px , page, limit, Goods.class);
	}

	@Log(type="保存",remark="保存商品信息")
	public void saveInfo(Goods info) {
			Goods user=this.findById(info.getId());
			if(null!=user){
				goodsDao.merge(info, String.valueOf(info.getId()));
			}else{
				goodsDao.add(info);
			}
	}

	@Log(type="删除",remark="删除商品信息")
	public void delete(String deleteID) {
		String[] ids=deleteID.split(",");
		StringBuffer sb=new StringBuffer(500);
		for (int m=0;m<ids.length;m++) {
			if(m>0){
				sb.append(",");
			}
			sb.append("'"+ids[m]+"'");
		}
		goodsDao.executeSql(" delete from "+tablename+" where id in ("+sb+")");
	}

	public Goods findById(String id) {
		return goodsDao.get(id,Goods.class);
	}	

}
