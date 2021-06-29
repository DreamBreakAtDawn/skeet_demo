package com.skeet.common.api.util;

import java.util.Random;

/**
 * Desc:
 *
 * @author chengsj
 * @date 2020/10/15 10:12
 */
public class NumUtil {

    private NumUtil() {
    }

    public static void main(String[] args) {
        int[] ints = buildRandomIntArray(100);
        for (int anInt : ints) {
            System.out.println(anInt);
        }
    }

    public static int[] buildRandomIntArray(int count) {
        int[] arr = new int[count];
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            arr[i] = random.nextInt(1000);
        }
        return arr;
    }

    public static int[] buildSortIntArray(int count) {
        int[] arr = new int[count];
        for (int i = 0; i < count; i++) {
            arr[i] = i;
        }
        return arr;
    }
}
