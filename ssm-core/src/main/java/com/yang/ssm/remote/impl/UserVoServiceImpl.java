package com.yang.ssm.remote.impl;

import org.springframework.stereotype.Service;

import com.yang.ssm.remote.UserVoService;

/**
 * 
 * @author yanglei
 * 2017年7月12日 下午3:21:28
 */
@Service
public class UserVoServiceImpl implements UserVoService {

	@Override
	public String getUser(String userId) {
		System.out.println("------------远程调用------"+ userId +"-------");
		return userId;
	}

}
