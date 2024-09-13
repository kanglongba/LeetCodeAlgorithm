package com.alibaba.edison;

/**
 * 检查骑士巡视方案
 * <p>
 * 骑士在一张 n x n 的棋盘上巡视。在 有效 的巡视方案中，骑士会从棋盘的 左上角 出发，并且访问棋盘上的每个格子 恰好一次 。
 * <p>
 * 给你一个 n x n 的整数矩阵 grid ，由范围 [0, n * n - 1] 内的不同整数组成，其中 grid[row][col] 表示单元格 (row, col) 是骑士访问的第 grid[row][col] 个单元格。骑士的行动是从下标 0 开始的。
 * <p>
 * 如果 grid 表示了骑士的有效巡视方案，返回 true；否则返回 false。
 * <p>
 * 注意，骑士行动时可以垂直移动两个格子且水平移动一个格子，或水平移动两个格子且垂直移动一个格子。下图展示了骑士从某个格子出发可能的八种行动路线。
 * <p>
 * 输入：grid = [[0,11,16,5,20],[17,4,19,10,15],[12,1,8,21,6],[3,18,23,14,9],[24,13,2,7,22]]
 * 输出：true
 * 解释：grid 如上图所示，可以证明这是一个有效的巡视方案。
 * <p>
 * 输入：grid = [[0,3,6],[5,8,1],[2,7,4]]
 * 输出：false
 * 解释：grid 如上图所示，考虑到骑士第 7 次行动后的位置，第 8 次行动是无效的。
 * <p>
 * author: qonyqian
 * created on: 2024/9/13 20:58
 * version：1.0
 * description:
 */
public class LeetCode2596 {

    static final int[][] steps = {
            {-2, 1},
            {-1, 2},
            {1, 2},
            {2, 1},
            {2, -1},
            {1, -2},
            {-1, -2},
            {-2, -1},
    };

    public static boolean checkValidGrid(int[][] grid) {
        int row = grid.length;
        int column = grid[0].length;
        int step = 0;
        boolean result = dfs(grid, step, 0, 0, row, column);
        return result;
    }

    public static boolean dfs(int[][] grid, int step, int startX, int startY, int row, int column) {
        if (startX < 0 || startX >= row || startY < 0 || startY >= column) {
            return false;
        }
        if (grid[startX][startY] != step) {
            return false;
        }
        if (step == row * column - 1) {
            return true;
        }
        boolean result = false;
        step++;
        for (int i = 0; i < 8; i++) {
            int newX = startX + steps[i][0];
            int newY = startY + steps[i][1];
            result = dfs(grid, step, newX, newY, row, column);
            if (result) {
                break;
            }
        }
        step--;
        return result;
    }


    public static void main(String[] args) {
        int[][] chess = {{0, 11, 16, 5, 20}, {17, 4, 19, 10, 15}, {12, 1, 8, 21, 6}, {3, 18, 23, 14, 9}, {24, 13, 2, 7, 22}};
        int[][] chess2 = {{0, 3, 6}, {5, 8, 1}, {2, 7, 4}};
        System.out.println(checkValidGrid(chess));
        System.out.println(checkValidGrid(chess2));
    }
}
