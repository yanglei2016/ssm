package com.yang.ssm.dao;

import java.util.List;
import java.util.Map;

import com.yang.ssm.domain.Role;

public interface RoleMapper {
    int deleteByPrimaryKey(Integer roleId);

    int insert(Role record);

    Role selectByPrimaryKey(Integer roleId);

    int updateByPrimaryKeySelective(Role record);

    List<Role> selectRoleList(Map<String, Object> map);
}