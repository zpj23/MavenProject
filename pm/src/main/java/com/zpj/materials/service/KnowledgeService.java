package com.zpj.materials.service;

import com.zpj.common.MyPage;

import java.util.Map;

public interface KnowledgeService {

    MyPage findPageData(Map params, Integer page, Integer limit);

}
