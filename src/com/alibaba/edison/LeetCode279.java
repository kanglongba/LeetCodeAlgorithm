package com.alibaba.edison;

/**
 * 完全平方数，medium
 * 给你一个整数 n ，返回 和为 n 的完全平方数的最少数量 。
 * 完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不
 * 是。
 *
 * 未能得出最优解
 *
 * author: qonyqian
 * created on: 2022/2/13 2:03 下午
 * version：1.0
 * description:
 */
public class LeetCode279 {

    /**
     * 竟然是动态规划：https://leetcode-cn.com/problems/perfect-squares/solution/wan-quan-ping-fang-shu-by-leetcode-solut-t99c/
     * https://leetcode-cn.com/problems/perfect-squares/solution/hua-jie-suan-fa-279-wan-quan-ping-fang-shu-by-guan/
     * dp[i]表示 和为 i 的完全平方数的最少数量，最差情况就是完全平方数都是 1，即dp[i]=i，如4 = 1 + 1 + 1 + 1。
     * 状态转移方程：
     * 当前的完全平方和 n = i
     * 如果使用j，此时 n = i - j * j，用了一个数所以个数就是dp[i - j*j] + 1;
     * 如果不使用j，此时dp[i]未发生变化，即还是dp[i]
     * 最终dp[i]为 第一步，第二步的最小值，即：dp[i] = min(dp[i], dp[i - j*j] + 1)
     *
     * @param n
     * @return
     */
    public int numSquares(int n) {
        int[] dp = new int[n+1]; //n >= 1
        for (int i = 1; i <= n; i++) {
            dp[i] = i; // 最坏的情况就是每个完全平方数都是1
            for (int j = 1; i - j * j >= 0; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1); // 动态转移方程
            }
        }
        return dp[n];
    }
}
