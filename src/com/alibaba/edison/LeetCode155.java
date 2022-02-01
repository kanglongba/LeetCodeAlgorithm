package com.alibaba.edison;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * 最小栈，easy
 * 未能独立解答
 * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
 *
 * push(x) —— 将元素 x 推入栈中。
 * pop() —— 删除栈顶的元素。
 * top() —— 获取栈顶元素。
 * getMin() —— 检索栈中的最小元素。
 *
 *
 * author: qonyqian
 * created on: 2022/1/31 10:26 下午
 * version：1.0
 * description:
 */
public class LeetCode155 {

    Stack<Integer> stack = new Stack<>();
    Deque<Integer> deque = new LinkedList<>();

    /**
     * 难点在于getMin，使用两个栈，一个栈当做正常栈来用，另一个栈同步插入当前最小元素，两个栈保持同步
     */
    public LeetCode155() {
        deque.push(Integer.MAX_VALUE);
    }

    public void push(int val) {
        stack.push(val);
        deque.push(Math.min(deque.peek(), val));
    }

    public void pop() {
        stack.pop();
        deque.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return deque.peek();
    }
}
