package com.skeet.consul.provider.config;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * Desc:
 *
 * @author chengsj
 * @date 2020/4/9 18:54
 */
public class WindowsCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
        ClassLoader classLoader = context.getClassLoader();
        BeanDefinitionRegistry registry = context.getRegistry();
        Environment environment = context.getEnvironment();

        String property = environment.getProperty("os.name");
        if (StringUtils.isNotBlank(property) && property.contains("Windows")) {
            return true;
        }

        return false;
    }
}
