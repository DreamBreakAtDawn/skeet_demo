package com.skeet.tkmybatis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@MapperScan("com.skeet.tkmybatis.mapper")
@SpringBootApplication
public class SkeetTkMybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(SkeetTkMybatisApplication.class, args);
    }

}
