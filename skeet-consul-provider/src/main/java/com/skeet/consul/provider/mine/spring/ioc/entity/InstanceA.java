package com.skeet.consul.provider.mine.spring.ioc.entity;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Desc:
 *
 * @author chengsj
 * @date 2020/7/30 15:03
 */
@Component
public class InstanceA {

    @Resource
    private InstanceB instanceB;

    public void eat() {
        System.out.println("instanceA eat......");
//        int a = 1 / 0;
    }
}
