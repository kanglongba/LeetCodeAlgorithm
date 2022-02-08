package com.alibaba.edison;

/**
 * 不同路径，medium
 * 一个机器人位于一个 m x n网格的左上角 （起始点在下图中标记为 “Start” ）。
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
 * 问总共有多少条不同的路径？
 * <p>
 * author: qonyqian
 * created on: 2022/2/8 1:36 下午
 * version：1.0
 * description:
 */
public class LeetCode62 {

    /**
     * 经典的动态规划
     * <p>
     * 由于机器人只能向右走和向下走，所以要想到达(m,n)，必须先到达(m,n-1)或(m-1,n)
     * dp[i][j]，表示到达(i,j)的总路径
     * 状态转移方程：dp[i][j] = dp[i][j-1] + dp[i-1][j]
     * 边界条件：在左边和上边，只有一种走法
     *
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths(int m, int n) {
        if (m == 1 || n == 1) {
            return 1;
        }
        int[][] dp = new int[m][n];
        dp[0][0] = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0) {
                    dp[i][j] = 1;
                } else if (j == 0) {
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
                }
            }
        }
        return dp[m - 1][n - 1];
    }
}
