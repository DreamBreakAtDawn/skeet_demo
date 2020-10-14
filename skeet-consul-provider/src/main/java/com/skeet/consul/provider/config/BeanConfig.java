package com.skeet.consul.provider.config;

import com.skeet.consul.provider.model.entity.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * Desc:
 *
 * @author chengsj
 * @date 2020/4/9 18:03
 */
@Configuration
@Conditional(WindowsCondition.class)
public class BeanConfig {

    @Bean("dawn")
    @Conditional(WindowsCondition.class)
    public Person personOne() {
        return Person.builder().name("heaven").age(99).build();
    }

    @Bean("dark")
//    @Conditional(LinuxCondition.class)
    public Person personTwo() {
        return Person.builder().name("hell").age(0).build();
    }
}
