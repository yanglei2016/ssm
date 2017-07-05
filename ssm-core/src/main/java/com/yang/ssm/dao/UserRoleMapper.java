package com.yang.ssm.dao;

import java.util.List;

import com.yang.ssm.domain.UserRole;

public interface UserRoleMapper {
	
    int insert(UserRole userRole);

    List<UserRole> selectUserRoleByUserId(String userId);
    
    int deleteUserRoleByUserId(String userId);
    
    int deleteUserRoleByRoleId(Integer roleId);
    
    int batchInsert(List<UserRole> userRoleList);
}