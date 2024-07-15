package com.alibaba.edison;

/**
 * 二叉树最长连续序列
 * 给你一棵指定的二叉树，请你计算它最长连续序列路径的长度。最长连续序列路径 是依次递增 1 的路径 或者 依次递减 1 的路径。
 * 该路径，可以是从某个初始结点到树中任意结点，通过「父 - 子」或者「子 - 父」关系连接而产生的任意路径。
 * 这个最长连续的路径，可以是父结点到子结点，也可以是子结点到父结点。
 * <p>
 * 示例 1：
 * 输入: root
 *      4
 *     / \
 *    3   5
 *  / \
 * 1   2
 * 最长序列是[2,3,4,5] or [5,4,3,2]
 * 输出: 4
 * 示例 2：
 * 输入: root
 *           5
 *          / \
 *         2   6
 *       / \    \
 *      1   3    7
 *         / \
 *        4   5
 * 最长序列是[1,2,3,4] or [4,3,2,1]
 * 输出: 4
 */
public class LongestConsecutive2 {

    /**
     * @param root: the root of binary tree
     * @return: the length of the longest consecutive sequence path
     */
    int maxval = 0;

    public int longestConsecutive2(TreeNode root) {
        helper(root);
        return maxval;
    }

    private int[] helper(TreeNode root) {
        if (root == null) {
            return new int[]{0, 0};
        }

        int incr = 1, decr = 1;
        if (root.left != null) {
            int[] left = helper(root.left);
            if (root.val == root.left.val + 1) {
                decr = left[1] + 1;
            } else if (root.val == root.left.val - 1) {
                incr = left[0] + 1;
            }
        }
        if (root.right != null) {
            int[] right = helper(root.right);
            if (root.val == root.right.val + 1) {
                decr = Math.max(decr, right[1] + 1);
            } else if (root.val == root.right.val - 1) {
                incr = Math.max(incr, right[0] + 1);
            }
        }

        maxval = Math.max(maxval, incr + decr - 1);

        return new int[]{incr, decr};
    }
}
