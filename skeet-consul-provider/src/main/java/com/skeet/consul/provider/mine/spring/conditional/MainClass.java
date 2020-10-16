package com.skeet.consul.provider.mine.spring.conditional;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Desc:
 *
 * @author chengsj
 * @date 2020/9/7 15:19
 */
public class MainClass {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);
    }
}
