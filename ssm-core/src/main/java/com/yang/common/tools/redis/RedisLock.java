package com.yang.common.tools.redis;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import redis.clients.jedis.ShardedJedis;

/**
 * redis锁
 * 
 * @author yanglei 2017年7月13日 下午3:14:40
 */
@Component
public class RedisLock {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	private static final String LOCAL_FLAG = "true"; // 锁标识

	@Autowired
	private RedisUtils redisUtils;
	
	/**
	 * 获取分布式锁
	 * @param lockName				竞争获取锁key
	 * @param acquireTimeoutInMS	获取锁超时时间(毫秒)
	 * @param lockTimeoutInMS		锁的超时时间(毫秒)
	 * @return 						获取锁标识
	 */
	public boolean tryLock(String lockName, long timeOut, long expireTime) {
		ShardedJedis jedis = null;
		boolean broken = false;
		try {
			jedis = redisUtils.getJ();
			
			String lockKey = "lock:" + lockName;
			long endTime = System.currentTimeMillis() + timeOut;
			int lockExpire = (int) (expireTime / 1000);
			
			while (System.currentTimeMillis() < endTime) {
				if (jedis.setnx(lockKey, LOCAL_FLAG) == 1) {
					jedis.expire(lockKey, lockExpire);
					broken = true;
				}
				if (jedis.ttl(lockKey) < 0) {
					jedis.expire(lockKey, lockExpire);
				}
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			}
		} catch (Exception e) {
			redisUtils.releaseJ(jedis);
			logger.info("获取锁异常：{}", e);
		} finally {
			redisUtils.releaseJ(jedis);
		}
		return broken;
	}
	
	
	/**
     * 释放锁
     * @param lockName 竞争获取锁key
     * @return
     */
    public boolean unLock(String lockName) {
    	ShardedJedis jedis = null;
        String lockKey = "lock:" + lockName;
        boolean rtnFlag = false;
        try {
        	jedis = redisUtils.getJ();
        	String value = jedis.get(lockKey);
        	if(StringUtils.isNotBlank(value) && LOCAL_FLAG.equals(value)){
        		jedis.del(lockKey);
        		rtnFlag = true;
        	}
        } catch (Exception e) {
        	redisUtils.releaseJ(jedis);
			logger.info("释放锁异常：{}", e);
        } finally {
        	redisUtils.releaseJ(jedis);
        }
        return rtnFlag;
    }
	
}
