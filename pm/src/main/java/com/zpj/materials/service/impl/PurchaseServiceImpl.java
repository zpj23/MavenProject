package com.zpj.materials.service.impl;

import com.zpj.common.BaseDao;
import com.zpj.common.MyPage;
import com.zpj.common.aop.Log;
import com.zpj.materials.entity.*;
import com.zpj.materials.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zpj
 * @ClassName: PurchaseServiceImpl
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/6/3
 */
@Service
public class PurchaseServiceImpl implements PurchaseService {

    @Autowired
    private BaseDao<Purchase> purchaseBaseDao;
    @Autowired
    private BaseDao<PurchaseDetail> purchaseDetailBaseDao;

    @Autowired
    private InStoreService inStoreService;
    @Autowired
    private OutStoreService outStoreService;
    @Autowired
    private StoreService storeService;
    @Autowired
    public GoodsService goodsService;

    private String tablename="jl_material_purchase_info";

    public MyPage findPageData(Map params, Integer page, Integer limit){
        Map<String,Object> param=new HashMap<String,Object>();
        if(null!=params.get("state")&&!"".equalsIgnoreCase((String)params.get("state"))){
            param.put("state-eq", params.get("state"));
        }
        if(null!=params.get("name")&&!"".equalsIgnoreCase((String)params.get("name"))){
            param.put("name-like", params.get("name"));
        }
        Map px=new HashMap();
	    px.put("createtime", "desc");
        return purchaseBaseDao.findPageDateSqlT(tablename, param,px , page, limit, Purchase.class);
    }

    public void delInfo(String id){
        purchaseBaseDao.executeSql("delete from "+tablename+" where id='"+id+"'");
        purchaseBaseDao.executeSql("delete from jl_material_purchase_detail_info where purchaseid='"+id+"'");
    }

    public void saveInfo(Purchase purchase){
        Purchase temp=purchaseBaseDao.get(purchase.getId(),Purchase.class);
        if(null!=temp){
            purchaseBaseDao.merge(purchase, String.valueOf(purchase.getId()));
        }else{
            purchaseBaseDao.add(purchase);
        }
    }

    public void saveDetailInfo(PurchaseDetail purchaseDetail){
        PurchaseDetail temp=purchaseDetailBaseDao.get(purchaseDetail.getId(),PurchaseDetail.class);
        if(null!=temp){
            purchaseDetailBaseDao.merge(purchaseDetail, String.valueOf(purchaseDetail.getId()));
        }else{
            purchaseDetailBaseDao.add(purchaseDetail);
        }
    }

