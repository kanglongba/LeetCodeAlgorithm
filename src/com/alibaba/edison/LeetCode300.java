package com.alibaba.edison;

/**
 * 最长递增子序列，medium
 * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 * 子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
 * <p>
 * author: qonyqian
 * created on: 2022/2/13 4:34 下午
 * version：1.0
 * description:
 */
public class LeetCode300 {

    /**
     * 凭直觉就是动态规划
     * dp[i]表示以 nums[i] 为结尾的最长递增子序列的长度
     * 状态转移方程：遍历 [0, i-1]，dp[i] = max(dp[j]) + 1，0 <= j <= i-1 且 nums[i] > nums[j]
     *
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        int length = nums.length;
        if (length == 1) {
            return 1;
        }
        int[] dp = new int[length];
        dp[0] = 1; //最短的递增子序列就是它自己
        int max = 1;
        for (int i = 1; i < length; i++) {
            dp[i] = 1; //最短的递增子序列就是它自己
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] <= nums[j]) {
                    continue;
                } else {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }
}
