package com.skeet.tkmybatis;

import com.skeet.tkmybatis.domain.entity.UmsUser;
import com.skeet.tkmybatis.mapper.UmsUserMapper;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SkeetTkMybatisApplication.class)
public class SkeetTkMybatisApplicationTest {

    @Resource
    private UmsUserMapper umsUserMapper;

    @Test
    public void testBatchUpdateByPrimaryKeySelective() {
        List<UmsUser> list = Lists.newArrayList(
                UmsUser.builder().tid(1).username("a").build(),
                UmsUser.builder().tid(2).username("b").build(),
                UmsUser.builder().tid(3).username("c").build(),
                UmsUser.builder().tid(4).username("d").build()
        );
        int result = umsUserMapper.batchUpdateByPrimaryKeySelective(list);
        Assert.assertTrue(result > 0);
//        for (UmsUser umsUser : list) {
//            umsUserMapper.updateByPrimaryKeySelective(umsUser);
//        }
    }

}
