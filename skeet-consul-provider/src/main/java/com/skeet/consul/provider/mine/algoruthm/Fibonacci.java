package com.skeet.consul.provider.mine.algoruthm;

import java.util.Map;

/**
 * Desc:斐波那契
 *
 * @author chengsj
 * @date 2020/11/6 16:26
 */
public class Fibonacci {

    public static void main(String[] args) {
        int layer = 8;
        System.out.println(solution(layer));
        System.out.println(climbStairs(layer));
    }

    private static int solution(int layer) {
        if (layer == 1 || layer == 0) {
            return 1;
        }

        return solution(layer -1) + solution(layer - 2);
    }

    private static int solution(int layer, Map<Integer,Integer> map) {
        if (layer == 1 || layer == 0) {
            return 1;
        }

        return solution(layer -1) + solution(layer - 2);
    }

    public static int climbStairs(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 1;
        if (n==1) return dp[1];
        dp[2] = 2;
        if (n==2) return dp[2];
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
}
