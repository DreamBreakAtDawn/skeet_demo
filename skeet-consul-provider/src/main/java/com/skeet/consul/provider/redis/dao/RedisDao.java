package com.skeet.consul.provider.redis.dao;

import java.util.Map;

/**
 * Desc:
 *
 * @author chengsj
 * @date 2020/8/31 15:50
 */
public interface RedisDao {

    boolean set(String key, String value);

    boolean hset(String key, String item, Object value);

    boolean hsetIfAbsent(String key, String item, Object value);

    Object hget(String key, String item);

    Map<Object, Object> hmget(String key);
}
