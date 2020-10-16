package com.skeet.consul.provider.mine.spring.conditional;

import com.skeet.consul.provider.mine.spring.conditional.condition.SkeetConditional;
import com.skeet.consul.provider.mine.spring.conditional.entiy.SkeetAspect;
import com.skeet.consul.provider.mine.spring.conditional.entiy.SkeetLog;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * Desc:
 *
 * @author chengsj
 * @date 2020/9/7 15:20
 */
@Configuration
@ComponentScan
public class MainConfig {

    @Bean
    public SkeetAspect skeetAspect() {
        return new SkeetAspect();
    }

    @Bean
    @Conditional(SkeetConditional.class)
    public SkeetLog skeetLog() {
        return new SkeetLog();
    }
}
