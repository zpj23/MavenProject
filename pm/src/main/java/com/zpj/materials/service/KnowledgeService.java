package com.zpj.materials.service;

import com.zpj.common.MyPage;
import com.zpj.materials.entity.KnowledgeInfo;
import com.zpj.materials.entity.Maintain;

import java.util.Map;

public interface KnowledgeService {

    MyPage findPageData(Map params, Integer page, Integer limit);

    public KnowledgeInfo findById(String id);
}
