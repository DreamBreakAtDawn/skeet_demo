package com.skeet.h2.service;

import com.skeet.h2.SkeetH2Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description
 * @Author chengsj
 * @Date 2021/3/22 14:06
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SkeetH2Application.class)
public class ServiceTest {

    @Resource
    private UserService userService;
    @Resource
    private List<BaseService> baseServices;

    @Test
    public void testRepeatDependency() {
        userService.getUserName();
        baseServices.forEach(BaseService::getUserName);
    }
}
