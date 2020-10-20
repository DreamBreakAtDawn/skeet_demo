package com.skeet.consul.provider.mine.spring.log;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Desc:
 *
 * @author chengsj
 * @date 2020/10/16 14:56
 */
@Slf4j
@Component
public class LogPrintTest {

    @Value("${skeet.name}")
    private String name;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(MainConfig.class);
        log.error("skeet test");
        log.error("skeet error test");
        LogPrintTest test = (LogPrintTest) ctx.getBean("logPrintTest");
        test.name = null;
        System.out.println(test.name.intern());
    }
}
