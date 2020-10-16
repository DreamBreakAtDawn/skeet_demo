package com.skeet.consul.provider.mine.spring.conditional.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * Desc:
 *
 * @author chengsj
 * @date 2020/9/7 15:21
 */
public class SkeetConditional implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        return context.getBeanFactory().containsBean("skeetAspect");
    }
}
