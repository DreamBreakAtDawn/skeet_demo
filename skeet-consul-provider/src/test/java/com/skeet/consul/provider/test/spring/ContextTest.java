package com.skeet.consul.provider.test.spring;

import com.skeet.consul.provider.config.BeanConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Desc:
 *
 * @author chengsj
 * @date 2020/6/24 18:29
 */
public class ContextTest {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BeanConfig.class);
        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        for (String name : beanDefinitionNames) {
            System.out.println(name);
        }
    }
}
