package com.skeet.consul.provider.config;

import com.skeet.consul.provider.ConsulProviderApplication;
import com.skeet.consul.provider.model.entity.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.SQLOutput;
import java.util.Map;

/**
 * Desc:
 *
 * @author chengsj
 * @date 2020/4/9 18:11
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ConsulProviderApplication.class)
public class BeanConfigTest {

    private AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BeanConfig.class);

    @Test
    public void testPerson() {
        Map<String, Person> beansOfType = context.getBeansOfType(Person.class);
        beansOfType.forEach((k,v) -> {
            System.out.println(k + "：" + v);
        });
    }
}
