package com.alibaba.edison;

/**
 * 爬楼梯，easy
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * author: qonyqian
 * created on: 2022/1/30 6:08 下午
 * version：1.0
 * description:
 */
public class LeetCode70 {

    /**
     * 动态规划，dp[i]表示到第n阶台阶的爬法
     * 状态转移方程：dp[i]=dp[i-1]+dp[i-2]
     *
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        //可以优化dp数组，使空间复杂度变为O(1)
        int[] dp = new int[n + 1];
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
}
