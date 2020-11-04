package com.skeet.consul.provider.mine.algoruthm;

/**
 * Desc:给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。
 *
 * 返回被除数 dividend 除以除数 divisor 得到的商。
 *
 * 整数除法的结果应当截去（truncate）其小数部分，例如：truncate(8.345) = 8 以及 truncate(-2.7335) = -2
 *
 *  
 *
 * 示例 1:
 *
 * 输入: dividend = 10, divisor = 3
 * 输出: 3
 * 解释: 10/3 = truncate(3.33333..) = truncate(3) = 3
 * 示例 2:
 *
 * 输入: dividend = 7, divisor = -3
 * 输出: -2
 * 解释: 7/-3 = truncate(-2.33333..) = -2
 *
 * @author chengsj
 * @date 2020/11/3 19:05
 */
public class Normal29_NumManualDivide {

    public static void main(String[] args) {
        int dividend = 2147483647;
        int divisor = 2;
//        int result = divide(dividend, divisor);
        int result = divide_Best(dividend, divisor);
        System.out.println(result);
    }

    private static int divide(int dividend, int divisor) {
        boolean navigate = false;
        if (dividend < 0) {
            dividend = -dividend;
            navigate = !navigate;
        }

        if (divisor < 0) {
            divisor = -divisor;
            navigate = !navigate;
        }

        int result = 0;
        int minus;
        while (true) {
            minus = dividend - divisor;
            dividend = minus;
            if (minus >= 0) {
                result++;
            } else {
                break;
            }
        }

        if (navigate) {
            result = -result;
        }

        return result;
    }

    private static int divide_Best(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE && divisor == -1) return Integer.MAX_VALUE;
        if (divisor == -1) return -dividend;
        if (divisor == 1) return dividend;

        int sign = dividend ^ divisor;

        if (dividend > 0) dividend = -dividend;
        if (divisor > 0) divisor = -divisor;

        int count = div(dividend, divisor);
        return (sign >= 0) ? count : -count;
    }

    private static int div(int dividend, int divisor) {
        int move = 0;
        int count = 1;
        if (dividend > divisor) return 0;

        int divTmp = divisor;
        // Integer.MIN_VALUE >> 1 <= divTmp 防止int型溢出
        while (Integer.MIN_VALUE >> 1 <= divTmp && dividend <= divTmp << 1) {
            divTmp = divTmp << 1;
            move++;
        }

        return (count << move) + div(dividend - divTmp, divisor);
    }
}
