package com.skeet.consul.provider.mine.algoruthm;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Desc:
 *
 * @author chengsj
 * @date 2020/10/14 14:24
 */
public class StackPushAndPopJudge {

    public static void main(String[] args) {
        int[] pushed = {1,2,3,4,5};
        int[] popped = {4,5,3,2,1};
        System.out.println(validateStackSequences(pushed, popped));
    }

    public static boolean validateStackSequences(int[] pushed, int[] popped) {
        Deque<Integer> stack = new ArrayDeque<>();
        int i = 0;
        for (int ele : pushed) {
            stack.push(ele);
            while (!stack.isEmpty() && stack.peek().equals(popped[i])) {
                stack.pop();
                i++;
            }
        }

        return i == popped.length;
    }
}
