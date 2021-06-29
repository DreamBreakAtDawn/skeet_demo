package com.skeet.consul.provider.mine.spring.aop;

import com.skeet.consul.provider.mine.spring.aop.entity.InstanceA;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Desc:
 *
 * @author chengsj
 * @date 2020/7/30 15:02
 */
public class MainClass {

    public static void main(String[] args) {
        doLogic();
    }

    private static String doLogic() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(MainConfig.class);
        InstanceA instance = (InstanceA) ctx.getBean("instanceA");
        return instance.said();
    }
}
