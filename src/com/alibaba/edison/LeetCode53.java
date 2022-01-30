package com.alibaba.edison;

/**
 * 最大子数组合，easy
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
