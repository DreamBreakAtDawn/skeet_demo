package com.skeet.consul.provider.mine.other;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.skeet.consul.provider.mine.testextend.entity.Father;
import com.skeet.consul.provider.mine.testextend.entity.Son;

import java.util.HashMap;

/**
 * Desc:
 *
 * @author chengsj
 * @date 2020/11/10 14:23
 */
public class ClassJudgeTest {

    public static void main(String[] args) {
        Father f = new Father();
        Son s = new Son();
        System.out.println(f instanceof Object);
        System.out.println(f.getClass().isAssignableFrom(s.getClass()));

        HashMap<String, Integer> map = new HashMap<>(9);
        map.put("orgRole", 1);
        map.put("bondName", 3);
        map.put("pubAmount", 10);
        map.put("pubRating", 2);
        System.out.println(JSON.toJSONString(map));
        System.out.println(JSON.toJSONString(Lists.newArrayList(map.entrySet())));
    }
}
