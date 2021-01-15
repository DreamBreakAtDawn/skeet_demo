package com.skeet.consul.provider.redis.controller;

import com.skeet.consul.provider.redis.dao.RedisDao;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * Desc:redis相关操作
 *
 * @author chengsj
 * @date 2020/8/31 17:07
 */
@Log4j2
@RestController
@RequestMapping("redis")
public class RedisController {

    private static final String PASSWORD = "1qazXSW@";
    @Resource
    private RedisDao redisDao;

    /**
     * 设置key有效期
     *
     * @param time (单位：秒)
     */
    @RequestMapping("expire/{key}/{time}")
    public boolean expire(@PathVariable("key") String key, @PathVariable("time") long time) {
        return redisDao.expire(key, time);
    }

    /**
     * 获取key有效期
     */
    @RequestMapping("getExpire/{key}")
    public long getExpire(@PathVariable("key") String key) {
        return redisDao.getExpire(key);
    }

    /**
     * 判断可以是否存在
     */
    @RequestMapping("hasKey/{key}")
    public boolean hasKey(@PathVariable("key") String key) {
        return redisDao.hasKey(key);
    }

    /**
     * 批量删除指定key，多个key使用英文“,”分隔
     */
    @RequestMapping("del/{key}/{password}")
    public void del(@PathVariable("key") String key, @PathVariable("password") String password) {
        if (StringUtils.equals(password, PASSWORD)) {
            String[] keys = key.split(",");
            redisDao.del(keys);
        }
    }

    /**
     * 获取字符串类型指定key的值
     */
    @RequestMapping("get/{key}")
    public Object get(@PathVariable("key") String key) {
        return redisDao.get(key);
    }

    /**
     * 设置String值
     */
    @RequestMapping("set/{key}")
    public boolean set(@PathVariable("key") String key, @RequestParam("value") Object value) {
        return redisDao.set(key, value);
    }

    /**
     * 判断是否存在设置String值
     */
    @RequestMapping("setIfAbsent/{key}/{time}")
    public boolean setIfAbsent(@PathVariable("key") String key, @RequestParam("value") Object value, @PathVariable("time") long time) {
        return redisDao.setIfAbsent(key, value, time);
    }

    /**
     * 设置值并设置过期时间
     */
    @RequestMapping("setAndTime/{key}/{time}")
    public boolean set(@PathVariable("key") String key, @RequestParam("value") Object value, @PathVariable("time") long time) {
        return redisDao.set(key, value, time);
    }

    /**
     * 递增
     */
    @RequestMapping("incr/{key}/{delta}")
    public long incr(@PathVariable("key") String key, @PathVariable("delta") long delta) {
        return redisDao.incr(key, delta);
    }

    /**
     * 递减
     */
    @RequestMapping("decr/{key}/{delta}")
    public long decr(@PathVariable("key") String key, @PathVariable("delta") long delta) {
        return redisDao.decr(key, delta);
    }

    /**
     * 获取hash结构指定key，指定域的值
     */
    @RequestMapping("hget/{key}")
    public Object hget(@PathVariable("key") String key, String item) {
        return redisDao.hget(key, item);
    }


    /**
     * 获取指定key包含的hash值
     */
    @RequestMapping("hmget/{key}")
    public Map<Object, Object> hmget(@PathVariable("key") String key) {
        return redisDao.hmget(key);
    }

    /**
     * 批量设置hash值
     */
    @RequestMapping("hmset/{key}")
    public boolean hmset(@PathVariable("key") String key, @RequestBody Map<Object, Object> map) {
        return redisDao.hmset(key, map);
    }

    /**
     * 批量设置值并设置过期时间
     */
    @RequestMapping("hmset/{key}/{time}")
    public boolean hmset(@PathVariable("key") String key, @RequestBody Map<Object, Object> map, @PathVariable("time") long time) {
        return redisDao.hmset(key, map, time);
    }

    /**
     * HashSet 清除缓存项
     *
     * @param key  键
     * @param itemKey 项
     * @return true成功 false失败
     */
    @PostMapping("hmdel/{key}/{itemKey}")
    public boolean hmdel(@PathVariable("key") String key, @PathVariable("itemKey") String itemKey) {
        return redisDao.hmdel(key, itemKey);
    }

    /**
     * 设置单个值
     */
    @RequestMapping("hset/{key}/{item}")
    public boolean hset(@PathVariable("key") String key, @PathVariable("item") String item, @RequestParam("value") Object value) {
        return redisDao.hset(key, item, value);
    }

    /**
     * 设置单个值并设置过期时间
     */
    @RequestMapping("hset/{key}/{item}/{time}")
    public boolean hset(@PathVariable("key") String key, @PathVariable("item") String item, @RequestParam("value") Object value, @PathVariable("time") long time) {
        return redisDao.hset(key, item, value, time);
    }

    /**
     * 删除指定key中的域
     *
     * @param item (多个域使用“，”分隔)
     */
    @RequestMapping("hdel/{key}/{item}")
    public void hdel(@PathVariable("key") String key, @PathVariable("item") String item) {
        String[] items = item.split(",");
        redisDao.hdel(key, items);
    }

    /**
     * 判断域是否存在
     */
    @RequestMapping("hHasKey/{key}/{item}")
    public boolean hHasKey(@PathVariable("key") String key, @PathVariable("item") String item) {
        return redisDao.hHasKey(key, item);
    }

    /**
     * 递增hash值
     *
     * @return 递增之后的值
     */
    @RequestMapping("hincr/{key}/{item}/{by}")
    public double hincr(@PathVariable("key") String key, @PathVariable("item") String item, @PathVariable("by") double by) {
        return redisDao.hincr(key, item, by);
    }

