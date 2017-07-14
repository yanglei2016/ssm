package com.yang.ssm.service;

import java.util.List;

import com.yang.ssm.domain.Menu;

/**
 * 
 * @author yanglei
 * 2017年6月27日 下午2:13:22
 */
public interface MenuService {

	public List<Menu> selectLeftMenuList(String userId);
	
	public String getTreeData();
	
	public Menu selectOneMenu(String menuId);
	
	public void batchDeleteMenu(String menuIds);
	
	public void insert(Menu menu);
	
	public void update(Menu menu);
	
	public List<Menu> selectMenuByUserId(String userId);
}
