package com.alibaba.edison;

/**
 * 最小路径和，medium
 * 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 * 说明：每次只能向下或者向右移动一步。
 *
 * 输入：grid = [[1,3,1],[1,5,1],[4,2,1]]
 * 输出：7
 * 解释：因为路径 1→3→1→1→1 的总和最小。
 *
 * 与LeetCode62，同样的思路
 * <p>
 * author: qonyqian
 * created on: 2022/2/8 2:32 下午
 * version：1.0
 * description:
 */
public class LeetCode64 {

    /**
     * 凭直觉是动态规划
     * <p>
     * dp[i][j] 表示 到达(i,j)的路径和
     * 状态转移方程：dp[i][j] = min(dp[i-1][j], dp[i][j-1]) + grid[i][j] ，只能向下和向右移动
     *
     * @param grid
     * @return
     */
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) { //因为只能向下和向右移动，所以可以使用循环，因为循环也是按照从做到右，从上到下的顺序。使前面的计算结果正好可以给后面复用
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) { // 初始值
                    dp[0][0] = grid[0][0];
                } else if (i == 0) { //上边
                    dp[i][j] = dp[i][j - 1] + grid[i][j];
                } else if (j == 0) { //左边
                    dp[i][j] = dp[i - 1][j] + grid[i][j];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
                }
            }
        }
        return dp[m - 1][n - 1];
    }
}
