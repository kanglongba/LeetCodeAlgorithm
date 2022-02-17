package com.alibaba.edison;

/**
 * 最大正方形，medium
 * 在一个由 '0' 和 '1' 组成的二维矩阵内，找到只包含 '1' 的最大正方形，并返回其面积。
 *
 * 输入：matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
 * 输出：4
 *
 * 经典的题目
 * <p>
 * author: qonyqian
 * created on: 2022/2/11 4:21 下午
 * version：1.0
 * description:
 */
public class LeetCode221 {

    /**
     * 暴力法，从左上向右下探索
     * 我这算法效率太低了：
     * 执行用时：1113 ms, 在所有 Java 提交中击败了 5.03% 的用户
     * 内存消耗：54.1 MB, 在所有 Java 提交中击败了 5.02% 的用户
     *
     * @param matrix
     * @return
     */
    public int maximalSquare(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int max = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                max = Math.max(max, visit(matrix, m, n, i, j));
            }
        }
        return max * max;
    }

    public int visit(char[][] matrix, int m, int n, int i, int j) {
        if (matrix[i][j] == '0') {
            return 0;
        }
        int range = 1; //(i,j)默认是正方形，那么从 边长+1 开始探索
        //只需检查右边和下边，如果右边和下边符合要求，range+1，继续向下探索
        while ((i + range < m) && (j + range < n)) { //边界检查
            for (int k = 0; k <= range; k++) {
                //探索右边
                if (matrix[i + k][j + range] != '1') {
                    return range;
                }
                //探索下边
                if (matrix[i + range][j + k] != '1') {
                    return range;
                }
            }
            range++;
        }
        return range; //range是在以(i,j)为起点向前探索的步长，但是循环检查失败，或者循环正常结束，range都会多加1，所以正确的边长是 = 1 + range -1 = range
    }

    /**
     * 使用动态规划
     * 这道题还是要看图，才能分析出状态转移方程的规律。
     *
     * dp[i][j]，表示以(i,j)为右下角的正方形的边长
     * <p>
     * 状态转移方程：dp[i][j] =
     * 1.如果matrix[i][j]=1, min(dp[i-1][j-1],dp[i][j-1],dp[i-1][j]) + 1
     * 2.如果matrix[i][j]=0, 0
     * <p>
     * 边界条件：
     * 1.上边的点，dp[i][j] = matrix[i][j]
     * 2.左边的点，dp[i][j] = matrix[i][j]
     *
     * @param matrix
     * @return
     */
    public int maximalSquareDp(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int max = 0;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = matrix[i][j] == '0' ? 0 : 1;
                } else if (matrix[i][j] == '0') {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i][j - 1], dp[i - 1][j])) + 1;
                }
                max = Math.max(max, dp[i][j]);
            }
        }
        return max * max;
    }
}
