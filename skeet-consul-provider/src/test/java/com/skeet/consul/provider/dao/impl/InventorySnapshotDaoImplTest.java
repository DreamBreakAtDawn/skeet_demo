package com.skeet.consul.provider.dao.impl;

import com.skeet.consul.provider.ConsulProviderApplication;
import com.skeet.consul.provider.modules.project.order.dao.impl.InventorySnapshotDaoImpl;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Desc:
 *
 * @author chengsj
 * @date 2020/4/14 17:53
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ConsulProviderApplication.class)
public class InventorySnapshotDaoImplTest {

    @Resource
    private InventorySnapshotDaoImpl inventorySnapshotDao;
}
