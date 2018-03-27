package com.totoro.core.service;

import java.util.List;

import com.totoro.core.model.MenuInfo;

public interface MenuService {

	List<MenuInfo> findMenuByUserid(String id);

	List<MenuInfo> findMenuByParentid(String pid);

	String findMenuJsonByParentid(String pid);

}
