package com.yang.ssm.dao;

import java.util.List;
import java.util.Map;

import com.yang.ssm.domain.RoleMenu;

public interface RoleMenuMapper {
    int insert(RoleMenu record);

    List<RoleMenu> selectListByRoleId(Integer roleId);
    
    int deleteRoleMenuByRoleId(Integer roleId);
    
	int deleteRoleMenuByMenuIds(Map<String,Object> paramMap);
    
    int batchInsert(List<RoleMenu> roleMenuList);
}