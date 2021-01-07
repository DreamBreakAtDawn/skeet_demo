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
public class InstanceB extends InstanceFather {

    public void run() {
        System.out.println("instanceB run......");
    }

//    public InstanceB() {
//        System.out.println("InstanceB");
//    }

//    public InstanceB(InstanceA instanceA) {
//        System.out.println("InstanceB with argument");
//        this.instanceA = instanceA;
//    }
//
//    public void setInstanceA(InstanceA instanceA) {
//        System.out.println("set InstanceA");
//        this.instanceA = instanceA;
//    }
}
