package com.alibaba.edison;

/**
 * 二叉树节点
 * author: qonyqian
 * created on: 2022/1/30 10:59 下午
 * version：1.0
 * description:
 */
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
