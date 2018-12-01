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
