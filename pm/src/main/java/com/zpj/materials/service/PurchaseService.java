package com.zpj.materials.service;

import com.zpj.common.MyPage;
import com.zpj.materials.entity.Purchase;
import com.zpj.materials.entity.PurchaseDetail;

import java.util.List;
import java.util.Map;

public interface PurchaseService {
    MyPage findPageData(Map params, Integer page, Integer limit);
    void saveInfo(Purchase purchase);
    /*
     * @MethodName: saveDetailInfo
     * @Description: TODO(保存采购单详细信息)
     * @params [purchaseDetail]
     * @return void
     * @author zpj
     * @date 2019/7/4 14:27
    */
    public void saveDetailInfo(PurchaseDetail purchaseDetail);
    /*
     * @MethodName: findByPurchaseId
     * @Description: TODO(根据采购单id查询详细信息)
     * @params [id]
     * @return com.zpj.materials.entity.PurchaseDetail
     * @author zpj
     * @date 2019/7/4 9:42
    */
    public List<PurchaseDetail> findDetailListByPurchaseId(String id);
    /*
     * @MethodName: savePurchaseMutiInfo
     * @Description: TODO(保存采购单信息)
     * @params [id, num, state, name]
     * @return void
     * @author zpj
     * @date 2019/7/4 9:10
    */
    public Map savePurchaseMutiInfo(String id,String num,String state,String name) throws RuntimeException;

    public void deletePurchaseMutiInfo(String id);
}
