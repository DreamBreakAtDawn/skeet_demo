package com.skeet.h2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@MapperScan("com.skeet.h2.mapper")
@SpringBootApplication
public class SkeetH2Application {

    public static void main(String[] args) {
        SpringApplication.run(SkeetH2Application.class, args);
    }

}
