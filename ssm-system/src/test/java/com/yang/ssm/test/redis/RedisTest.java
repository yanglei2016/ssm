package com.yang.ssm.test.redis;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.yang.common.tools.redis.RedisLock;
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
	@Autowired
	private RedisLock redisLock;

	@Test
	public void testGetSet(){
		String key = "name";
		redisUtils.setStr(key, "zhangsan111");
	}
	
	@Test
	public void testLock(){
		String key = "name";
		if(redisLock.tryLock(key, 2 * 1000, 10 * 1000)){
			System.out.println("-----1-----获取锁成功");
			
			if(redisLock.tryLock(key, 5 * 1000, 60 * 1000)){
				System.out.println("-----2-----获取锁成功");
			}else{
				System.out.println("=====2=====获取锁失败");
			}
		}else{
			System.out.println("=====1=====获取锁失败");
		}
		
		
	}
	
	
}
