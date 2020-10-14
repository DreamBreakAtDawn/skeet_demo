package com.skeet.common.api.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Desc:
 *
 * @author chengsj
 * @date 2020/3/31 11:06
 */
@RequestMapping("consul/provider")
public interface ConsulWsService {

    @GetMapping("sayHello/{name}")
    String sayHello(@PathVariable("name") String name);
}
