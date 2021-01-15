package com.skeet.consul.provider.modules.cache.controller;

import com.skeet.consul.provider.model.entity.Human;
import com.skeet.consul.provider.modules.cache.service.RedisCacheService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description
 * @Author chengsj
 * @Date 2021/1/12 20:18
 */
@RestController
@RequestMapping("/redis/cache")
public class RedisCacheController {

    @Resource
    private RedisCacheService redisCacheService;

    @GetMapping("/data/{key}")
    public List<Human> getData(@PathVariable("key") String key) {
        return redisCacheService.getData(key);
    }

    @GetMapping("/dataNoCache/{key}")
    public List<Human> dataNoCache(@PathVariable("key") String key) {
        return redisCacheService.getDataNoCache(key);
    }

    @GetMapping("/flush/{key}")
    public List<Human> flush(@PathVariable("key") String key) {
        return redisCacheService.flush(key);
    }

    @GetMapping("/clear/{key}")
    public String clear(@PathVariable("key") String key) {
        redisCacheService.clear(key);
        return "success";
    }
}
