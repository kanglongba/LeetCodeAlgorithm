package com.alibaba.edison;

import java.util.HashMap;

/**
 * 只出现一次的数字，easy
 * <p>
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 * author: qonyqian
 * created on: 2022/1/31 5:11 下午
 * version：1.0
 * description:
 */
public class LeetCode136 {

    public int singleNumber(int[] nums) {
        return hash(nums);
    }

    /**
     * 最简单的就是hashmap
     * 需要遍历两次，时间复杂度O(2n)
     *
     * @param nums
     * @return
     */
    public int hash(int[] nums) {
        HashMap<Integer, Integer> hash = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (hash.get(nums[i]) == null) {
                hash.put(nums[i], 1);
            } else {
                hash.put(nums[i], hash.get(nums[i]) + 1);
            }
        }
        for (Integer key : hash.keySet()) {
            if (hash.get(key) == 1) {
                return key;
            }
        }
        return -1;
    }

    /**
     * 未能独立想到最优解
     * 最优解：使用异或，https://leetcode-cn.com/problems/single-number/solution/zhi-chu-xian-yi-ci-de-shu-zi-by-leetcode-solution/
     *
     * 位运算
     * 与：全为1才为1，运算符 &
     * 或：有一个为1就为1，运算符 |
     * 非：0为1，1为0，运算符 ~
     * 异或：相异为1，相同为0，运算符 ^
     * 同或：相同为1，相异为0，Java中没有同或运算符
     *
     * 移位操作：https://zhuanlan.zhihu.com/p/30108890
     * 左移：<<，符号位也会被移掉，左边产生的空位补0
     * 右移：>>，左边空位补上符号位的值（符号位是1，就补1；符号位是0，就补0）
     * 无符号右移：>>>，左边空位固定补0
     * 所有的移位运算，符号位都会跟着走，差别只是在补位的策略不同
     *
     * @param nums
     * @return
     */
    public int xor(int[] nums) {
        int single = 0;
        for (int num : nums) {
            single ^= num;
        }
        return single;
    }
}
