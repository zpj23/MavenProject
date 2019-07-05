package com.zpj.materials.service.impl;

import com.zpj.common.BaseDao;
import com.zpj.common.MyPage;
import com.zpj.materials.entity.InStore;
import com.zpj.materials.service.InStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zpj
 * @ClassName: InStoreServiceImpl
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/7/2
 */
@Service
public class InStoreServiceImpl  implements InStoreService {
    @Autowired
    private BaseDao<InStore> inStoreBaseDao;
    private String tablename="jl_material_instore_info";

    public MyPage findPageData(Map params, Integer page, Integer limit){
        MyPage mp=new MyPage();
        StringBuilder sql=new StringBuilder(300);
        sql.append("select  a.*,b.name as goodsname,b.unit,b.type,b.supplierName  from "+tablename+" a left join jl_material_goods_info b on a.goodsid=b.id  where 1=1 ");
        if(null!=params.get("name")&&!"".equalsIgnoreCase((String)params.get("name"))){
            sql.append(" and b.name like '%"+params.get("name")+"%' ");
        }
        sql.append(" order by a.createtime desc ");

        List list=inStoreBaseDao.findMapObjBySql(sql.toString(),null,page,limit);
        List count=inStoreBaseDao.findMapObjBySqlNoPage(sql.toString().replace("a.*,b.name as goodsname,b.unit,b.type,b.supplierName"," count(*) as total "));
        mp.setData(list);
        if(null!=list&&count.size()>0){
            mp.setCount(Integer.valueOf(((Map)count.get(0)).get("total").toString()));
        }
        return mp;
    }

   public void saveInfo(InStore inStore){
       InStore temp=inStoreBaseDao.get(inStore.getId(),InStore.class);
       if(null!=temp){
           inStoreBaseDao.merge(inStore,String.valueOf(inStore.getId()));
       }else{
           inStoreBaseDao.add(inStore);
       }
   }
    public void delInfoByPurchaseId(String id){
        inStoreBaseDao.executeSql("delete from "+tablename +" where purchaseid='"+id+"'");
    }
}
