package com.skeet.consul.provider.service;

import com.google.common.collect.Lists;
import com.skeet.consul.provider.ConsulProviderApplication;
import com.skeet.consul.provider.modules.project.order.model.domain.InventorySnapshotCriteria;
import com.skeet.consul.provider.modules.project.order.service.InventorySnapshotService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * Desc:
 *
 * @author chengsj
 * @date 2020/4/21 20:51
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ConsulProviderApplication.class)
public class InventorySnapshotServiceImplTest {

    @Resource
    private InventorySnapshotService inventorySnapshotService;

    @Test
    public void testSelectChargeData() {
        List<String> customerIds = Arrays.asList("P5ZXNCM", "P622C4A", "CNQAHJQ6", "T5ZMVP3", "75Z4SF6", "P5Z2BR7", "CNUB3M66",
                "W5Z6WB5", "BUYDDXD", "L5ZX5X6", "B5ZMYZ9", "T5ZMWRE", "T5Z4PA8", "75ZMF96", "CNCGLJL5", "CNTWL9F7", "H5Z26W4",
                "CNHNDJD1", "CN47NZV2", "W5Z4YXD", "75ZXFG3", "CNDW2XN4", "H5Z4JWA", "CN64A9A6", "L5ZXE3B", "75Z45AA", "CNHBHKA7",
                "T5Z4LKA", "CNAFMH85", "B5ZMMUA", "UBLAXMF", "E5Z34GE", "CNRPJJ76", "CNNMBMJ5", "H5Z2G54", "75Z2FM2", "CNJMZTF4", "P5ZX5S8");

//        List<Integer> snapshotTimes = TimeUtil.getDateRegion(20200101, 20200331);
        List<Integer> snapshotTimes = Lists.newArrayList(20200229);
        InventorySnapshotCriteria criteria = InventorySnapshotCriteria.builder()
                .snapshotTime(20200501)
//                .customerIds(customerIds)
                .build();
        inventorySnapshotService.selectBy(criteria);
    }
}
