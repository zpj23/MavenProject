package com.zpj.materials.service;

import com.zpj.common.MyPage;
import com.zpj.materials.entity.InStore;

import java.util.Map;

public interface InStoreService {
    void saveInfo(InStore inStore);

    void delInfoByPurchaseId(String id);


    MyPage findPageData(Map params, Integer page, Integer limit);
}
