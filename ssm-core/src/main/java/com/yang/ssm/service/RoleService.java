package com.yang.ssm.service;

import java.util.List;
import java.util.Map;

import com.yang.ssm.domain.Role;

/**
 * 
 * @author yanglei
 * 2017年6月29日 下午4:41:12
 */
public interface RoleService {
	
	public Role selectRoleById(Integer roleId);
	
	public void insertRole(Role role, String menuIds);
	
	public void updateRole(Role role, String menuIds);
	
	public void deleteRole(Integer roleId);
	
	public List<Role> selectRoleList(Map<String, Object> map);
	
	public Map<String, Object> getRoleMenuRef(Integer roleId);
	
}
