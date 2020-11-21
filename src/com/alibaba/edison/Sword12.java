package com.alibaba.edison;

import java.util.Arrays;

/**
 * Created by 钧戈 on 2020/10/19.
 */
public class Sword12 {

    /**
     * https://leetcode-cn.com/problems/ju-zhen-zhong-de-lu-jing-lcof/
     *
     * @param board
     * @param word
     * @return
     */
    public boolean exist(char[][] board, String word) {
        return dfs(board, word);
    }

    /**
     * 一看到这题，就意思到是图的搜索的问题，应该用dfs(递归)或者bfs(队列)去做，但是就是不知道怎么写代码，惭愧。
     *
     * @param board
     * @param word
     * @return
     */
    private boolean dfs(char[][] board, String word) {
        char[] chars = word.toCharArray();
        int row = board.length;
        int col = board[0].length;
        boolean[][] visited = new boolean[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (chars[0] == board[i][j]) {
                    if (digui(board, i, j, visited, chars, row, col)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean digui(char[][] board, int x, int y, boolean[][] visited, char[] chars, int row, int col) {
        if (chars == null || chars.length == 0) {
            return true;
        } else if (x >= 0 && x < row && y >= 0 && y < col && chars[0] == board[x][y] && !visited[x][y]) {
            visited[x][y] = true;
            //这里也可以优化，创建多个对象会增加耗时，这里可以改成下标的方式
            char[] newChars = Arrays.copyOfRange(chars, 1, chars.length);
            boolean res =  digui(board, x - 1, y, visited, newChars, row, col)
                || digui(board, x + 1, y, visited, newChars, row, col)
                || digui(board, x, y - 1, visited, newChars, row, col)
                || digui(board, x, y + 1, visited, newChars, row, col);
            //很关键，重置状态位
            visited[x][y] = false;
            return res;
        }
        return false;
    }

    private boolean[][] copyVisited(boolean[][] visited) {
        boolean[][] v = new boolean[visited.length][];
        for (int i = 0; i < visited.length; i++) {
            v[i] = Arrays.copyOf(visited[i], visited[i].length);
        }
        return v;
    }

}
