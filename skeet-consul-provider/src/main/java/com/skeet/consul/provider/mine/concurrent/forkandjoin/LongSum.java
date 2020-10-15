package com.skeet.consul.provider.mine.concurrent.forkandjoin;

import java.util.concurrent.RecursiveTask;

/**
 * Desc:
 *
 * @author chengsj
 * @date 2020/10/14 17:03
 */
public class LongSum extends RecursiveTask<Long> {

    public static final int SEQUENTIAL_THRESHOLD = 1000;
    public static final long NPS = 1000L * 1000 * 1000;
    public static final boolean extraWork = true;
    int low;
    int high;
    int[] array;

    public LongSum(int[] arr, int lo, int hi) {
        array = arr;
        low = lo;
        high = hi;
    }

    @Override
    protected Long compute() {
        if (high - low <= SEQUENTIAL_THRESHOLD) {
            long sum = 0;
            for (int i = low; i < high; i++) {
                sum += array[i];
            }

            return sum;
        } else {
            int mid = low + (high - low) / 2;
            LongSum left = new LongSum(array, low, mid);
            LongSum right = new LongSum(array, mid, high);
            left.fork();
            right.fork();
            Long rightAns = right.join();
            Long leftAns = left.join();
//            Long rightAns = right.compute();
//            Long leftAns = left.compute();
            return leftAns + rightAns;
        }
    }
}
