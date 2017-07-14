package com.yang.ssm.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yang.common.tools.json.GsonUtils;
import com.yang.ssm.dao.MenuMapper;
import com.yang.ssm.dao.RoleMenuMapper;
import com.yang.ssm.domain.Menu;
import com.yang.ssm.service.MenuService;
import com.yang.ssm.util.SysWebUtils;

/**
 * 
 * @author yanglei
 * 2017年6月27日 下午2:20:50
 */
@Service
public class MenuServiceImpl implements MenuService {

	@Autowired
	private MenuMapper menuMapper;
	@Autowired
	private RoleMenuMapper roleMenuMapper;
	@Autowired
	private SysWebUtils sysWebUtils;
	
	@Override
	public List<Menu> selectLeftMenuList(String userId) {
		List<Menu> menuList = menuMapper.selectTreeMenuList(userId);
		List<Menu> leftMenuList = this.getLeftMenuList(menuList);
		return leftMenuList;
	}
	
	/**
	 * 获取左侧菜单树列表
	 * @author yanglei
	 * 2017年6月28日 上午10:24:00
	 */
	private List<Menu> getLeftMenuList(List<Menu> menuList){
		List<Menu> resultList = null;
		if(menuList != null && menuList.size() > 0){
			Map<String, List<Menu>> menuMap = new LinkedHashMap<String, List<Menu>>();
			List<Menu> tmpList = new ArrayList<Menu>();
			String parentId = "";
			for(Menu menu : menuList){
				if(menu != null && menu.getMenuLevel() == 1){
					tmpList = new ArrayList<Menu>();
					tmpList.add(menu);
					menuMap.put(menu.getMenuId(), tmpList);
				}
				if(menu != null && menu.getMenuLevel() == 2){
					if(!parentId.equals(menu.getParentId())){
						parentId = menu.getParentId();
					}
					menuMap.get(parentId).add(menu);
				}
			}
			
			if(MapUtils.isNotEmpty(menuMap)){
				resultList = new ArrayList<Menu>();
				for(Entry<String, List<Menu>> entry : menuMap.entrySet()){
					resultList.addAll(entry.getValue());
				}
			}
		}
		return resultList;
	}

	@Override
	public String getTreeData() {
		String resultTreeData = "";
		List<Menu> menuList = menuMapper.selectMenuList();
		if(null != menuList && menuList.size() >= 0){
			Map<String, String> map = null;
			List<Map<String, String>> treeList = new ArrayList<Map<String,String>>();
			for(Menu menu : menuList){
				map = new HashMap<String, String>();
				if(StringUtils.isNotEmpty(menu.getMenuId()) && StringUtils.isNotEmpty(menu.getParentId())){
					map.put("id", menu.getMenuId());
					map.put("pId", menu.getParentId());
					map.put("name", menu.getMenuName());
					if("0".equals(menu.getParentId())){
						map.put("open", "true");
					}
					treeList.add(map);
				}
			}
			resultTreeData = GsonUtils.toJson(treeList);
		}
		return resultTreeData;
	}

	@Override
	public Menu selectOneMenu(String menuId) {
		return menuMapper.selectOneMenu(menuId);
	}
	
	@Override
	public void batchDeleteMenu(String menuIds) {
		List<String> menuIdList = new ArrayList<String>();
		String[] menuIdArr = menuIds.split(",");
		for(String menuId : menuIdArr){
			menuIdList.add(menuId);
		}
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("menuIdList", menuIdList);
		menuMapper.deleteMenuByMenuIds(paramMap);
		
		roleMenuMapper.deleteRoleMenuByMenuIds(paramMap);
		
		sysWebUtils.refreshAuto();
	}
	
	@Override
	public void insert(Menu menu) {
		menuMapper.insert(menu);
		
		sysWebUtils.refreshAuto();
	}
	
	@Override
	public void update(Menu menu) {
		menuMapper.updateByMenuId(menu);
		
		sysWebUtils.refreshAuto();
	}
	
	@Override
	public List<Menu> selectMenuByUserId(String userId) {
		return menuMapper.selectMenuByUserId(userId);
	}
}
