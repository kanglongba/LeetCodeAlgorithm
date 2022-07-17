package com.alibaba.edison;

import java.util.Stack;

/**
 * 回文数，easy
 * 给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false 。
 *
 * 回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 *
 * 例如，121 是回文，而 123 不是。
 *
 * 输入：x = 121
 * 输出：true
 *
 * 输入：x = -121
 * 输出：false
 *
 * 输入：x = 10
 * 输出：false
 *
 * author: qonyqian
 * created on: 2022/7/17 22:52
 * version：1.0
 * description:
 */
public class LeetCode9 {

    public boolean isPalindrome(int x) {
        //借用堆栈
        Stack<Character> stack = new Stack<>();
        String num = Integer.toString(x);
        int length = num.length();
        for (int i = 0; i < length; i++) {
            stack.push(num.charAt(i));
        }
        for (int i = 0; i < length; i++) {
            if (num.charAt(i) == stack.pop()) {
                continue;
            } else {
                return false;
            }
        }
        return true;
    }

    public boolean isPalindrome2(int x) {
        //不借用栈
        String num = Integer.toString(x);
        int length = num.length();
        for (int i = 0, j = length-1; i <= j; i++,j--) {
            if (num.charAt(i) == num.charAt(j)) {
                continue;
            } else {
                return false;
            }
        }
        return true;
    }

}
