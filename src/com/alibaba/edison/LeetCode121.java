package com.alibaba.edison;

/**
 * 买卖股票的最佳时机，easy
 * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
 * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
 * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
 * <p>
 * dp入门题
 * 对于力扣平台上的股票类型的题目:
 * 121. 买卖股票的最佳时机
 * 122. 买卖股票的最佳时机 II
 * 123. 买卖股票的最佳时机 III
 * 188. 买卖股票的最佳时机 IV
 * 309. 最佳买卖股票时机含冷冻期
 * 714. 买卖股票的最佳时机含手续费
 * 剑指 Offer 63. 股票的最大利润
 *
 * <p>
 * author: qonyqian
 * created on: 2022/1/31 1:25 下午
 * version：1.0
 * description:
 */
public class LeetCode121 {

    /**
     * dp[i]表示第i天卖出获得的最大利润。
     * 这个定义里面隐含了一个意义：在第 i 天卖出获得的最大利润，那么必然是买在了 [0, i-1] 天内的最低点。
     * 在这层含义之下，就可以得到状态转移方程：dp[i] = dp[i-1] + (prices[i]-prices[i-1])
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        if (prices.length == 1) {
            return 0;
        }
        if (prices.length == 2) {
            return Math.max(prices[1] - prices[0], 0);
        }
        int[] dp = new int[prices.length];
        dp[0] = 0;
        dp[1] = Math.max(prices[1] - prices[0], 0);
        int max = dp[1];
        for (int i = 2; i < prices.length; i++) {
            dp[i] = Math.max(0, dp[i - 1] + prices[i] - prices[i - 1]);
            max = Math.max(dp[i], max);
        }
        return max;
    }
}
