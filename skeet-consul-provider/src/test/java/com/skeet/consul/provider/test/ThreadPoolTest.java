package com.skeet.consul.provider.test;

import com.skeet.consul.provider.ConsulProviderApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Desc:
 *
 * @author chengsj
 * @date 2020/4/21 20:39
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ConsulProviderApplication.class)
public class ThreadPoolTest {

    private static final ExecutorService EXECUTOR = Executors.newFixedThreadPool(20);

    @Test
    public void testPool() {
        List<Integer> integers = Arrays.asList(1, 2, 5, 7, 9);
        integers.forEach(i -> EXECUTOR.execute(() -> System.out.println(i)));
    }

}
