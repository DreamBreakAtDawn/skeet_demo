package com.skeet.consul.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

/**
 * Desc:
 *
 * @author chengsj
 * @date 2020/3/31 10:08
 */
@SpringBootApplication(scanBasePackages = {"com.skeet.consul.provider"})
@ComponentScan(excludeFilters = @ComponentScan.Filter(type = FilterType.REGEX, pattern = "com.skeet.consul.provider.mine.*"))
//@ComponentScan(basePackages = {"com.skeet.consul.provider"})
//@MapperScan(basePackages = {"com.skeet.consul.provider.project.order.dao"})
public class ConsulProviderApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(ConsulProviderApplication.class, args);
    }

//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//        return builder.sources(ConsulProviderApplication.class);
//    }
}
