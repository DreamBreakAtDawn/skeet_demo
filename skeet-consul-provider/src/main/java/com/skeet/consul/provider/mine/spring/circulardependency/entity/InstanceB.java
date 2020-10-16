package com.skeet.consul.provider.mine.spring.circulardependency.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Desc:
 *
 * @author chengsj
 * @date 2020/7/30 15:03
 */
@Component
@Data
//@DependsOn("instanceA")
public class InstanceB {

    @Resource
    private InstanceA instanceA;

//    public InstanceB() {
//        System.out.println("InstanceB");
//    }

//    @Autowired
//    public InstanceB(@Lazy InstanceA instanceA) {
//        System.out.println("InstanceB with argument");
//        this.instanceA = instanceA;
//    }

    public void eat() {
        System.out.println("instanceB eat......");
    }
//
//    public void setInstanceA(InstanceA instanceA) {
//        System.out.println("set InstanceA");
//        this.instanceA = instanceA;
//    }
}
