package com.skeet.consul.provider.modules.cache.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import javax.annotation.Resource;
import java.time.Duration;

/**
 * @Description
 * @Author chengsj
 * @Date 2021/1/12 19:15
 */
@Configuration
@EnableCaching
public class RedisCachingConfigurerSupport extends CachingConfigurerSupport {

    @Resource
    private RedisConnectionFactory redisConnectionFactory;
    @Resource
    private Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer;

    @Bean
    @Override
    public CacheManager cacheManager() {
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
                .computePrefixWith(cacheName -> cacheName)
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(jackson2JsonRedisSerializer))
                .entryTtl(Duration.ofDays(1));

        return RedisCacheManager.builder(RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory))
                .cacheDefaults(redisCacheConfiguration)
                .build();
    }

    @Bean
    @Override
    public KeyGenerator keyGenerator() {
        return (target, method, params) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(target.getClass().getName());
            sb.append(":");
            sb.append(method.getName());
            for (Object obj : params) {
                sb.append(":").append(obj.toString());
            }
            return sb.toString();
        };
    }
}
