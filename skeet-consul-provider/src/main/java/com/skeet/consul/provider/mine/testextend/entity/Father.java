package com.skeet.consul.provider.mine.testextend.entity;

/**
 * Desc:
 *
 * @author chengsj
 * @date 2020/7/28 16:08
 */
public class Father extends GrandFather {

    public Father() {
        System.out.println("I'm Father");
    }

    public Father(String name) {
        System.out.println("I'm Father");
        System.out.println(name);
    }
}
