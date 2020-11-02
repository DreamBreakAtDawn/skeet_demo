package com.skeet.consul.consumer.controller;

import com.skeet.consul.consumer.api.ConsulServiceRemote;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Desc:
 *
 * @author chengsj
 * @date 2020/3/31 17:11
 */
@RestController
@RequestMapping("/consul/consumer")
public class ConsulConsumerController {

    @Resource
    private ConsulServiceRemote consulServiceRemote;

    @RequestMapping("/sayHello/{name}")
    public String sayHello(@PathVariable("name") String name) {
        return consulServiceRemote.sayHello(name);
    }
}
