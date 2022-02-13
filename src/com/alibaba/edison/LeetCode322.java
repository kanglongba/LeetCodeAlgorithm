package com.alibaba.edison;

/**
 * 零钱兑换，medium
 * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
 * 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回-1 。
 * 你可以认为每种硬币的数量是无限的。
 *
 * 非常非常经典的一道题。
 *
 * 我知道用动态规划，但是没有写出代码
 * 评论说是腾讯二面真题
 *
 * author: qonyqian
 * created on: 2022/2/13 8:50 下午
 * version：1.0
 * description:
 */
public class LeetCode322 {

    /**
     * 动态规划
     * 经典题解：https://leetcode-cn.com/problems/coin-change/solution/javadi-gui-ji-yi-hua-sou-suo-dong-tai-gui-hua-by-s/
     *
     * dp[i] 表示金额为 i ，需要的最小硬币数量
     * 状态转移方程：dp[i] = min(dp[i-coins[j]]) + 1，其中 金额 = i-coins[j] 时，必须能被兑换，如果不能被兑换，就跳过这枚硬币。
     *
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange(int[] coins, int amount) {
        int max = Integer.MAX_VALUE; //表示这个金额无法正确兑换零钱
        int[] dp = new int[amount + 1];
        dp[0] = 0; //金额为0，不需要兑换
        for (int i = 1; i <= amount; i++) {
            dp[i] = max; //默认金额不能兑换
            for (int j = 0; j < coins.length; j++) {
                // 金额必须大于等于币值，才能被这个硬币兑换。  i - coins[j] >= 0
                // 并且 dp[i - coins[j]] 如果等于 max，表示这个金额不能被兑换为零钱，那么久必须跳过这个硬币，尝试下一枚硬币。
                // 如果遍历了所有硬币，发现都不能兑换零钱，那么 dp[i] = max，表示这个金额无法兑换。
                if (i - coins[j] >= 0 && dp[i - coins[j]] < max) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1); //因为使用了一枚硬币，所以要 + 1
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

}