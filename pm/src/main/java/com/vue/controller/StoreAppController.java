package com.vue.controller;

import com.zpj.common.BaseController;
import com.zpj.common.MyPage;
import com.zpj.materials.entity.Purchase;
import com.zpj.materials.service.InStoreService;
import com.zpj.materials.service.OutStoreService;
import com.zpj.materials.service.StoreService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zpj
 * @ClassName: StoreAppController
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/7/5
 */
@Controller
@RequestMapping("/vue/store")
@Api(value = "/vue/store",tags="库存接口", description = "库存接口")
public class StoreAppController extends BaseController {
    @Autowired
    private InStoreService inStoreService;
    @Autowired
    private OutStoreService outStoreService;
    @Autowired
    private StoreService storeService;


    @RequestMapping("/findInStoreList")
    @ResponseBody
    public void findInStoreList(String cpage,String pagerow,String name,String loginId,String isAdmin){
        Map param=getRequestMap();
        param.put("name",name);
//        User user=(User)getSession().getAttribute("jluser");
        MyPage pagedata = inStoreService.findPageData(param,Integer.parseInt(cpage),Integer.parseInt(pagerow));
        Map map=new HashMap();
        List temp=pagedata.getData();
        if(null==temp){
            map.put("list", new ArrayList());
        }else{
            map.put("list", temp);
        }
        int tot=(Integer)pagedata.getCount();
        double totalPage=Math.ceil((float)tot/Integer.parseInt(pagerow));
        map.put("totalPage", totalPage);
        map.put("count", tot);
        this.jsonWrite2(map);
    }

    @RequestMapping("/findOutStoreList")
    @ResponseBody
    public void findOutStoreList(String cpage,String pagerow,String name,String loginId,String isAdmin){
        Map param=getRequestMap();
        param.put("name",name);
//        User user=(User)getSession().getAttribute("jluser");
        MyPage pagedata = outStoreService.findPageData(param,Integer.parseInt(cpage),Integer.parseInt(pagerow));
        Map map=new HashMap();
        List temp=pagedata.getData();
        if(null==temp){
            map.put("list", new ArrayList());
        }else{
            map.put("list", temp);
        }
        int tot=(Integer)pagedata.getCount();
        double totalPage=Math.ceil((float)tot/Integer.parseInt(pagerow));
        map.put("totalPage", totalPage);
        map.put("count", tot);
        this.jsonWrite2(map);
    }

    @RequestMapping("/findStoreList")
    @ResponseBody
    public void findStoreList(String cpage,String pagerow,String name,String loginId,String isAdmin){
        Map param=getRequestMap();
        param.put("name",name);
//        User user=(User)getSession().getAttribute("jluser");
        MyPage pagedata = storeService.findPageData(param,Integer.parseInt(cpage),Integer.parseInt(pagerow));
        Map map=new HashMap();
        List temp=pagedata.getData();
        if(null==temp){
            map.put("list", new ArrayList());
        }else{
            map.put("list", temp);
        }
        int tot=(Integer)pagedata.getCount();
        double totalPage=Math.ceil((float)tot/Integer.parseInt(pagerow));
        map.put("totalPage", totalPage);
        map.put("count", tot);
        this.jsonWrite2(map);
    }
}
