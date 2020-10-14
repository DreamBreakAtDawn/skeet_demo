package com.skeet.consul.provider.mine.testextend;

import com.skeet.consul.provider.mine.testextend.entity.Son;

/**
 * Desc:
 *
 * @author chengsj
 * @date 2020/7/28 16:07
 */
public class MainClass {

    public static void main(String[] args) {
        Son son1 = new Son();
        System.out.println("------------------------------------------");
        Son son2 = new Son(18);
    }
}
