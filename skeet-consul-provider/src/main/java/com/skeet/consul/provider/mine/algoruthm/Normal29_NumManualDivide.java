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
        int dividend = -2147483648;
        int divisor = -1;
        int result = divide(dividend, divisor);
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
}
