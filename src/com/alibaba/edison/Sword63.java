package com.alibaba.edison;

/**
 * Created by 钧戈 on 2020/10/16.
 */
public class Sword63 {

    /**
     * 本题明确限制交易次数为1次 双指针
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 1) {
            return 0;
        }
        int min = 0, max = 0;
        int count = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[max]) {
                if (prices[i] - prices[min] > count) {
                    count = prices[i] - prices[min];
                    max = i;
                }
            } else if (prices[i] < prices[min]) {
                min = i;
                max = i;
            }
        }
        return count;
    }

    /**
     * 如果不限制交易次数 贪心算法.
     *
     * @param prices
     * @return
     */
    public int maxProfit2(int[] prices) {
        if (prices == null) {
            return 0;
        }
        int count = 0;
        for (int i = 1; i < prices.length; i++) {
            int change = prices[i] - prices[i - 1];
            if (change > 0) {
                count += change;
            }
        }
        return count;
    }

    /**
     * 动态规划
     * @param prices
     * @return
     */
    public int maxProfit3(int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }
        int[] dp = new int[prices.length];
        dp[0] = 0;
        int min = prices[0];
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[min]) {
                dp[i] = Math.max(dp[i-1], prices[i] - prices[min]);
            } else {
                dp[i] = dp[i-1];
                min = i;
            }
        }
        return dp[prices.length-1];
    }
}
