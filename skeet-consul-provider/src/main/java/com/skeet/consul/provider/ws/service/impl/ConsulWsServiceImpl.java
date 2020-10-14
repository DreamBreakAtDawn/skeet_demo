package com.skeet.consul.provider.ws.service.impl;

import com.skeet.common.api.service.ConsulWsService;
import com.skeet.consul.provider.service.ConsulService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Desc:
 *
 * @author chengsj
 * @date 2020/3/31 18:17
 */
@RestController
public class ConsulWsServiceImpl implements ConsulWsService {

    @Resource
    private ConsulService consulService;


    @Override
    public String sayHello(String name) {
        return consulService.sayHello(name);
    }
}
