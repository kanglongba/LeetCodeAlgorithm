package com.alibaba.edison;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 整数反转，medium
 * 给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
 * <p>
 * 如果反转后整数超过 32 位的有符号整数的范围[−231, 231− 1] ，就返回 0。
 * <p>
 * 假设环境不允许存储 64 位整数（有符号或无符号）。
 * <p>
 * 输入：x = 123
 * 输出：321
 * <p>
 * 输入：x = -123
 * 输出：-321
 * <p>
 * 输入：x = 120
 * 输出：21
 * <p>
 * author: qonyqian
 * created on: 2022/7/17 22:32
 * version：1.0
 * description:
 */
public class LeetCode7 {

    /**
     * 处理不了超限的问题
     * @param x
     * @return
     */
    public int reverse(int x) {
        String num = String.valueOf(x);
        int length = num.length();
        LinkedList<Character> queue = new LinkedList<>();
        for (int i = 0; i < length; i++) {
            queue.push(num.charAt(i));
        }
        StringBuilder builder = new StringBuilder();
        while (!queue.isEmpty()) {
            char value = queue.pop();
            if (value == '-') {
                builder.insert(0, value);
            } else {
                builder.append(value);
            }
        }
        return Integer.parseInt(builder.toString());
    }
}
