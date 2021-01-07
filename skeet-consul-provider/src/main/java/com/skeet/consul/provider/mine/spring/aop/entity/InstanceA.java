package com.skeet.consul.provider.mine.spring.aop.entity;

import com.skeet.consul.provider.mine.spring.aop.InstanceFather;
import org.springframework.stereotype.Component;

/**
 * Desc:
 *
 * @author chengsj
 * @date 2020/7/30 15:03
 */
@Component
public class InstanceA extends InstanceFather {

    public void eat() {
        System.out.println("instanceA eat......");
//        int a = 1 / 0;
    }

    public String said() {
        System.out.println("instanceA said......");

//        int a = 1 / 0;
        return content();
    }

    private String content() {
        System.out.println("happy game");
        return "happy game";
    }
}
