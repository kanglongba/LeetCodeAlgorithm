package com.alibaba.edison;

/**
 * Created by 钧戈 on 2020/10/9.
 */
public class Sword47 {
    public int maxValue(int[][] grid) {
        return dp(grid);
    }

    /**
     * 一看就知道是动态规划问题。
     * dp[m][n] = max(dp[m][n-1], dp[m-1][n]) + grid[m][n]
     * @param grid
     * @return
     */
    private int dp(int[][] grid) {
        if (grid == null) {
            return 0;
        }
        int m  = grid.length, n = grid[0].length;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (j == 0 && i == 0) {
                    dp[i][j] = grid[0][0];
                } else if (j == 0) {
                    dp[i][j] = dp[i-1][j] + grid[i][j];
                } else if (i == 0) {
                    dp[i][j] = dp[i][j-1] + grid[i][j];
                } else {
                    dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]) + grid[i][j];
                }
            }
        }
        return dp[m-1][n-1];
    }
}
