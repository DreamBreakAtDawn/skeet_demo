package com.skeet.consul.provider.mine.ioc.entity;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Desc:
 *
 * @author chengsj
 * @date 2020/8/21 14:51
 */
@Component
public class BeanLifecycleInstance implements InitializingBean, DisposableBean, ApplicationContextAware, BeanPostProcessor {

    @Override
    public void destroy() throws Exception {
        System.out.println("beanLifecycleInstance destroy!!!");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("beanLifecycleInstance afterPropertiesSet!!!");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("beanLifecycleInstance setApplicationContext!!!");
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println(beanName + " beanLifecycleInstance postProcessBeforeInitialization!!!");
        return null;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println(beanName + " beanLifecycleInstance postProcessAfterInitialization!!!");
        return null;
    }
}
