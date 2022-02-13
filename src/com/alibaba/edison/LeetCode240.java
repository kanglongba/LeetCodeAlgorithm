package com.alibaba.edison;

/**
 * 搜索二维矩阵 II，medium
 * 编写一个高效的算法来搜索 mxn 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：
 * 1.每行的元素从左到右升序排列。
 * 2.每列的元素从上到下升序排列。
 * <p>
 * 未能想到最优解
 * <p>
 * author: qonyqian
 * created on: 2022/2/11 11:05 下午
 * version：1.0
 * description:
 */
public class LeetCode240 {

    /**
     * 很明显，就是利用矩阵的特点，那么特点是什么？
     * 1.matrix[0][0] = 最小值
     * 2.matrix[m-1][n-1] = 最大值
     * 但是如果利用从左上到右下这条对角线，需要保证矩阵是正方形，对角线才能正好贯穿矩阵。所以还要想别的思路。
     * <p>
     * 假设坐标(i,j)，我们在以(i,j)为右上角的矩阵中搜索target。
     * 如果matrix[i][j] > target，则 j--；如果matrix[i][j] < target，则 i++。
     * 记住这个结论就行。
     * <p>
     * 除此之外：
     * 1.暴力法，挨个查找
     * 2.优化暴力法，在每一行中使用二分查找。
     *
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        int i = 0, j = n - 1; //注意这里，从右上角点开始。这样初始的寻找范围能覆盖整个矩阵。然后逐步缩小查找范围。
        while (i >= 0 && j >= 0 && i < m && j < n) {
            if (matrix[i][j] == target) {
                return true;
            } else if (matrix[i][j] < target) {
                i++;
            } else {
                j--;
            }
        }
        return false;
    }
}
