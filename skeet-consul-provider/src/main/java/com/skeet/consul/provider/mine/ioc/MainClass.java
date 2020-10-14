package com.skeet.consul.provider.mine.ioc;

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
        ctx.close();
    }
}
