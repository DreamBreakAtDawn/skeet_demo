package com.skeet.consul.provider.util;

import com.google.common.collect.Maps;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description
 * @Author chengsj
 * @Date 2020/11/26 16:42
 */
public interface Ano {

    Map<Object, Boolean> map = new ConcurrentHashMap<>();

    @FunctionalInterface
    interface AnoInner {

        void logic();
    }
}


class Test {

    void Ano() {

        testAno(new Ano.AnoInner() {

            @Override
            public void logic() {
                Ano.map.putIfAbsent("a", false);
            }
        });
    }

    private void testAno(Ano.AnoInner a) {
        a.logic();
    }

    private void test() {
        Map<String, String> map = Maps.newHashMap();
        map.put("name_1", "code_1");
        map.put("name_2", "code_1");
        map.put("name_1", "code_1");
    }
}