package com.vue.controller;

import com.zpj.common.BaseController;
import com.zpj.common.MyPage;
import com.zpj.materials.entity.*;
import com.zpj.materials.service.*;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * @author zpj
 * @ClassName: PurchaseAppController
 * @Description: TODO(采购单)
 * @date 2019/6/3
 */
@Controller
@RequestMapping("/vue/cgd")
@Api(value = "/vue/cgd",tags="采购单功能接口", description = "采购单接口")
public class PurchaseAppController extends BaseController {

    @Autowired
    private PurchaseService purchaseService;
    @Autowired
    public GoodsService goodsService;



    @RequestMapping("/findList")
    @ResponseBody
    public void findList(String cpage,String pagerow,String name,String loginId,String isAdmin){
        Map param=getRequestMap();
        param.put("name",name);
//        User user=(User)getSession().getAttribute("jluser");
        MyPage pagedata = purchaseService.findPageData(param,Integer.parseInt(cpage),Integer.parseInt(pagerow));
        Map map=new HashMap();
        List temp=pagedata.getData();
        if(null==temp){
            map.put("list", new ArrayList());
        }else{
            Purchase purchase=null;
            for(int m=0;m<temp.size();m++){
                purchase=(Purchase)temp.get(m);
                ((Purchase) temp.get(m)).setDetails(purchaseService.findDetailListByPurchaseId(purchase.getId()));
            }
            map.put("list", temp);
        }
        int tot=(Integer)pagedata.getCount();
        double totalPage=Math.ceil((float)tot/Integer.parseInt(pagerow));
        map.put("totalPage", totalPage);
        map.put("count", tot);
        this.jsonWrite2(map);
    }
    /*
     * @MethodName: saveInfo
     * @Description: TODO(保存方法)
     * state 0进货，1出货
     * @params [id, num, state, name, loginId, isAdmin]
     * @return void
     * @author zpj
     * @date 2019/7/2 14:30
    */
    @RequestMapping("/saveInfo")
    @ResponseBody
    public void saveInfo(String id,String num,String state,String name,String loginId,String isAdmin){
        Map map=new HashMap();
        try{
            map=purchaseService.savePurchaseMutiInfo(id, num, state, name);
        }catch (Exception e){
            e.printStackTrace();
            map.put("msg", false);
            if(state.equalsIgnoreCase("1")){
                map.put("data", "存在没有库存的商品信息，请检查");
            }else{
                map.put("data", "保存出错");
            }
        }
        jsonWrite2(map);
    }
    @RequestMapping("/delInfoById")
    @ResponseBody
    public void delInfoById(String delId,String loginId,String isAdmin){
        if(null!=delId&&!delId.equalsIgnoreCase("")){
            purchaseService.deletePurchaseMutiInfo(delId);
            Map map =new HashMap();
            map.put("msg", true);
            this.jsonWrite2(map);
        }
    }
}
