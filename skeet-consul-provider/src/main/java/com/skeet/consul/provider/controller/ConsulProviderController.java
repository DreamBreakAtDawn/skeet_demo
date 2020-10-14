package com.skeet.consul.provider.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Desc:
 *
 * @author chengsj
 * @date 2020/3/31 10:11
 */
@RestController
public class ConsulProviderController {

    @GetMapping("actuator/health")
    public String health() {
        return "success";
    }

    @GetMapping("sayHello/{name}")
    public String sayHello(@PathVariable("name") String name) {
        return "hello," + name;
    }
}
