package com.skeet.consul.provider.mine.spring.circulardependency;

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
//        InstanceB instanceB = ctx.getBean(InstanceB.class);
//        System.out.println(instanceB.getClass());
//        System.out.println(instanceB.getInstanceA().getClass());
//        instanceB.eat();
//        instanceB.getInstanceA().eat();
//        System.out.println(instanceB.getInstanceA().getClass());

//        InstanceA instanceA = ctx.getBean(InstanceA.class);
//        System.out.println(instanceA.getClass());
//        System.out.println(instanceA.getInstanceB().getClass());
    }
}
