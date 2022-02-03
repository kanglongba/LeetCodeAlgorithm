package com.alibaba.edison;

/**
 * 汉明距离,easy
 * 两个整数之间的 汉明距离 指的是这两个数字对应二进制位不同的位置的数目。
 * 给你两个整数 x 和 y，计算并返回它们之间的汉明距离。
 *
 * author: qonyqian
 * created on: 2022/2/3 12:09 上午
 * version：1.0
 * description:
 */
public class LeetCode461 {

    /**
     * 异或 + 右移
     * @param x
     * @param y
     * @return
     */
    public int hammingDistance(int x, int y) {
        int sum = x ^ y;
        int count = 0;
        for (int i = 0; i < 32; i++) {
            count += sum & 1;
            sum >>= 1;
        }
        return count;
    }
}
