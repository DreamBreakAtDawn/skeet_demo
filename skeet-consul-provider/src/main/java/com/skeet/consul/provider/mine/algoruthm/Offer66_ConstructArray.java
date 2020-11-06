package com.skeet.consul.provider.mine.algoruthm;

/**
 * Desc:给定一个数组 A[0,1,…,n-1]，请构建一个数组 B[0,1,…,n-1]，其中 B 中的元素 B[i]=A[0]×A[1]×…×A[i-1]×A[i+1]×…×A[n-1]。
 * 不能使用除法。
 * <p>
 *  
 * <p>
 * 示例:
 * <p>
 * 输入: [1,2,3,4,5]
 * 输出: [120,60,40,30,24]
 *  
 * <p>
 * 提示：
 * <p>
 * 所有元素乘积之和不会溢出 32 位整数
 * a.length <= 100000
 *
 * @author chengsj
 * @date 2020/11/5 17:33
 */
public class Offer66_ConstructArray {

    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4, 5};
        int[] res = solution(a);
        for (int i : res) {
            System.out.print(i + " ");
        }
    }

    private static int[] solution(int[] a) {
        if (a == null || a.length == 0) return a;
        int length = a.length;
        int[] b = new int[length];
        b[0] = 1;
        int tmp = 1;
        for (int i = 1; i < length; i++) {
            b[i] = b[i - 1] * a[i - 1];
        }

        for (int i = length - 2; i >= 0; i--) {
            tmp *= a[i + 1];
            b[i] *= tmp;
        }
        return b;
    }
}
