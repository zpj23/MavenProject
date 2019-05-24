package com.zpj.materials.service.impl;


import com.zpj.common.BaseDao;
import com.zpj.common.MyPage;
import com.zpj.materials.entity.KnowledgeInfo;
import com.zpj.materials.entity.Maintain;
import com.zpj.materials.service.KnowledgeService;
import com.zpj.sys.entity.SysUploadFile;
import com.zpj.sys.service.UploadfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class KnowledgeServiceImpl implements KnowledgeService {

    @Autowired
    private BaseDao<KnowledgeInfo> knowledgeDao;


    private String tablename="jl_material_knowledge_info";

    public MyPage findPageData(Map params, Integer page, Integer limit) {
        Map<String,Object> param=new HashMap<String,Object>();
        param.put("title-like", params.get("title"));
        param.put("content-like", params.get("content"));
        if(null!=params.get("starttime")&&!"".equalsIgnoreCase((String)params.get("starttime"))){
            param.put("starttime-self", " registertime >='"+params.get("starttime")+"' ");
        }
        if(null!=params.get("endtime")&&!"".equalsIgnoreCase((String)params.get("endtime"))){
            param.put("endtime-self", " registertime <='"+params.get("endtime")+"' ");
        }
        Map px=new HashMap();
        px.put("registertime", "desc");
        return knowledgeDao.findPageDateSqlT(tablename, param,px , page, limit, KnowledgeInfo.class);
    }
    public KnowledgeInfo findById(String id){
        KnowledgeInfo knowledgeInfo=knowledgeDao.get(id,KnowledgeInfo.class);
        return knowledgeInfo;
    }
}
