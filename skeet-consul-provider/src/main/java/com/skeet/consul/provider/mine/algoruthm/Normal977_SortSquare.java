package com.skeet.consul.provider.mine.algoruthm;

/**
 * Desc:给定一个按非递减顺序排序的整数数组 A，返回每个数字的平方组成的新数组，要求也按非递减顺序排序。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：[-4,-1,0,3,10]
 * 输出：[0,1,9,16,100]
 * 示例 2：
 *
 * 输入：[-7,-3,2,3,11]
 * 输出：[4,9,9,49,121]
 *
 * @author chengsj
 * @date 2020/10/16 10:43
 */
public class Normal977_SortSquare {

    public static void main(String[] args) {
        int[] arr = new int[] {-7,-3,2,3,11};
        sortedSquares_Best(arr);
    }

    public static int[] sortedSquares(int[] A) {
        for (int i = 0; i < A.length - 1; i++) {
            if (A[0] > 0) {
                break;
            }

            for (int j = 0; j < A.length - 1; j++) {

                if (Math.abs(A[j]) >= Math.abs(A[j + 1])) {
                    int tmp = A[j];
                    A[j] = A[j + 1];
                    A[j + 1] = tmp;
                } else {
                    break;
                }
            }
        }

        int[] result = new int[A.length];
        for (int k = 0; k < A.length; k++) {
            result[k] = A[k] * A[k];
        }

        return result;
    }


    public static int[] sortedSquares_Best(int[] A) {
        int[] result = new int[A.length];

        for (int i = 0, j = A.length - 1, pos = A.length - 1; i <= j;) {
            int fst = A[i] * A[i];
            int scd = A[j] * A[j];
            if (fst > scd) {
                result[pos--] = fst;
                i++;
            } else {
                result[pos--] = scd;
                j--;
            }
        }

        return result;
    }
}
