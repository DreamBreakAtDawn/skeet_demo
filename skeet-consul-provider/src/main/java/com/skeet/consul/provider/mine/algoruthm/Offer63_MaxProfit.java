package com.skeet.consul.provider.mine.algoruthm;

/**
 * @Description
 * 假设把某股票的价格按照时间先后顺序存储在数组中，请问买卖该股票一次可能获得的最大利润是多少？
 *
 *  
 *
 * 示例 1:
 *
 * 输入: [7,1,5,3,6,4]
 * 输出: 5
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 *      注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。
 * 示例 2:
 *
 * 输入: [7,6,4,3,1]
 * 输出: 0
 * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 *  
 *
 * 限制：
 *
 * 0 <= 数组长度 <= 10^5
 *
 * @Author chengsj
 * @Date 2021/4/1 15:50
 */
public class Offer63_MaxProfit {

    public static void main(String[] args) {
        int[] arr = new int[]{7,2,8,1,5};
//        int result = solution(arr);
        int i = maxProfit(arr);
        System.out.println(i);
    }

    private static int solution(int[] arr) {
        if (arr.length == 1) {
            return 0;
        }


        int tmpLowPoint = arr[0];
        int tmpHighPoint = arr[0];
        int lowPoint = arr[0];
        int highPoint = arr[0];
        int tmpProfit = 0;
        int resultProfit = 0;

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < tmpLowPoint) {
                tmpLowPoint = arr[i];
            }

            if (arr[i] > tmpHighPoint) {
                tmpHighPoint = arr[i];
            }

            if (arr[i] < arr[i - 1]) {
                continue;
            }


        }
        return 0;
    }

    /**
     * 官方解法
     */
    public static int maxProfit(int prices[]) {
        int minprice = Integer.MAX_VALUE;
        int maxprofit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minprice) {
                minprice = prices[i];
            } else if (prices[i] - minprice > maxprofit) {
                maxprofit = prices[i] - minprice;
            }
        }
        return maxprofit;
    }
}