    /**
     * 递减hash值
     */
    @RequestMapping("hdecr/{key}/{item}/{by}")
    public double hdecr(@PathVariable("key") String key, @PathVariable("item") String item, @PathVariable("by") double by) {
        return redisDao.hdecr(key, item, by);
    }


    /**
     * 获取集合值
     */
    @RequestMapping("sGet/{key}")
    public Set<Object> sGet(@PathVariable("key") String key) {
        return redisDao.sGet(key);
    }

    /**
     * 判断集合中是否存在某个值
     */
    @RequestMapping("sHashKey/{key}")
    public boolean sHasKey(@PathVariable("key") String key, @RequestParam("value") Object value) {
        return redisDao.sHasKey(key, value);
    }


    /**
     * 集合中添加值
     */
    @RequestMapping("sSet/{key}")
    public long sSet(@PathVariable("key") String key, @RequestBody Object[] values) {
        return redisDao.sSet(key, values);
    }

    /**
     * 集合中添加值并设置过期时间
     */
    @RequestMapping("sSetAndTime/{key}/{time}")
    public long sSetAndTime(@PathVariable("key") String key, @PathVariable("time") long time, @RequestBody Object[] values) {
        return redisDao.sSetAndTime(key, time, values);
    }

    /**
     * 获取集合长度
     */
    @RequestMapping("sGetSetSize/{key}")
    public long sGetSetSize(@PathVariable("key") String key) {
        return redisDao.sGetSetSize(key);
    }

    /**
     * 删除集合中值
     */
    @RequestMapping("setRemove/{key}")
    public long setRemove(@PathVariable("key") String key, @RequestBody Object[] values) {
        return redisDao.setRemove(key, values);
    }

    /**
     * 获取有序集合值
     */
    @RequestMapping("zGet/{key}")
    public Set<Object> zGet(@PathVariable("key") String key) {
        return redisDao.zGet(key);
    }

    /**
     * 获取有序集合值与score
     */
    @RequestMapping("zGetWithScore/{key}")
    public Map<Object, Double> zGetWithScore(@PathVariable("key") String key) {
        return redisDao.zGetWithScore(key);
    }


    /**
     * 判断有序集合中是否存在某个值
     */
    @RequestMapping("zHasKey/{key}")
    public boolean zHasKey(@PathVariable("key") String key, @RequestParam("value") Object value) {
        return redisDao.zHasKey(key, value);
    }

    /**
     * 设置有序集合值
     */
    @RequestMapping("zSet/{key}/{score}")
    public boolean zSet(@PathVariable("key") String key, @RequestParam("value") Object value, @PathVariable("score") double score) {
        return redisDao.zSet(key, value, score);
    }


    /**
     * 获取有序集合长度
     */
    @RequestMapping("getzSetSize/{key}")
    public long getzSetSize(@PathVariable("key") String key) {
        return redisDao.getzSetSize(key);
    }

    /**
     * 删除有序集合中某些值
     */
    @RequestMapping("zSetRemove/{key}")
    public long zSetRemove(@PathVariable("key") String key, @RequestBody Object[] values) {
        return redisDao.zSetRemove(key, values);
    }

    /**
     * 获取队列值
     */
    @RequestMapping("lGet/{key}/{start}/{end}")
    public List<Object> lGet(@PathVariable("key") String key, @PathVariable("start") long start, @PathVariable("end") long end) {
        return redisDao.lGet(key, start, end);
    }

    /**
     * 获取队列长度
     */
    @RequestMapping("lGetListSize/{key}")
    public long lGetListSize(@PathVariable("key") String key) {
        return redisDao.lGetListSize(key);
    }

    /**
     * 获取队列指定索引值
     */
    @RequestMapping("lGetIndex/{key}/{index}")
    public Object lGetIndex(@PathVariable("key") String key, @PathVariable("index") long index) {
        return redisDao.lGetIndex(key, index);
    }

    /**
     * 队列中添加值
     */
    @RequestMapping("lSet/{key}")
    public boolean lSet(@PathVariable("key") String key, @RequestParam("value") Object value) {
        return redisDao.lSet(key, value);
    }

    /**
     * 队列中添加值并设置队列有效期
     */
    @RequestMapping("lSetAndTime/{key}/{time}")
    public boolean lSetAndTime(@PathVariable("key") String key, @RequestParam("value") Object value, @PathVariable("time") long time) {
        return redisDao.lSet(key, value, time);
    }

    /**
     * 批量添加队列值
     */
    @RequestMapping("lSets/{key}")
    public boolean lSets(@PathVariable("key") String key, @RequestBody List<Object> value) {
        return redisDao.lSet(key, value);
    }

    /**
     * 批量添加队列值并设置队列有效期
     */
    @RequestMapping("lSets/{key}/{time}")
    public boolean lSets(@PathVariable("key") String key, @RequestBody List<Object> value, @PathVariable("time") long time) {
        return redisDao.lSet(key, value, time);
    }

    /**
     * 更新队列指定索引值
     */
    @RequestMapping("lUpdateIndex/{key}/{index}")
    public boolean lUpdateIndex(@PathVariable("key") String key, @PathVariable long index, @RequestParam("value") Object value) {
        return redisDao.lUpdateIndex(key, index, value);
    }

    /**
     * 移除N个值为value
     *
     * @param key   键
     * @param count 移除多少个
     * @param value 值
     * @return 移除的个数
     */
    @RequestMapping("lRemove/{key}/{count}")
    public long lRemove(@PathVariable("key") String key, @PathVariable("count") long count, @RequestParam("value") Object value) {
        return redisDao.lRemove(key, count, value);
    }
}
