package com.zpj.materials.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zpj.common.BaseDao;
import com.zpj.common.MyPage;
import com.zpj.materials.entity.Goods;
import com.zpj.materials.service.GoodsService;
@Service
public class GoodsServiceImpl implements GoodsService {

	@Autowired
	private BaseDao<Goods> goodsDao;

	private String tablename="jl_material_goods_info";
	
	@Override
	public MyPage findPageData(String username, Integer page, Integer limit) {
		Map<String,Object> param=new HashMap<String,Object>();
		param.put("name-like", username);
		Map px=new HashMap();
	    px.put("createtime", "desc");
		return goodsDao.findPageDateSqlT(tablename, param,px , page, limit, Goods.class);
	}

	@Override
	public void saveInfo(Goods info) {
		if(info.getId()!=0){
			Goods user=this.findById(info.getId());
			if(null!=user){
				goodsDao.merge(info, String.valueOf(info.getId()));
			}else{
				goodsDao.add(info);
			}
		}else{
			goodsDao.add(info);
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
		goodsDao.executeSql(" delete from "+tablename+" where id in ("+sb+")");
	}

	public Goods findById(int id) {
		return goodsDao.get(id,Goods.class);
	}	

}
