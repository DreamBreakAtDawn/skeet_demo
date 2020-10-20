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
//@DependsOn("instanceB")
public class InstanceA {

    @Resource
    private InstanceB instanceB;

//    @Autowired
//    public InstanceA() {
//        System.out.println("InstanceA");
//    }

//    @Autowired
//    public InstanceA(InstanceB instanceB) {
//        System.out.println("InstanceA with argument");
//        this.instanceB = instanceB;
//    }


    //    @Async
    public void eat() {
        System.out.println("instanceA eat......");
    }

//    public void setInstanceB(InstanceB instanceB) {
//        System.out.println("set InstanceB");
//        this.instanceB = instanceB;
//    }
}
