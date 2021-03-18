package com.skeet.h2.mapper;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.skeet.h2.SkeetH2Application;
import com.skeet.h2.entity.UmsUser;
import com.skeet.h2.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import javax.annotation.Resource;

/**
 * @Description
 * @Author chengsj
 * @Date 2021/3/15 16:19
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SkeetH2Application.class)
@TestExecutionListeners({
        MockitoTestExecutionListener.class,
        DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
@ActiveProfiles({"local", "utest"})
@ContextConfiguration(locations = "classpath:dataSource.xml")
public class MapperTest {

    @MockBean
    private UserService userService;

    @Resource
    private UmsUserMapper umsUserMapper;

    @Test
    @DatabaseSetup(value = "/com/skeet/h2/mapper/User_Gen.xml", type = DatabaseOperation.INSERT)
    @ExpectedDatabase(value = "/com/skeet/h2/mapper/User_Cmp.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void testUpdateUser() {
        Mockito.when(userService.getUserName()).thenReturn("my_sky");
        UmsUser user = UmsUser.builder().tid(20).username("talon1").password("dead").build();
        int result = umsUserMapper.updateByPrimaryKeySelective(user);
        Assert.assertTrue(result > 0);
    }
}
