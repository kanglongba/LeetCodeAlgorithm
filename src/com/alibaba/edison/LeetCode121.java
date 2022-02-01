package com.alibaba.edison;

/**
 * 买卖股票的最佳时机，easy
 * dp入门题
 * author: qonyqian
 * created on: 2022/1/31 1:25 下午
 * version：1.0
 * description:
 */
public class LeetCode121 {

    /**
     * dp[i]表示第i天卖出获得的最大利润
     * 状态转移方程：dp[i] = dp[i-1] + (prices[i]-prices[i-1])
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        if (prices.length == 1) {
            return 0;
        }
        if (prices.length == 2) {
            return Math.max(prices[1]-prices[0], 0);
        }
        int[] dp = new int[prices.length];
        dp[0] = 0;
        dp[1] = Math.max(prices[1]-prices[0], 0);
        int max = dp[1];
        for (int i = 2; i < prices.length; i++) {
            dp[i] = Math.max(0, dp[i-1] + prices[i] - prices[i-1]);
            max = Math.max(dp[i], max);
        }
        return max;
    }
}
