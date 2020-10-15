package com.skeet.consul.provider.mine.concurrent.scheduled;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Desc:
 *
 * @author chengsj
 * @date 2020/10/15 17:16
 */
public class ScheduledPoolOverPeriodTest {

    public static void main(String[] args) {
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(2);
        long startMills = System.currentTimeMillis();
        executor.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("overflow~~~~~");
                try {
                    if (System.currentTimeMillis() - startMills < 1000 * 15) {
                        System.out.println("do sleeping");
                        Thread.sleep(2000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, 3, 1, TimeUnit.SECONDS);

        executor.scheduleAtFixedRate(() -> {
            System.out.println("----------------- second -----------------");
        }, 3, 1, TimeUnit.SECONDS);
    }
}
