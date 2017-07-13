package com.yang.common.tools.redis;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

/**
 * redis工具类
 * @author yanglei
 * 2017年7月13日 上午8:48:08
 */
public class RedisUtils {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * DEFALT_EXPIRE_TIME:默认过期时间  10days.
	 */
	private static final int DEFALT_EXPIRE_TIME = 24 * 60 * 60 * 10;
	
	private ShardedJedisPool shardedJedisPool;
	
	/**
	 * jedis init
	 * @param shardingNodes
	 * 			"192.168.200.191:6379,192.168.200.191:6380,192.168.200.191:6381,192.168.200.191:63782"
	 * @param jedisPoolConfig
	 * 			jedis pool config
	 * @author yanglei
	 * 2017年7月13日 上午9:15:10
	 */
	public RedisUtils(String shardingNodes, GenericObjectPoolConfig jedisPoolConfig){
		if(StringUtils.isBlank(shardingNodes) || shardingNodes.indexOf(":") == -1){
			throw new RuntimeException("jedis init error, offered shardingNodes str illegal : "+ shardingNodes);
		}
		String[] nodesArr = shardingNodes.split(",");
		List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
		for(String node : nodesArr){
			String[] tmp = node.split(":");
			String hosts = tmp[0];
			int port = Integer.parseInt(tmp[1]);
			JedisShardInfo shard = new JedisShardInfo(hosts,port);
			shards.add(shard);
		}
		shardedJedisPool = new ShardedJedisPool(jedisPoolConfig, shards);
		logger.info("jedis init ok, shardingNodes is : {} ", shardingNodes);
	}
	

    public boolean setStr(String key, String str) {
        ShardedJedis jedis = getJ();
        try {
            jedis.set(key, str);
            return true;
        } catch (Exception e) {
            logger.error("setStr error", e);
        } finally {
            releaseJ(jedis);
        }
        return false;
    }

    public String getStr(String key) {
        ShardedJedis jedis = getJ();
        try {
            return jedis.get(key);
        } catch (Exception e) {
            logger.error("getStr error", e);
        } finally {
            releaseJ(jedis);
        }
        return null;
    }
	
	/**
	 * setInt:(缓存Integer对象). <br/>
	 *
	 * @param key
	 * @param i
	 * @return
	 */
	public boolean setInt(String key, int i) {
		ShardedJedis jedis = getJ();
		try {
			jedis.set(key, String.valueOf(i));
			jedis.expire(key, DEFALT_EXPIRE_TIME);
			return true;
		} catch (Exception e) {
			logger.error("setInt error", e);
		} finally {
			releaseJ(jedis);
		}
		return false;
	}
	
	/**
	 * getInt:(获取Integer从redis). <br/>
	 *
	 * @param key
	 * @return
	 */
	public Integer getInt(String key) {
		ShardedJedis jedis = getJ();
		try {
			String s = jedis.get(key);
			if (s == null)
				return null;
			return Integer.parseInt(s);
		} catch (Exception e) {
			logger.error("getInt error", e);
		} finally {
			releaseJ(jedis);
		}
		return null;
	}
	
	
	
	
	private ShardedJedis getJ(){
		return shardedJedisPool.getResource();
	}
	
	@SuppressWarnings("deprecation")
	private void releaseJ(ShardedJedis jedis){
		if (jedis != null){
			shardedJedisPool.returnResourceObject(jedis);
		}
	}
}
