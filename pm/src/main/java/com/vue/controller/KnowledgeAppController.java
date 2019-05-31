package com.vue.controller;
/*
 * @ClassName: KnowledgeAppController
 * @Description: TODO(知识app接口)
 * @author zpj
 * @date 2019/4/16 13:59
*/

import com.zpj.common.BaseController;
import com.zpj.common.DateHelper;
import com.zpj.common.MyPage;
import com.zpj.materials.entity.KnowledgeInfo;
import com.zpj.materials.service.KnowledgeService;
import com.zpj.sys.service.UploadfileService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("/vue/knowledge")
@Api(value = "/vue/knowledge",tags="知识功能接口", description = "知识接口")
public class KnowledgeAppController extends BaseController {

    @Autowired
    private KnowledgeService knowledgeService;
    @Autowired
    private UploadfileService uploadfileService;

    @RequestMapping("/findList")
    @ResponseBody
    public void findList(String title,String content,String cpage,String pagerow,String loginId,String isAdmin ){

        Map param=new HashMap();
        param.put("title", title);
        param.put("content", content);
        MyPage pagedata =knowledgeService.findPageData(param,Integer.parseInt(cpage),Integer.parseInt(pagerow));
        Map map=new HashMap();
        if(null==pagedata.getData()){
            map.put("list", new ArrayList());
        }else{
            map.put("list", pagedata.getData());
        }
        int tot=(Integer)pagedata.getCount();
        double totalPage=Math.ceil((float)tot/Integer.parseInt(pagerow));
        map.put("totalPage", totalPage);
        map.put("count", tot);
        this.jsonWrite2(map);
    }

    @RequestMapping("/findById")
    @ResponseBody
    public void findById(String id){
        KnowledgeInfo knowledgeInfo=knowledgeService.findById(id);
        //String url=uploadfileService.findFilesList(id,"pic");
//        knowledgeInfo.setUrl(url);
        Map map=new HashMap();
        map.put("msg", true);
        map.put("data", knowledgeInfo);
        jsonWrite2(map);
    }

    @RequestMapping("/saveInfo")
    @ResponseBody
    public void saveInfo(String id,String registertime,String title,String content ,String picurl,String loginId,String isAdmin){
        Map map=new HashMap();
        try{
            KnowledgeInfo mt =new KnowledgeInfo();
            mt.setId(id);
            mt.setRegistertime(DateHelper.getDateFromString(registertime+":00", "yyyy-MM-dd HH:mm:ss"));
            mt.setTitle(title);
            mt.setContent(content);
            mt.setUrl(picurl);
            knowledgeService.saveInfo(mt);
            map.put("msg", true);
//			map.put("data", mt);
        }catch (Exception e) {
            map.put("msg", false);
            e.printStackTrace();
        }

        jsonWrite2(map);
    }


    @RequestMapping("/delInfoById")
    @ResponseBody
    public void delInfoById(String delId,String loginId,String isAdmin){
        if(null!=delId&&!delId.equalsIgnoreCase("")){
            knowledgeService.delete(delId);
            Map map =new HashMap();
            map.put("msg", true);
            this.jsonWrite2(map);
        }
    }
}
