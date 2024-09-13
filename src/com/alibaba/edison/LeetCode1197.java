package com.alibaba.edison;

/**
 * 进击的骑士（马走日）
 * author: qonyqian
 * created on: 2024/9/13 19:47
 * version：1.0
 * description:
 */
public class LeetCode1197 {

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

    /**
     * 字节一面
     * 马走日，从起始点(startX, startY)，能否到达 (targetX,targetY)
     * <p>
     * 深度优先算法
     *
     * @param chess
     * @param targetX
     * @param targetY
     * @return
     */
    public static boolean visit(int[][] chess, int startX, int startY, int targetX, int targetY) {
        int row = chess.length;
        int column = chess[0].length;
        boolean[][] visited = new boolean[row][column];
        boolean result = bfs(chess, visited, startX, startY, targetX, targetY, row, column);
        return result;
    }

    public static boolean bfs(int[][] chess, boolean[][] visited, int startX, int startY, int targetX, int targetY, int row, int column) {
        if (startX < 0 || startX >= row || startY < 0 || startY >= column) {
            return false;
        }
        if (visited[startX][startY]) {
            return false;
        }
        if (startX == targetX && startY == targetY) {
            return true;
        }
        visited[startX][startY] = true;
        boolean result = false;
        for (int i = 0; i < 8; i++) {
            int newStartX = startX + steps[i][0];
            int newStartY = startY + steps[i][1];
            result = bfs(chess, visited, newStartX, newStartY, targetX, targetY, row, column);
            if (result) {
                break;
            }
        }
        visited[startX][startY] = false;
        return result;
    }

    public static void main(String[] args) {
        int[][] chess = new int[5][5];
        boolean result = visit(chess, 0, 0, 1, 1);
        System.out.println(result);
    }
}
