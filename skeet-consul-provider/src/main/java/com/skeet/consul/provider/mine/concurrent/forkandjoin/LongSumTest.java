package com.skeet.consul.provider.mine.concurrent.forkandjoin;

import com.skeet.consul.provider.util.NumUtil;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

/**
 * Desc:
 *
 * @author chengsj
 * @date 2020/10/14 18:38
 */
public class LongSumTest {

    public static final int NCPU = Runtime.getRuntime().availableProcessors();
    public static final long NPS = 1000L * 1000 * 1000;
    public static final boolean reportSteals = true;
    public static long calcSum;

    public static void main(String[] args) throws Exception {
        int[] array = NumUtil.buildSortIntArray(100);
        System.out.println("cpu - num: " + NCPU);
        calcSum = seqSum(array);
        System.out.println("seq sum = " + calcSum);

        LongSum ls = new LongSum(array, 0 , array.length);
        ForkJoinPool forkJoinPool = new ForkJoinPool(4);
        ForkJoinTask<Long> result = forkJoinPool.submit(ls);
        System.out.println("fork/join sum = " + result.get());

        forkJoinPool.shutdown();
    }

    public static long seqSum(int[] arr) {
        long sum = 0;
        for (int value : arr) {
            sum += value;
        }

        return sum;
    }
}
