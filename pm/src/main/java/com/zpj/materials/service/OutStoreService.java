package com.zpj.materials.service;

import com.zpj.common.MyPage;
import com.zpj.materials.entity.OutStore;

import java.util.Map;

public interface OutStoreService {
    void saveInfo(OutStore outStore);

    public void delInfoByPurchaseId(String id);
    public MyPage findPageData(Map params, Integer page, Integer limit);
}
