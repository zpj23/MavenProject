package com.zpj.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.mysql.fabric.xmlrpc.base.Array;
import com.zpj.sys.entity.DictionaryItem;
import com.zpj.sys.entity.DictionaryType;
import com.zpj.sys.service.DictionaryService;

/**
    * @ClassName: 字典资源初始化
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author zpj
    * @date 2018年7月6日
    *
    */
    
public class ResourceCodeUtil implements ApplicationListener<ContextRefreshedEvent>{
	
	public static Map<String,List<DictionaryType>> typeMap=new HashMap();
	public static Map<String,List<DictionaryItem>> itemMap=new HashMap();
	@Autowired
	public DictionaryService dictionaryService;
	
	public void findSon(List<DictionaryType> list){
		
	}
	
	public void initDictionary() {
		System.out.println("----初始化字典数据-----");
		itemMap.clear();
		List<DictionaryType> typeList=dictionaryService.findAllDictionaryType();
		
		DictionaryType dt;
		for(int j=0;j<typeList.size();j++){
			dt=typeList.get(j);
			itemMap.put(dt.getTypeCode(), new ArrayList<DictionaryItem>());
		}
		List<DictionaryItem> itemList=dictionaryService.findAllDictionaryItem();
		List<DictionaryItem> temp;
		for(int m=0;m<itemList.size();m++){
			temp=itemMap.get(itemList.get(m).getTypeCode());
			temp.add(itemList.get(m));
			itemMap.put(itemList.get(m).getTypeCode(), temp);
		}
	}
	

	public static Map<String,String> getItemByTypeCode(String typeCode){
		List<DictionaryItem> list=itemMap.get(typeCode);
		Map<String,String> retMap=new HashMap();
		for(int k=0;k<list.size();k++){
			retMap.put(list.get(k).getItemCode(),list.get(k).getItemName());
		}
		return retMap;
	}

@Override
public void onApplicationEvent(ContextRefreshedEvent event) {
	
	if(event.getApplicationContext().getParent() == null){//root application context 没有parent，他就是老大.  
        //需要执行的逻辑代码，当spring容器初始化完成后就会执行该方法。  
		initDictionary();
	}  
}
}
