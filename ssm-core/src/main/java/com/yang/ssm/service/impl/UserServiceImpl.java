package com.yang.ssm.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yang.common.datasource.DataSourceType;
import com.yang.ssm.dao.UserMapper;
import com.yang.ssm.dao.UserRoleMapper;
import com.yang.ssm.domain.User;
import com.yang.ssm.domain.UserRole;
import com.yang.ssm.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private UserRoleMapper userRoleMapper;
	
	@Override
	public User selectUser(String userId) {
		return userMapper.selectByPrimaryKey(userId);
	}
	
	@Transactional
	@Override
	public void insertUser(User user, String roleIds) {
		userMapper.insert(user);
		userRoleMapper.deleteUserRoleByUserId(user.getUserId());
		
		userRoleMapper.batchInsert(this.getUserRoleList(user.getUserId(), roleIds));
	}
	
	@Transactional
	@Override
	public void updateUser(User user, String roleIds) {
		userMapper.updateByPrimaryKeySelective(user);
		userRoleMapper.deleteUserRoleByUserId(user.getUserId());
		
		userRoleMapper.batchInsert(this.getUserRoleList(user.getUserId(), roleIds));
	}
	
	@Transactional
	@Override
	public void deleteUser(String userId) {
		userMapper.deleteByPrimaryKey(userId);
		userRoleMapper.deleteUserRoleByUserId(userId);
	}

	@DataSourceType(slave = true)
	@Override
	public List<User> selectUserList(Map<String, Object> map) {
		return userMapper.selectUserList(map);
	}
	
	@Override
	public List<UserRole> selectUserRoleByUserId(String userId) {
		return userRoleMapper.selectUserRoleByUserId(userId);
	}
	
	private List<UserRole> getUserRoleList(String userId, String roleIds){
		List<UserRole> userRoleList = null;
		if(StringUtils.isNotEmpty(roleIds)){
			userRoleList = new ArrayList<UserRole>();
			String[] roleIdArray = roleIds.split(",");
			UserRole userRole = null;
			for(int i = 0, size = roleIdArray.length; i < size; i++){
				userRole = new UserRole();
				userRole.setUserId(userId);
				userRole.setRoleId(Integer.parseInt(roleIdArray[i]));
				userRoleList.add(userRole);
			}
		}
		return userRoleList;
	}
	
	@Override
	public User checkUser(String userId, String password) {
		return userMapper.checkUser(userId, password);
	}
}
