package com.skeet.consul.provider.mine.testextend.entity;

import lombok.Data;

/**
 * Desc:
 *
 * @author chengsj
 * @date 2020/7/28 16:08
 */
@Data
public class Son extends Father {

    public Son() {
        System.out.println("I'm Son");
    }

    public Son(Integer age) {
        System.out.println("I'm Son, My age is " + age);
    }
}
