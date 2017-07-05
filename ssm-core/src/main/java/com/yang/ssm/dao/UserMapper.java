package com.yang.ssm.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.yang.ssm.domain.User;

public interface UserMapper {
    int deleteByPrimaryKey(String userId);

    int insert(User record);

    User selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(User record);

    List<User> selectUserList(Map<String, Object> map);
    
    User checkUser(@Param("userId")String userId, @Param("password")String password);
}