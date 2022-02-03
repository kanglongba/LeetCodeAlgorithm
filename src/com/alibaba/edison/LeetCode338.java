package com.alibaba.edison;

/**
 * 比特位计数，easy
 * 给你一个整数 n ，对于 0 <= i <= n 中的每个 i ，计算其二进制表示中 1 的个数 ，返回一个长度为 n + 1 的数组 ans 作为答案。
 *
 * author: qonyqian
 * created on: 2022/2/2 5:43 下午
 * version：1.0
 * description:
 */
public class LeetCode338 {

    /**
     * 整型是32位，每次与1做按位与运算，然后再右移一位，循环32次，便可得到这个整型的1的个数
     * @param n
     * @return
     */
    public int[] countBits(int n) {
        int[] nums = new int[n+1];
        for (int i = 0; i <= n; i++) {
            int count = 0;
            int temp = i;
            for (int j = 0; j < 32; j++) {
                int value = temp & 1;
                count = count + value;
                temp = temp >> 1;
            }
            nums[i] = count;
        }
        return nums;
    }
}
