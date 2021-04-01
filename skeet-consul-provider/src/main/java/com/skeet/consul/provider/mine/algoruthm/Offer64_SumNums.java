package com.skeet.consul.provider.mine.algoruthm;

/**
 * @Description
 * 求 1+2+...+n ，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。
 *
 *  
 *
 * 示例 1：
 *
 * 输入: n = 3
 * 输出: 6
 * 示例 2：
 *
 * 输入: n = 9
 * 输出: 45
 *  
 *
 * 限制：
 *
 * 1 <= n <= 10000
 *
 * @Author chengsj
 * @Date 2021/4/1 15:13
 */
public class Offer64_SumNums {

    public static void main(String[] args) {
        System.out.println(solution(1));
        System.out.println(solution(10000));
    }

    private static int solution(int n) {
        return (int) (Math.pow(n, 2) + n) >> 1;
    }

    /**
     * 力扣官方解法
     */
    public int sumNums(int n) {
        boolean flag = n > 0 && (n += sumNums(n - 1)) > 0;
        return n;
    }
}
