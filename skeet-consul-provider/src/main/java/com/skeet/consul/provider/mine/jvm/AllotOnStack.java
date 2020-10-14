package com.skeet.consul.provider.mine.jvm;

import com.google.common.collect.Lists;
import com.skeet.consul.provider.mine.jvm.entity.User;

import java.util.List;

/**
 * Desc:
 *
 * @author chengsj
 * @date 2020/8/28 10:46
 */
public class AllotOnStack {

    public static void main(String[] args) {
        List<User> list = Lists.newArrayList();
        User user = null;
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000000; i++) {
//            alloc();
            user = allocReturn();
            user.setId(2);
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    private static void alloc() {
        User user = new User();
        user.setId(1);
        user.setName("zhuge");
    }

    private static User allocReturn() {
        User user = new User();
        user.setId(1);
        user.setName("zhuge");
        return user;
    }
}
