package com.alibaba.edison;

/**
 * Created by 钧戈 on 2020/10/31.
 */
public class Sword55_ii {

    public boolean isBalanced(TreeNode root) {
        return digui2(root);
    }

    /**
     * 2020-10-31 15:33:09
     * 二刷
     * 自顶向下
     * @param root
     * @return
     */
    private boolean digui2(TreeNode root) {
        if (root == null) {
            return true;
        } else {
            return (Math.abs(deep(root.left) - deep(root.right)) <= 1) && digui2(root.left) && digui2(root.right);
        }
    }

    private int deep(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(deep(root.left), deep(root.right));
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) { val = x; }
    }
}
