package com.yang.ssm.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yang.ssm.dao.RoleMapper;
import com.yang.ssm.dao.RoleMenuMapper;
import com.yang.ssm.dao.UserRoleMapper;
import com.yang.ssm.domain.Role;
import com.yang.ssm.domain.RoleMenu;
import com.yang.ssm.service.RoleService;

/**
 * 
 * @author yanglei
 * 2017年6月29日 下午4:42:40
 */
@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private RoleMenuMapper roleMenuMapper;
	@Autowired
	private UserRoleMapper userRoleMapper;
	
	@Override
	public Role selectRoleById(Integer roleId) {
		return roleMapper.selectByPrimaryKey(roleId);
	}

	@Transactional
	@Override
	public void insertRole(Role role, String menuIds) {
		roleMapper.insert(role);
		roleMenuMapper.deleteRoleMenuByRoleId(role.getRoleId());
		
		List<RoleMenu> roleMenuList = this.getRoleMenuList(role.getRoleId(), menuIds);
		roleMenuMapper.batchInsert(roleMenuList);
	}

	@Transactional
	@Override
	public void updateRole(Role role, String menuIds) {
		roleMapper.updateByPrimaryKeySelective(role);
		roleMenuMapper.deleteRoleMenuByRoleId(role.getRoleId());
		
		List<RoleMenu> roleMenuList = this.getRoleMenuList(role.getRoleId(), menuIds);
		roleMenuMapper.batchInsert(roleMenuList);
	}
	
	@Transactional
	@Override
	public void deleteRole(Integer roleId) {
		roleMapper.deleteByPrimaryKey(roleId);
		roleMenuMapper.deleteRoleMenuByRoleId(roleId);
		userRoleMapper.deleteUserRoleByRoleId(roleId);
	}

	@Override
	public List<Role> selectRoleList(Map<String, Object> map) {
		return roleMapper.selectRoleList(map);
	}

	@Override
	public Map<String, Object> getRoleMenuRef(Integer roleId) {
		Map<String, Object> refMap = null;
		List<RoleMenu> roleMenuList = roleMenuMapper.selectListByRoleId(roleId);
		if(roleMenuList != null && roleMenuList.size() > 0){
			refMap = new HashMap<String, Object>();
			String menuIds = "";
			for(RoleMenu roleMenu : roleMenuList){
				menuIds += "," + roleMenu.getMenuId();
			}
			refMap.put("menuIds", menuIds.substring(1));
		}
		return refMap;
	}
	
	private List<RoleMenu> getRoleMenuList(Integer roleId, String menuIds){
		List<RoleMenu> roleMenuList = null;
		if(StringUtils.isNotEmpty(menuIds)){
			roleMenuList = new ArrayList<RoleMenu>();
			String[] menuIdArray = menuIds.split(",");
			RoleMenu roleMenu = null;
			for(int i = 0, size = menuIdArray.length; i < size; i++){
				roleMenu = new RoleMenu();
				roleMenu.setRoleId(roleId);
				roleMenu.setMenuId(menuIdArray[i]);
				roleMenuList.add(roleMenu);
			}
		}
		return roleMenuList;
	}
	
}
