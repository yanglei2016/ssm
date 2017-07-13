package com.yang.ssm.test.redis;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.yang.common.tools.redis.RedisUtils;
import com.yang.ssm.BaseTest;

/**
 * 
 * @author yanglei
 * 2017年7月13日 上午10:23:35
 */
public class RedisTest extends BaseTest {
	
	@Autowired
	private RedisUtils redisUtils;

	@Test
	public void testGetSet(){
		String key = "name";
		redisUtils.set(key, "zhangsan111");
	}
}
