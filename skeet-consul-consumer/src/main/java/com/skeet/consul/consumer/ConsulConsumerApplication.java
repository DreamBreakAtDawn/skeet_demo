package com.skeet.consul.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RestController;

/**
 * Desc:
 *
 * @author chengsj
 * @date 2020/3/31 10:26
 */
@SpringBootApplication(scanBasePackages = {"com.skeet.consul.consumer"})
@EnableFeignClients(basePackages = "com.skeet.consul.consumer")
@RestController
//@EnableDiscoveryClient
public class ConsulConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsulConsumerApplication.class, args);
    }
}

