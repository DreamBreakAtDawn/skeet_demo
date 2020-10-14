package com.skeet.consul.provider.redis;

import com.skeet.consul.provider.ConsulProviderApplication;
import com.skeet.consul.provider.redis.dao.RedisDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Desc:
 *
 * @author chengsj
 * @date 2020/8/31 16:07
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ConsulProviderApplication.class)
public class RedisTest {

    @Resource
    private RedisDao redisDao;

    @Test
    public void testHset() {
        System.out.println(redisDao.hset("people", "name", "skeet"));
        System.out.println(redisDao.hset("people", "age", "24"));
        System.out.println(redisDao.hset("people", "sex", "male"));
        System.out.println(redisDao.hset("people", "height", "170"));
    }

    @Test
    public void testSet() {
        System.out.println(redisDao.set("fyb", "shuaib"));
    }

    @Test
    public void testHget() {
        System.out.println(redisDao.hget("people", "name"));
    }

    @Test
    public void testHmget() {
        System.out.println(redisDao.hmget("people"));
    }
}
