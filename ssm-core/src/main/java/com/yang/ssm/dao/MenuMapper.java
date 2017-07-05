package com.yang.ssm.dao;

import java.util.List;
import java.util.Map;

import com.yang.ssm.domain.Menu;

public interface MenuMapper {

	List<Menu> selectMenuList();
	
	List<Menu> selectTreeMenuList(String userId);
	
	Menu selectOneMenu(String menuId);
	
	int deleteMenuByMenuIds(Map<String,Object> paramMap);
	
	int insert(Menu menu);
	
	int updateByMenuId(Menu menu);
	
	List<Menu> selectMenuByUserId(String userId);
	
}
