package com.totoro.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.totoro.core.dao.MenuInfoMapper;
import com.totoro.core.model.MenuInfo;
import com.totoro.core.model.MenuInfoExample;
import com.totoro.core.service.MenuService;

@Service("menuService")
public class MenuServiceImpl implements MenuService {

	@Autowired
	private MenuInfoMapper menuInfoMapper;

	
	public List<MenuInfo> findMenuByUserid(String id) {
		MenuInfoExample example = new MenuInfoExample();
		MenuInfoExample.Criteria c = example.createCriteria();
		c.andIsMenuEqualTo(1);
		example.setOrderByClause("ordernum");
		return menuInfoMapper.selectByExample(example);
	}

	
	public List<MenuInfo> findMenuByParentid(String pid) {
		MenuInfoExample example = new MenuInfoExample();
		MenuInfoExample.Criteria c = example.createCriteria();
		c.andParentidEqualTo(pid);
		c.andIsMenuEqualTo(1);
		example.setOrderByClause("ordernum");
		return menuInfoMapper.selectByExample(example);
	}

	
	public String findMenuJsonByParentid(String pid) {
		StringBuffer str = new StringBuffer();
		
		List<MenuInfo> list = findMenuByParentid(pid);
		
		str.append("[");
		for(int i=0;i<list.size();i++){  
			if(pid.equals(list.get(i).getParentid().toString())){	
				
				String state = "open";
				List<MenuInfo> list1 = this.findMenuByParentid(list.get(i).getId()+"");
				if(list1!=null)
					state = list1.size() >0 ? "closed":"open";
				str.append("{\"id\":").append(list.get(i).getId()).append(",\"text\":\"").append(list.get(i).getName())				
				.append("\",\"iconCls\":\"").append("icon-menudefault").append("\" ")
				.append(",\"url\":\"").append(list.get(i).getUrl()).append("\",\"state\":\""+state+"\"},");					
			}
		}		
		//str.delete(str.toString().length()-1, str.toString().length()).append("]");
		str.deleteCharAt(str.toString().length()-1).append("]");
		return str.toString();
	}
	
}
