package com.skeet.consul.provider.mine.other;

/**
 * Desc:
 *
 * @author chengsj
 * @date 2020/11/2 14:20
 */
public class ExceptionBreakPointTest {

    public static void main(String[] args) {
        try {
            int a = 20;
            int b = 15 / 0;
            System.out.println(a + b);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
