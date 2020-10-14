package com.skeet.consul.provider.redis.dao.impl;

import com.skeet.consul.provider.redis.dao.RedisDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * Desc:
 *
 * @author chengsj
 * @date 2020/8/31 15:52
 */
@Repository
public class RedisDaoImpl implements RedisDao {

    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    public RedisDaoImpl(RedisConnectionFactory redisConnectionFactory) {
        stringRedisTemplate = new StringRedisTemplate(redisConnectionFactory);
    }


    @Override
    public boolean set(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
        return true;
    }

    @Override
    public boolean hset(String key, String item, Object value) {
        stringRedisTemplate.opsForHash().put(key, item, value);
        return true;
    }

    @Override
    public boolean hsetIfAbsent(String key, String item, Object value) {
        stringRedisTemplate.opsForHash().putIfAbsent(key, item, value);
        return true;
    }

    @Override
    public Object hget(String key, String item) {
        return stringRedisTemplate.opsForHash().get(key, item);
    }

    @Override
    public Map<Object, Object> hmget(String key) {
        return stringRedisTemplate.opsForHash().entries(key);
    }
}
