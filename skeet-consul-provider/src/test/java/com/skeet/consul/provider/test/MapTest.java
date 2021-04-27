package com.skeet.consul.provider.test;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * @Description
 * @Author chengsj
 * @Date 2021/4/21 14:37
 */
public class MapTest {

    public static void main(String[] args) {
        Map<Integer, String> map = Maps.newLinkedHashMap();
        map.put(1, "1");
        map.put(2, "1");
        map.put(5, "1");
        map.put(4, "1");
        map.put(3, "1");
        map.forEach((k, v) -> System.out.println(k));
    }
}
