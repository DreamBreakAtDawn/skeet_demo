package com.skeet.consul.provider.mine.other;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Desc:
 *
 * @author chengsj
 * @date 2020/11/2 14:45
 */
public class BreakPointBooleanTest {

    public static void main(String[] args) {
        for (String str : getList()) {
            System.out.println(str);
        }

        System.out.println("do other something");
    }

    private static List<String> getList() {
        return Lists.newArrayList("yasuo", "zed", "talon");
    }
}