    @Log(type = "保存",remark = "采购单")
    public Map savePurchaseMutiInfo(String id,String num,String state,String name) throws RuntimeException{
        Map map =new HashMap();
        map.put("msg", true);
        map.put("data", "保存成功");
        try{
            String[] ids=id.split(",");
            String[] nums=num.split(",");
            Goods goods=null;
            StringBuilder ifNullName=new StringBuilder(100);

            Purchase purchase=new Purchase();
            double totalprice=0;
            double number=0;

            boolean inFlag=false;
            //是否进货
            if(state.equalsIgnoreCase("0")){
                inFlag=true;
            }
            for(int i=0;i<ids.length;i++){
                number=Double.parseDouble(nums[i]);
                if(number==0){
                    continue;
                }
                //获取商品信息
                goods=new Goods();
                if(null!=ids[i]&&!ids[i].equalsIgnoreCase(""))
                    goods=goodsService.findById(ids[i]);






                if(!ifNullName.toString().equalsIgnoreCase("")){
                    ifNullName.append(",");
                }
                ifNullName.append(goods.getName());



                if(inFlag){
                    //入库总金额，用进价算
                    totalprice=totalprice+goods.getPurchasePrice()*number;

                    InStore inStore=new InStore();
                    inStore.setCreatetime(new Date());
                    inStore.setGoodsid(ids[i]);
                    inStore.setNum(number);
                    inStore.setPrice(goods.getPurchasePrice());
                    inStore.setSupplierid(goods.getSupplierId());
                    inStore.setPurchaseid(purchase.getId());
                    inStoreService.saveInfo(inStore);
                }else{
                    //出库总金额，用卖价算
                    totalprice=totalprice+goods.getSellingPrice()*number;
                    OutStore outStore=new OutStore();
                    outStore.setCreatetime(new Date());
                    outStore.setGoodsid(ids[i]);
                    outStore.setNum(number);
                    outStore.setPrice(goods.getSellingPrice());
                    outStore.setSupplierid(goods.getSupplierId());
                    outStore.setPurchaseid(purchase.getId());
                    outStoreService.saveInfo(outStore);

                }
                //库存
                Store store=new Store();
                store.setGoodsid(ids[i]);
                store.setNum(number);
                if(inFlag){
                    store.setPrice(goods.getPurchasePrice());
                }else {
                    store.setPrice(goods.getSellingPrice());
                }

                store.setSupplierid(goods.getSupplierId());
                store.setUpdatetime(new Date());
                Map retmap=storeService.updateStoreInfo(store,inFlag);
                if(!(boolean)retmap.get("flag")){
                    map.put("msg",false);
                    map.put("data","保存失败，可能存在没有库存的商品信息，请检查");
                    throw new RuntimeException();
                }


                PurchaseDetail pd=new PurchaseDetail();
                pd.setGoodsid(ids[i]);
                if(inFlag){
                    pd.setGoodsprice(goods.getPurchasePrice());
                }else{
                    pd.setGoodsprice(goods.getSellingPrice());
                }
                pd.setNum(number);
                pd.setSupplierid(goods.getSupplierId());
                pd.setPurchaseid(purchase.getId());
                this.saveDetailInfo(pd);
            }
            purchase.setCreatetime(new Date());
            purchase.setState(state);
            DecimalFormat format = new DecimalFormat("#.00");
            purchase.setTotalprice(Double.parseDouble(format.format(totalprice)));
            if(null!=name&&!name.equalsIgnoreCase("")){
                purchase.setName(name);
            }else{
                purchase.setName(ifNullName.toString());
            }
            this.saveInfo(purchase);

        }catch (RuntimeException e1){

            throw new RuntimeException();
        }
        catch (Exception e){
            e.printStackTrace();
            map.put("msg", false);
            map.put("data", "保存失败，所有信息都会回滚");
            throw new RuntimeException();
        }finally {
            return map;
        }


    }

    public List<PurchaseDetail> findDetailListByPurchaseId(String id){
        return purchaseDetailBaseDao.findMapObjBySqlNoPage("select a.*,b.name as goodsName,b.unit from  jl_material_purchase_detail_info a left join jl_material_goods_info b on a.goodsid=b.id where a.purchaseid ='"+id+"' ");
    }

    public void deletePurchaseMutiInfo(String id){
        try{
            //删除采购单表和采购单详细信息表
            Purchase purchase=purchaseBaseDao.get(id,Purchase.class);
            List<PurchaseDetail> list=this.findDetailListByPurchaseId(id);
            Store store=null;
            if(purchase.getState().equalsIgnoreCase("0")){
                //0进货，1出货
                inStoreService.delInfoByPurchaseId(id);
                for (int n=0;n<list.size();n++){
                    store=new Store();
                    store.setGoodsid((String)((Map)list.get(n)).get("goodsid"));
                    store.setNum((double)((Map)list.get(n)).get("num"));
                    storeService.updateStoreInfo(store,false);
                }

            }else{
                outStoreService.delInfoByPurchaseId(id);
                for (int n=0;n<list.size();n++){
                    store=new Store();
                    store.setGoodsid((String)((Map)list.get(n)).get("goodsid"));
                    store.setNum((double)((Map)list.get(n)).get("num"));
                    storeService.updateStoreInfo(store,true);
                }
            }
            this.delInfo(id);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

}
