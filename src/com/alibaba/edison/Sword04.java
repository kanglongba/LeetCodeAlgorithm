package com.alibaba.edison;

/**
 * Created by 钧戈 on 2020/10/4.
 */
public class Sword04 {

    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        return leetCode(matrix, target);
    }

    /**
     * 官方答案：从右上角开始找，大于->减一列，小于->加一行
     *
     * @param matrix
     * @param target
     * @return
     */
    private boolean leetCode(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int rows = matrix.length-1, cols = matrix[0].length-1, startX = 0, startY = cols;
        while(startX <= rows && startY >= 0) {
            if (matrix[startX][startY] == target) {
                return true;
            } else if (matrix[startX][startY] < target) {
                startX++;
            } else {
                startY--;
            }
        }
        return false;
    }

    /**
     * 根据矩阵的特性，使用二分法
     * 我这个二分法的思路是以对角线（从左上角到右下角）分，然后排出掉部分元素，减少搜索的范围。但是由于依赖对角线，所
     * 以貌似只适合正方形矩阵，遇到长方形就不灵了。
     * @param matrix
     * @param target
     * @return
     */
    private boolean mid(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return false;
        }
        int rows = matrix.length;
        int cols = matrix[0].length;
        int rs = 0, re = rows-1, cs = 0, ce = cols - 1;
        while(rs<re && cs< ce){
            int mr = (rs+re)/2;
            int mc = (cs+ce)/2;
            if (matrix[mr][mc] == target) {
                return true;
            } else if(matrix[mr][mc] > target) {
                re = mr-1;
                ce = mc-1;
            } else {
                rs = mr+1;
                cs = mc+1;
            }
        }
        for (int i = 0; i <= ce; i++) {
            if (matrix[re][i] == target) {
                return true;
            }
        }
        for (int j = 0; j <= re; j++) {
            if (matrix[j][ce] == target) {
                return true;
            }
        }
        return false;
    }

    private boolean force(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0){
            return false;
        }
        int rows = matrix.length;
        int cols = matrix[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == target) {
                    return true;
                }
            }
        }
        return false;
    }

}
