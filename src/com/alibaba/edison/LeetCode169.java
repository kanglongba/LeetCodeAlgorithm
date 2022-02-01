package com.alibaba.edison;

import java.util.Arrays;

/**
 * 多数元素，easy
 * 只想到了排序法
 * 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
 *
 * LeetCode经典题解：暴力法（遍历，所及）、技巧（排序），朴素（分治），聪明（投票法）
 *
 * author: qonyqian
 * created on: 2022/2/1 9:49 下午
 * version：1.0
 * description:
 */
public class LeetCode169 {

    /**
     * 最优算法是投票法
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {
        return vote(nums);
    }

    /**
     * 投票法
     * @param nums
     * @return
     */
    public int vote(int[] nums) {
        int major = nums[0];
        int count = 0;
        for (int num : nums) {
            if (count == 0) {
                major = num;
                count++;
            } else if (major == num) {
                count++;
            } else {
                count--;
            }
        }
        return major;
    }

    /**
     * 最简单的方法是排队，然后取中间值
     * @param nums
     * @return
     */
    public int sort(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length/2];
    }
}
