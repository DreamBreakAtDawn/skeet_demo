package com.skeet.consul.provider.service.impl;

import com.skeet.consul.provider.service.ConsulService;
import org.springframework.stereotype.Service;

/**
 * Desc:
 *
 * @author chengsj
 * @date 2020/3/31 18:17
 */
@Service
public class ConsulServiceImpl implements ConsulService {

    @Override
    public String sayHello(String name) {
        return "hello," + name;
    }
}
