package com.alibaba.edison;

/**
 * 岛屿数量，medium
 * 给你一个由'1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 * 此外，你可以假设该网格的四条边均被水包围。
 * <p>
 * 经典的题目
 * <p>
 * author: qonyqian
 * created on: 2022/2/11 2:44 下午
 * version：1.0
 * description:
 */
public class LeetCode200 {

    /**
     * 探索所有可能，运用回溯法的思想，但是不回溯
     *
     * 有点像图像染色，把连在一起的陆地全部染色，然后继续寻找没有染过色的陆地，继续染色，最后染了几回色，就有几块陆地。
     *
     * @param grid
     * @return
     */
    public int numIslands(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int count = 0;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '0') {
                    continue;
                } else if (visited[i][j]) {
                    continue;
                } else {
                    recursion(visited, grid, m, n, i, j);
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * 使用递归法，探索所有的岛屿，把连在的岛屿，全部染色。
     * 只能向上、向下、向左、向右探索
     *
     * @param visited
     * @param grid
     * @param m
     * @param n
     * @param i
     * @param j
     */
    public void recursion(boolean[][] visited, char[][] grid, int m, int n, int i, int j) {
        if (i < 0 || i >= m || j < 0 || j >= n) {
            return;
        }
        if (grid[i][j] == '0') {
            return;
        }
        if (visited[i][j]) {
            return;
        }
        visited[i][j] = true;
        recursion(visited, grid, m, n, i - 1, j);
        recursion(visited, grid, m, n, i, j + 1);
        recursion(visited, grid, m, n, i + 1, j);
        recursion(visited, grid, m, n, i, j - 1);
    }
}
