package com.alibaba.edison;

/**
 * 单词搜索，medium
 * 给定一个m x n 二维字符网格 board 和一个字符串单词word 。如果word 存在于网格中，返回 true ；否则，返回 false 。
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 * <p>
 * <p>
 * author: qonyqian
 * created on: 2022/2/8 5:11 下午
 * version：1.0
 * description:
 */
public class LeetCode79 {

    /**
     * 这题好难呀，乍一看，完全没有思路
     * <p>
     * 不过这种探索类型的题目，没想法时，用回溯法准没错
     *
     * @param board
     * @param word
     * @return
     */
    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) { //以每个点为起点，依次探索
                recursion(visited, m, n, i, j, board, word, 0); //递归探索
                if (find) { //如果找到了，提前返回。
                    return true;
                }
            }
        }
        return false;
    }

    boolean find = false;

    /**
     * 递归探索单次
     *
     * @param visited
     * @param m       矩阵的行
     * @param n       矩阵的列
     * @param x       将要探索元素的坐标
     * @param y       将要探索元素的坐标
     * @param board
     * @param word
     * @param index   探索 word 的第几个字符
     */
    public void recursion(boolean[][] visited, int m, int n, int x, int y, char[][] board, String word, int index) {
        if (find) { //如果已经找到了，提前返回
            return;
        }
        if (index == word.length()) { //word 已经探索完，成功找到
            find = true;
            return;
        }
        if (x < 0 || x > m - 1 || y < 0 || y > n - 1) { //元素坐标非法
            return;
        }
        if (visited[x][y]) { //这个点已经探索过了，不能重复探索
            return;
        }
        if (board[x][y] != word.charAt(index)) { //元素与 word 的字符不等
            return;
        }
        visited[x][y] = true; //更新元素已经探索过
        recursion(visited, m, n, x - 1, y, board, word, index + 1); //继续探索它的上
        recursion(visited, m, n, x, y + 1, board, word, index + 1); //右
        recursion(visited, m, n, x + 1, y, board, word, index + 1); //下
        recursion(visited, m, n, x, y - 1, board, word, index + 1); //左
        visited[x][y] = false; //回溯，恢复元素为未探索状态
    }
}
