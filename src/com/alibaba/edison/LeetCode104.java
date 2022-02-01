package com.alibaba.edison;

/**
 * 二叉树的最大深度，easy
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 *
 * author: qonyqian
 * created on: 2022/1/30 10:58 下午
 * version：1.0
 * description:
 */
public class LeetCode104 {

    /**
     * 典型的dfs
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(recursion(root.left, 1), recursion(root.right, 1));
    }

    /**
     * 典型的递归问题
     * @param root
     * @param depth
     * @return
     */
    public int recursion(TreeNode root, int depth) {
        if (root == null) {
            return depth;
        }
        return Math.max(recursion(root.left, 1+depth), recursion(root.right, 1+depth));
    }
}
