package com.bizideal.whoami.croe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import redis.clients.jedis.ShardedJedis;

@Repository("redisClientTemplate")
public class RedisClientTemplate {
	private static final Logger log = LoggerFactory
			.getLogger(RedisClientTemplate.class);

	@Autowired(required = false)
	private JedisDataSource redisDataSource;

	/**
	 * 关闭redis连接
	 */
	public void disconnect() {
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		shardedJedis.disconnect();
	}

	/**
	 * 设置单个值
	 * 
	 * @param key
	 *            键名
	 * @param value
	 *            对应的值
	 * @return
	 */
	public String set(String key, String value) {
		String result = null;

		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {
			result = shardedJedis.set(key, value);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/**
	 * 获取单个值
	 * 
	 * @param key
	 * @return
	 */
	public String get(String key) {
		String result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}

		boolean broken = false;
		try {
			result = shardedJedis.get(key);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/**
	 * 判断该key是否存在
	 * 
	 * @param key
	 * @return
	 */
	public Boolean exists(String key) {
		Boolean result = false;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {
			result = shardedJedis.exists(key);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/**
	 * 该键对应值的类型
	 * 
	 * @param key
	 * @return
	 */
	public String type(String key) {
		String result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {
			result = shardedJedis.type(key);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/**
	 * 在某段时间后失效
	 * 
	 * @param key
	 * @param seconds
	 * @return
	 */
	public Long expire(String key, int seconds) {
		Long result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {
			result = shardedJedis.expire(key, seconds);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/**
	 * 在某个时间点失效
	 * 
	 * @param key
	 * @param unixTime
	 * @return
	 */
	public Long expireAt(String key, long unixTime) {
		Long result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {
			result = shardedJedis.expireAt(key, unixTime);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/**
	 * 获得一个key的活动时间
	 * 
	 * @param key
	 * @return
	 */
	public Long ttl(String key) {
		Long result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {
			result = shardedJedis.ttl(key);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/**
	 * 设置或者清空key的value(字符串)在offset处的bit值。
	 * 
	 * @param key
	 * @param offset
	 * @param value
	 * @return
	 */
	public boolean setbit(String key, long offset, boolean value) {

		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		boolean result = false;
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {
			result = shardedJedis.setbit(key, offset, value);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}
    /**
     * 获取key是否存在
     * @param key
     * @param offset
     * @return
     */
	public boolean getbit(String key, long offset) {
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		boolean result = false;
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;

		try {
			result = shardedJedis.getbit(key, offset);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}
   /**
    * 在特定位置设置字符穿
    * @param key
    * @param offset
    * @param value
    * @return
    */
	public long setRange(String key, long offset, String value) {
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		long result = 0;
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {
			result = shardedJedis.setrange(key, offset, value);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}
/**
 * 获取特定位置的字符串
 * @param key
 * @param startOffset
 * @param endOffset
 * @return
 */
	public String getRange(String key, long startOffset, long endOffset) {
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		String result = null;
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {
			result = shardedJedis.getrange(key, startOffset, endOffset);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}

	/**
	 * key 键 timeOut 缓存时间 单位是秒 bytes 对象序列化后的byte[]数组
	 * 
	 */
	public String putObject(String key, int timeOut, byte[] bytes) {
		String result = null;

		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {
			if (timeOut == -1) {
				result = shardedJedis.set(key.getBytes(), bytes);
			} else {
				result = shardedJedis.setex(key.getBytes(), timeOut, bytes);
			}

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;

	}

	/**
	 * key 键 永久存储 bytes 对象序列化后的byte[]数组
	 * 
	 */
	public String putObject(String key, byte[] bytes) {
		return this.putObject(key, -1, bytes);

	}

	/**
	 * 根据key获取序列化后的数组
	 */
	public byte[] getObject(String key) {

		byte[] bs = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return bs;
		}
		boolean broken = false;
		try {
			bs = shardedJedis.get(key.getBytes());

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return bs;

	}

	/**
	 * 根据key删除
	 * 
	 * @param key
	 * @return
	 */
	public String deteteByKey(String key) {

		String flag = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return flag;
		}
		boolean broken = false;
		try {

			flag = shardedJedis.del(key) + "";

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return flag;

	}

	/**
	 * 操作Set类型 插入String或String[] 并设置Key过期时间
	 */
	public Long saddSET(String key, int timeOut, String data) {
		Long result = null;

		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {

			return result;

		}
		boolean broken = false;
		try {
			result = shardedJedis.sadd(key, data);
			shardedJedis.expire(key, timeOut);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;
	}
   /**
    * 设置存在时间
    * @param key
    * @param seconds
    * @param value
    * @return
    */
	public String setex(String key, int seconds, String value) {
		String result = null;
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		boolean broken = false;
		try {
			result = shardedJedis.setex(key, seconds, value);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			broken = true;
		} finally {
			redisDataSource.returnResource(shardedJedis, broken);
		}
		return result;

	}

}