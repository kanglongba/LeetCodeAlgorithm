package com.alibaba.edison;

import java.util.Stack;

/**
 * 有效的括号，easy
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']'的字符串 s ，判断字符串是否有效。
 *
 * 有效字符串需满足：
 * 1.左括号必须用相同类型的右括号闭合。
 * 2.左括号必须以正确的顺序闭合。
 *
 * 输入：s = "([)]"
 * 输出：false
 *
 * 输入：s = "{[]}"
 * 输出：true
 *
 * author: qonyqian
 * created on: 2022/1/30 2:31 下午
 * version：1.0
 * description:
 */
public class LeetCode20 {

    public boolean isValid(String s) {
        int length = s.length();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < length; i++) {
            if (stack.isEmpty()) {
                stack.push(s.charAt(i));
            } else if (s.charAt(i) == '(' || s.charAt(i) == '[' || s.charAt(i) == '{') {
                stack.push(s.charAt(i));
            } else if (s.charAt(i) == ')') {
                if (stack.peek() == '(') {
                    stack.pop();
                } else {
                    stack.push(s.charAt(i));
                }
            } else if (s.charAt(i) == ']') {
                if (stack.peek() == '[') {
                    stack.pop();
                } else {
                    stack.push(s.charAt(i));
                }
            } else if (s.charAt(i) == '}') {
                if (stack.peek() == '{') {
                    stack.pop();
                } else {
                    stack.push(s.charAt(i));
                }
            }
        }
        return stack.isEmpty();
    }
}
