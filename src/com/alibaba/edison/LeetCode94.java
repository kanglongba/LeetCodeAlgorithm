package com.alibaba.edison;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉树的中序遍历，easy
 * author: qonyqian
 * created on: 2022/1/30 6:21 下午
 * version：1.0
 * description:
 */
public class LeetCode94 {

    /**
     * 典型的递归
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        recursion(root, list);
        return list;
    }

    /**
     * 递归函数，最重要的就是退出条件
     * @param root
     * @param list
     */
    private void recursion(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        if (root.left != null) {
            recursion(root.left, list);
        }
        list.add(root.val);
        if (root.right != null) {
            recursion(root.right, list);
        }
    }

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
}
