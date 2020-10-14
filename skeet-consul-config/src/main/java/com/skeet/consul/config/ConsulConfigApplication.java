package com.skeet.consul.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Desc:
 *
 * @author chengsj
 * @date 2020/4/2 11:33
 */
@SpringBootApplication(scanBasePackages = {"com.skeet.consul.config"})
public class ConsulConfigApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(ConsulConfigApplication.class);
        springApplication.run(args);
    }
}
