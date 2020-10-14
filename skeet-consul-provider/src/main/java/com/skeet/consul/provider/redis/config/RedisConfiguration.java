package com.skeet.consul.provider.redis.config;

import org.springframework.context.annotation.Configuration;

/**
 * Desc:
 *
 * @author chengsj
 * @date 2020/8/31 17:07
 */
@Configuration
public class RedisConfiguration {

//    @Bean
//    @Primary
//    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
//        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
//        redisTemplate.setConnectionFactory(redisConnectionFactory);
//        // 设置value的序列化规则和 key的序列化规则
//        StringRedisSerializer serializer = new StringRedisSerializer();
//        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer());
//        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer());
//        redisTemplate.setHashKeySerializer(jackson2JsonRedisSerializer());
//        redisTemplate.setKeySerializer(serializer);
//        redisTemplate.afterPropertiesSet();
//        return redisTemplate;
//    }
}
