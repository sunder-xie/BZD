package com.bizideal.whoami.croe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;
/**
 * 获取redis连接的工具
 * @author pc
 *
 */
@Repository("jedisDataSource")
public class JedisDataSourceImpl implements JedisDataSource {
    private static final Logger LOG = LoggerFactory.getLogger(JedisDataSourceImpl.class);
    
    @Autowired(required=false)
    private ShardedJedisPool shardedJedisPool;
    /**
     * 获取redis连接
     */
    @Override
    public ShardedJedis getRedisClient() {
        ShardedJedis shardJedis = null;
        try {
            shardJedis = shardedJedisPool.getResource();
            return shardJedis;
        } catch (Exception e) {
            LOG.error("[JedisDS] getRedisClent error:" + e.getMessage());
            if (null != shardJedis)
                shardJedis.close();
        }
        return null;
    }
    /**
     * 释放redis连接资源
     */
    @Override
    public void returnResource(ShardedJedis shardedJedis) {
        shardedJedis.close();
    }
    /**
     * 释放redis连接资源
     */
    @Override
    public void returnResource(ShardedJedis shardedJedis, boolean broken) {
        shardedJedis.close();
    }

}
