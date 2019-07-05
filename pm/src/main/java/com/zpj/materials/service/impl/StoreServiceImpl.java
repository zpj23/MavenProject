package com.zpj.materials.service.impl;

import com.zpj.common.BaseDao;
import com.zpj.common.MyPage;
import com.zpj.materials.entity.Store;
import com.zpj.materials.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zpj
 * @ClassName: StoreServiceImpl
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/7/2
 */
@Service
public class StoreServiceImpl implements StoreService {



    @Autowired
    private BaseDao<Store> storeBaseDao;
    private String tablename="jl_material_store_info";


    public MyPage findPageData(Map params, Integer page, Integer limit){
        MyPage mp=new MyPage();
        StringBuilder sql=new StringBuilder(300);
        sql.append("select  a.*,b.name as goodsname,b.unit,b.type,b.supplierName  from jl_material_store_info a left join jl_material_goods_info b on a.goodsid=b.id  where 1=1 ");
        if(null!=params.get("name")&&!"".equalsIgnoreCase((String)params.get("name"))){
            sql.append(" and b.name like '%"+params.get("name")+"%' ");
        }
        sql.append(" order by createtime desc ");

        List list=storeBaseDao.findMapObjBySql(sql.toString(),null,page,limit);
        List count=storeBaseDao.findMapObjBySqlNoPage(sql.toString().replace("a.*,b.name as goodsname,b.unit,b.type,b.supplierName"," count(*) as total "));
        mp.setData(list);
        if(null!=list&&count.size()>0){
            mp.setCount(Integer.valueOf(((Map)count.get(0)).get("total").toString()));
        }
        return mp;
    }


    public Map updateStoreInfo(Store store,boolean inFlag){
        Map map=new HashMap();
        Store s=findByGoodsId(store.getGoodsid());
        if(s==null){
            if(!inFlag){
                map.put("goodsid",store.getGoodsid());
                map.put("flag",false);
                return map;
            }else{
                storeBaseDao.add(store);
            }
        }else{
            double bnum=s.getNum();//之前数量
            if(inFlag){
                bnum+=store.getNum();
            }else{
                bnum-=store.getNum();
            }
            s.setNum(bnum);
            s.setUpdatetime(new Date());
            storeBaseDao.merge(s,String.valueOf(s.getId()));
        }
        map.put("flag",true);
        return map;
    }
    public Store findByGoodsId(String id){
        List<Store> list= storeBaseDao.findBySqlT("select * from "+tablename+" where goodsid='"+id+"'",Store.class);
        if(null!=list&&list.size()>0){
            return list.get(0);
        }
        return null;
    }
    public Store findById(String id){
        return storeBaseDao.get(id,Store.class);
    }

    public void saveInfo(Store store){
        Store temp=storeBaseDao.get(store.getId(),Store.class);
        if(null!=temp){
            storeBaseDao.merge(store,String.valueOf(store.getId()));
        }else{
            storeBaseDao.add(store);
        }
    }
}
