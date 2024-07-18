package com.alibaba.edison;

/**
 * https://leetcode.cn/problems/path-sum/description/
 * <p>
 * 给你二叉树的根节点 root 和一个表示目标和的整数 targetSum 。判断该树中是否存在 根节点到叶子节点 的路径，这条路径上所有节点值相加等于
 * 目标和 targetSum 。如果存在，返回 true ；否则，返回 false 。
 * <p>
 * 叶子节点 是指没有子节点的节点。
 * <p>
 * easy
 * <p>
 * Applovin 3面，要求非递归算法
 * author: qonyqian
 * created on: 2022/8/19 23:02
 * version：1.0
 * description:
 */
public class LeetCode112 {

    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        return deepSearch(root, targetSum, 0);
    }

    public boolean deepSearch(TreeNode node, int targetSum, int currentSum) {
        if (node == null) {
            return false;
        }
        if (currentSum + node.val == targetSum) {
            return true;
        }
        if (currentSum + node.val > targetSum) {
            return false;
        }
        if (currentSum + node.val < targetSum) {
            return deepSearch(node.left, targetSum, currentSum + node.val) || deepSearch(node.right, targetSum, currentSum + node.val);
        }
        return false;
    }
}
