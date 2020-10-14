package com.skeet.consul.provider.mine.loadclass.entity;

/**
 * Desc:
 *
 * @author chengsj
 * @date 2020/7/29 17:00
 */
public class LoadClass {

    public static final String PROPERTY = "SOLO ME";

    static {
        System.out.println("I'm loading");
    }

    public static String getName() {
        return "CLEAR LOVE";
    }
}
