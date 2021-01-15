package com.skeet.consul.provider.modules.cache.service.impl;

import com.google.common.collect.Lists;
import com.skeet.consul.provider.model.entity.Human;
import com.skeet.consul.provider.model.entity.Person;
import com.skeet.consul.provider.modules.cache.RedisKey;
import com.skeet.consul.provider.modules.cache.service.RedisCacheService;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description
 * @Author chengsj
 * @Date 2021/1/12 20:18
 */
@Service
@CacheConfig(cacheNames = RedisKey.MY_PREFIX)
public class RedisCacheServiceImpl implements RedisCacheService {

    @Resource
    private RedisCacheService redisCacheService;

    @Cacheable(key = "T(com.skeet.consul.provider.modules.cache.RedisKey).MY_KEY.concat(':').concat(#key)")
    @Override
    public List<Human> getData(String key) {
        return Lists.newArrayList(Person.builder().age(RandomUtils.nextInt(1, 100)).name(key).build());
    }

    @Override
    public List<Human> getDataNoCache(String key) {
        return Lists.newArrayList(Person.builder().age(20).name(key).build());
    }

    @CachePut(key = "T(com.skeet.consul.provider.modules.cache.RedisKey).MY_KEY.concat(':').concat(#key)")
    @Override
    public List<Human> flush(String key) {
        return Lists.newArrayList(this.getData(key));
    }

    @CacheEvict(key = "T(com.skeet.consul.provider.modules.cache.RedisKey).MY_KEY.concat(':').concat(#key)", beforeInvocation = true)
    @Override
    public void clear(String key) {
    }
}
