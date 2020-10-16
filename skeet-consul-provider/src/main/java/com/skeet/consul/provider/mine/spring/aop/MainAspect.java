package com.skeet.consul.provider.mine.spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Desc:
 *
 * @author chengsj
 * @date 2020/8/20 17:55
 */
@Aspect
@Component
@Order(7)
public class MainAspect {

    @Pointcut("execution(public * com.skeet.consul.provider.mine.spring.aop..*.*(..))")
    public void log() {
    }

    @Around("log()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("----------------MainAspect：执行around方法前----------------");
        joinPoint.proceed();
        System.out.println("----------------MainAspect：执行around方法后----------------");
        return null;
    }

    @Before("log()")
    public void before() {
        System.out.println("----------------MainAspect：执行before方法----------------");
    }

    @After("log()")
    public void after() {
        System.out.println("----------------MainAspect：执行after方法----------------");
    }

    @AfterReturning("log()")
    public void afterReturning() {
        System.out.println("----------------MainAspect：执行afterReturning方法----------------");
    }

    @AfterThrowing("log()")
    public void afterThrowing() {
        System.out.println("----------------MainAspect：执行afterThrowing方法----------------");
    }

}
