package com.skeet.consul.provider.mine.concurrent.scheduled;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Desc:
 *
 * @author chengsj
 * @date 2020/10/15 16:31
 */
public class ScheduledPoolTest {

    public static void main(String[] args) {
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);
        System.out.println("start running");

        executor.schedule(() -> {
            System.out.println("I'm coming 3 seconds!!!");
        }, 3, TimeUnit.SECONDS);

        executor.schedule(() -> {
            System.out.println("I'm coming 1 seconds!!!");
        }, 1, TimeUnit.SECONDS);

        executor.scheduleAtFixedRate(() -> {
            System.out.println("I'm coming period per seconds!!!");
//            try {
//                Thread.sleep(500);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }, 5, 1, TimeUnit.SECONDS);

        executor.scheduleWithFixedDelay(() -> {
            System.out.println("I'm coming delay per seconds!!!");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, 5, 1, TimeUnit.SECONDS);
    }
}
