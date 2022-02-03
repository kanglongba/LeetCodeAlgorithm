package com.alibaba.edison;

/**
 * 翻转二叉树，easy
 * 给你一棵二叉树的根节点 root ，翻转这棵二叉树，并返回其根节点。
 * author: qonyqian
 * created on: 2022/2/2 1:17 下午
 * version：1.0
 * description:
 */
public class LeetCode226 {

    /**
     * 递归
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        switchTree(root);
        return root;
    }

    public void switchTree(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode left = root.left;
        TreeNode right = root.right;
        root.left = right;
        root.right = left;
        switchTree(root.left);
        switchTree(root.right);
    }
}
