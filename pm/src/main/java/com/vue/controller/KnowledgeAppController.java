package com.vue.controller;
/*
 * @ClassName: KnowledgeAppController
 * @Description: TODO(知识app接口)
 * @author zpj
 * @date 2019/4/16 13:59
*/

import com.zpj.common.BaseController;
import com.zpj.common.MyPage;
import com.zpj.materials.entity.KnowledgeInfo;
import com.zpj.materials.service.KnowledgeService;
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
}
