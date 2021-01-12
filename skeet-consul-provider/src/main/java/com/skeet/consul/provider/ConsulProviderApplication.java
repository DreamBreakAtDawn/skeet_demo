package com.skeet.consul.provider;

import lombok.extern.slf4j.Slf4j;
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
//@MapperScan(basePackages = {"com.skeet.consul.provider.modules.project.order.dao"})
@Slf4j
public class ConsulProviderApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(ConsulProviderApplication.class, args);
        log.info("-------------------Application started-------------------");
        System.out.println();
    }

//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//        return builder.sources(ConsulProviderApplication.class);
//    }
}
