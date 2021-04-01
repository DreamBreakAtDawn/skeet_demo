package com.skeet.consul.provider.mine.algoruthm;

/**
 * @Description 数字以0123456789101112131415…的格式序列化到一个字符序列中。在这个序列中，第5位（从下标0开始计数）是5，第13位是1，第19位是4，等等。
 * <p>
 * 请写一个函数，求任意第n位对应的数字。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 3
 * 输出：3
 * 示例 2：
 * <p>
 * 输入：n = 11
 * 输出：0
 *  
 * <p>
 * 限制：
 * <p>
 * 0 <= n < 2^31
 * @Author chengsj
 * @Date 2021/3/31 16:26
 */
public class Offer44_FindNthDigit {

    public static void main(String[] args) {
//        10 + 90 * 2 + 900 * 3 + 9000 * 4
        System.out.println(solution(1000000000));
        System.out.println(solution(3));
    }

    private static int solution(int n) {
        int base = 9;
        int ratio = 1;
        while (n - (base * ratio < 0 ? Integer.MAX_VALUE : base * ratio) > 0) {
            n -= base * ratio;
            base *= 10;
            ratio += 1;
        }

        int sort = (n + 1) / ratio;
        int pos = (n - 1) % ratio + 1;

        int num = base > 10 ? (base / 9 - 1) + sort : sort - 1;

        num %= (int) Math.pow(10, ratio - pos + 1);
        num /= (int) Math.pow(10, ratio - pos);

        return num;
    }

    /**
     * 用户Krahets的解法
     */
    public int findNthDigit(int n) {
        int digit = 1;
        long start = 1;
        long count = 9;
        while (n > count) { // 1.
            n -= count;
            digit += 1;
            start *= 10;
            count = digit * start * 9;
        }
        long num = start + (n - 1) / digit; // 2.
        return Long.toString(num).charAt((n - 1) % digit) - '0';
    }
}
