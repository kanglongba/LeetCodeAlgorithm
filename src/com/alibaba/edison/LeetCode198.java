package com.alibaba.edison;

/**
 * 打家劫舍，medium
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果
 * 两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * <p>
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
 *
 * 过于经典的动态规划题目
 *
 * <p>
 * author: qonyqian
 * created on: 2022/2/10 11:09 下午
 * version：1.0
 * description:
 */
public class LeetCode198 {

    /**
     * 经典的动态规划题目
     * dp[i] 表示以 nums[i] 结尾的偷盗最大值
     * 状态转移方程：dp[i] = max(dp[i-1], dp[i-2] + nums[i])
     *
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        int length = nums.length;
        if (length == 1) {
            return nums[0];
        }
        if (length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        int[] dp = new int[length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        int max = Math.max(dp[0], dp[1]);
        for (int i = 2; i < length; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
            max = Math.max(max, dp[i]);
        }
        return max;
    }
}
