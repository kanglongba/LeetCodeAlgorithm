package com.alibaba.edison;

/**
 * Created by 钧戈 on 2020/9/19.
 */
public class Sword68_ii {

    /**
     * 要先分析出规律，才能写出答案
     * 后续遍历+递归
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p , q);
        if (left == null) {
            return right;
        } else if (right == null) {
            return left;
        } else {
            return root;
        }
    }

    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }

    /**
     * 2020-11-15 17:57:08
     * @param root
     * @param p
     * @param q
     * @return
     */
    private TreeNode sanshua(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        if (root.val == p.val) {
            return p;
        }
        if (root.val == q.val) {
            return q;
        }

        TreeNode left = sanshua(root.left, p, q);
        TreeNode right = sanshua(root.right, p, q);
        if (left != null && right != null) {
            return root;
        } else if (left == null) {
            return right;
        } else {
            return left;
        }
    }
}
