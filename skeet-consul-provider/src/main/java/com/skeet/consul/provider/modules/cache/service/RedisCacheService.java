package com.skeet.consul.provider.modules.cache.service;

import com.skeet.consul.provider.model.entity.Human;

import java.util.List;

/**
 * @Description
 * @Author chengsj
 * @Date 2021/1/12 20:17
 */
public interface RedisCacheService {

    List<Human> getData(String key);

    List<Human> getDataNoCache(String key);

    List<Human> flush(String key);

    void clear(String key);
}
