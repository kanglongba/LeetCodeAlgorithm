package com.alibaba.edison;

import java.util.Stack;

/**
 * Created by 钧戈 on 2020/10/16.
 */
public class Sword31 {

    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> stack = new Stack<>();
        int i = 0;
        for (int num : pushed) {
            stack.push(num); // num 入栈
            while (!stack.isEmpty() && stack.peek() == popped[i]) { // 循环判断与出栈
                stack.pop();
                i++;
            }
        }
        return stack.isEmpty();
    }

    /**
     * 惭愧，这道题看答案都看了好久
     * 需要借助辅助栈。
     * 入栈的顺序是固定的，出栈的顺序也是固定的，因此每次入栈一个新元素，都要判断是否为当前出栈的元素
     * 如果不是，继续入栈下一个元素，
     * 如果是，立即出栈，然后再判断栈中的其余元素，因为之前入栈的元素可能不满足出栈条件，没有被出栈。
     * 最后如果栈为空，表示全部顺利出栈。
     * @param pushed
     * @param popped
     * @return
     */
    public boolean validateStackSequences1(int[] pushed, int[] popped) {
        Stack<Integer> stack = new Stack<>();
        int i = 0;
        for (int num : pushed) {
            stack.push(num); // num 入栈
            while (!stack.isEmpty() && stack.peek() == popped[i]) { // 循环判断与出栈
                stack.pop();
                i++;
            }
        }
        return stack.isEmpty();
    }
}
