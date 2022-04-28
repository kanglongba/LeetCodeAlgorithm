package com.alibaba.edison;

/**
 * 最大子数组合，easy
 * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * 子数组 是数组中的一个连续部分
 *
 * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出：6
 * 解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
 *
 * author: qonyqian
 * created on: 2022/1/30 4:27 下午
 * version：1.0
 * description:
 */
public class LeetCode53 {

    /**
     * 动态规划，dp[i]表示以nums[i]为结尾的，最大子数组的和
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        //可以优化dp数组，使空间复杂度变为 O(1)
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int max = dp[0];
        for (int i = 1; i < nums.length; i++) {
            //状态转移方程：dp[i] = max(nums[i], dp[i-1]+nums[i])
            dp[i] = Math.max(nums[i], dp[i-1]+nums[i]);
            max = Math.max(max, dp[i]);
        }
        return max;
    }
}
