package com.zpj.materials.service;

import com.zpj.common.MyPage;
import com.zpj.materials.entity.Store;

import java.util.Map;

public interface StoreService {

    public Store findById(String id);

    public void saveInfo(Store store);

    public Map updateStoreInfo(Store store, boolean inFlag);

    public MyPage findPageData(Map params, Integer page, Integer limit);
}
