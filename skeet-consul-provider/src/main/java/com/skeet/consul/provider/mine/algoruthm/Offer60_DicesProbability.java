package com.skeet.consul.provider.mine.algoruthm;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Desc:把n个骰子扔在地上，所有骰子朝上一面的点数之和为s。输入n，打印出s的所有可能的值出现的概率。
 * <p>
 *  
 * <p>
 * 你需要用一个浮点数数组返回答案，其中第 i 个元素代表这 n 个骰子所能掷出的点数集合中第 i 小的那个的概率。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: 1
 * 输出: [0.16667,0.16667,0.16667,0.16667,0.16667,0.16667]
 * 示例 2:
 * <p>
 * 输入: 2
 * 输出: [0.02778,0.05556,0.08333,0.11111,0.13889,0.16667,0.13889,0.11111,0.08333,0.05556,0.02778]
 *
 * @author chengsj
 * @date 2020/11/6 17:27
 */
public class Offer60_DicesProbability {

    public static void main(String[] args) {
        int n = 2;
        for (double i : solution_Best(n)) {
            System.out.print(i + " ");
        }
    }

    private static double[] solution(int n) {
        int[] fst = new int[]{1};
        int[] scd = new int[]{0, 1, 1, 1, 1, 1, 1};
        int[] res = null;
        int startPos = 0;
        for (int i = 1; i <= n; i++) {

            int length = Math.multiplyExact(6, i);
            res = new int[length + 1];
            for (int j = startPos; j < fst.length; j++) {
                for (int k = 1; k < scd.length; k++) {
                    // 点数相加后的位置
                    int pos = j + k;
                    // 点数出现的次数
                    int val = fst[j] * scd[k];
                    // 对应位置的点数累加
                    res[pos] += val;
                }
            }

            fst = res;
            startPos++;
        }

        double[] resDouble = new double[res.length - startPos];
        int total = (int) Math.pow(6, n);
        for (int p = 0; p < resDouble.length; p++) {
            resDouble[p] = BigDecimal.valueOf(res[startPos + p]).divide(BigDecimal.valueOf(total), 5, RoundingMode.HALF_UP).doubleValue();
        }

        return resDouble;
    }

    private static double[] solution_Best(int n) {
        int[][] dp = new int[n + 1][6 * n + 1];
        for (int i = 1; i <= 6; i++)
            dp[1][i] = 1;

        for (int i = 2; i <= n; i++)
            for (int j = i; j <= 6 * i; j++)
                for (int k = 1; k <= 6 && k <= j; k++)
                    dp[i][j] += dp[i - 1][j - k];

        double[] ans = new double[6 * n - n + 1];
        int total = (int) Math.pow(6, n);
        for (int i = n; i <= 6 * n; i++)
            ans[i - n] = BigDecimal.valueOf(dp[n][i]).divide(BigDecimal.valueOf(total), 5, RoundingMode.HALF_UP).doubleValue();

        return ans;
    }

}
