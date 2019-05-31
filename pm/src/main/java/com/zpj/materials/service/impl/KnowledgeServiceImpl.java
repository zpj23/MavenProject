package com.zpj.materials.service.impl;


import com.zpj.common.BaseDao;
import com.zpj.common.MyPage;
import com.zpj.materials.entity.KnowledgeInfo;
import com.zpj.materials.service.KnowledgeService;
import com.zpj.sys.entity.LogInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class KnowledgeServiceImpl implements KnowledgeService {

    @Autowired
    private BaseDao<KnowledgeInfo> knowledgeDao;
    @Autowired
    private BaseDao<LogInfo> logDao;

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

    public void saveInfo(KnowledgeInfo info){
        KnowledgeInfo temp=knowledgeDao.get(info.getId(),KnowledgeInfo.class);
        if(null!=temp){
            knowledgeDao.merge(info,info.getId());
        }else{
            knowledgeDao.add(info);
        }
    }

    public void delete(String deleteID) {
        String[] ids=deleteID.split(",");
        StringBuffer sb=new StringBuffer(500);
        for (int m=0;m<ids.length;m++) {
            if(m>0){
                sb.append(",");
            }
            sb.append("'"+ids[m]+"'");
        }
        List list=knowledgeDao.findBySqlT(" select *  from "+tablename+" where id in ("+sb+")", KnowledgeInfo.class);
        KnowledgeInfo mt=null;
        if(null!=list&&list.size()>0){
            for(int m=0;m<list.size();m++){
                mt=(KnowledgeInfo)list.get(m);
                LogInfo loginfo=new LogInfo();
                loginfo.setId(UUID.randomUUID().toString());
                loginfo.setUsername("朱培军");
                loginfo.setCreatetime(new Date());
                loginfo.setType("删除知识记录");
                loginfo.setDescription(mt.toString());
                logDao.add(loginfo);
            }
            knowledgeDao.executeSql(" delete from "+tablename+" where id in ("+sb+")");
        }
    }

}
