package com.skeet.consul.provider.mine.algoruthm;

/**
 * Desc:写一个函数 StrToInt，实现把字符串转换成整数这个功能。不能使用 atoi 或者其他类似的库函数。
 * <p>
 *  
 * <p>
 * 首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。
 * <p>
 * 当我们寻找到的第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字组合起来，作为该整数的正负号；假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成整数。
 * <p>
 * 该字符串除了有效的整数部分之后也可能会存在多余的字符，这些字符可以被忽略，它们对于函数不应该造成影响。
 * <p>
 * 注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换。
 * <p>
 * 在任何情况下，若函数不能进行有效的转换时，请返回 0。
 * <p>
 * 说明：
 * <p>
 * 假设我们的环境只能存储 32 位大小的有符号整数，那么其数值范围为 [−2^31,  2^31 − 1]。如果数值超过这个范围，请返回  INT_MAX (2^31 − 1) 或 INT_MIN (−2^31) 。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "42"
 * 输出: 42
 * 示例 2:
 * <p>
 * 输入: "   -42"
 * 输出: -42
 * 解释: 第一个非空白字符为 '-', 它是一个负号。
 *      我们尽可能将负号与后面所有连续出现的数字组合起来，最后得到 -42 。
 * 示例 3:
 * <p>
 * 输入: "4193 with words"
 * 输出: 4193
 * 解释: 转换截止于数字 '3' ，因为它的下一个字符不为数字。
 * 示例 4:
 * <p>
 * 输入: "words and 987"
 * 输出: 0
 * 解释: 第一个非空字符是 'w', 但它不是数字或正、负号。
 * 因此无法执行有效的转换。
 * 示例 5:
 * <p>
 * 输入: "-91283472332"
 * 输出: -2147483648
 * 解释: 数字 "-91283472332" 超过 32 位有符号整数范围。
 *      因此返回 INT_MIN (-2^31) 。
 *
 * @author chengsj
 * @date 2020/11/5 16:02
 */
public class Offer67_StringToInt {

    public static void main(String[] args) {
        String str = "+-2";
        System.out.println(solution(str));
    }

    private static int solution(String str) {
        if (str.length() == 0) return 0;

        // 表示是否跳过判断
        boolean skipJudgeFlag = true;
        boolean signPositive = true;
        int result = 0;
        int threshold = Integer.MAX_VALUE / 10;

        for (char c : str.toCharArray()) {
            if (c == ' ') {
                if (skipJudgeFlag) {
                    continue;
                } else {
                    break;
                }
            }

            if (c == '-' && skipJudgeFlag) {
                signPositive = false;
                skipJudgeFlag = false;
                continue;
            }
            if (c == '+' && skipJudgeFlag) {
                signPositive = true;
                skipJudgeFlag = false;
                continue;
            }
            if (c < '0' || c > '9') break;
            if (result > threshold || (result == threshold && c > '7')) {
                return signPositive ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }

            result = result * 10 + (c - '0');
            if (skipJudgeFlag) {
                skipJudgeFlag = false;
            }
        }

        return signPositive ? result : -result;
    }

    private static int solution_Best(String str) {
        int res = 0;
        int boundary = Integer.MAX_VALUE / 10;
        int i = 0;
        int sign = 1;
        int length = str.length();
        if (length == 0) return 0;
        while (str.charAt(i) == ' ')
            if (++i == length) return 0;
        if (str.charAt(i) == '-') sign = -1;
        if (str.charAt(i) == '-' || str.charAt(i) == '+') i++;
        for (int j = i; j < length; j++) {
            if (str.charAt(j) < '0' || str.charAt(j) > '9') break;
            if (res > boundary || res == boundary && str.charAt(j) > '7')
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            res = res * 10 + (str.charAt(j) - '0');
        }
        return sign * res;
    }
}
