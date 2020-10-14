package com.skeet.consul.provider.mine.aop;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Desc:
 *
 * @author chengsj
 * @date 2020/7/30 15:02
 */
public class MainClass {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(MainConfig.class);
//        InstanceA instance = (InstanceA) ctx.getBean("instanceA");
//        instance.eat();

//        InstanceInterface instanceB = (InstanceInterface) ctx.getBean("instanceB");
//        instanceB.run();
    }
}
