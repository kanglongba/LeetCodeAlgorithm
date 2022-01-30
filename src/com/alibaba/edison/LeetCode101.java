package com.alibaba.edison;

/**
 * 未能第一时间解答
 * 对称二叉树，easy
 * 给你一个二叉树的根节点 root ， 检查它是否轴对称。
 *
 * author: qonyqian
 * created on: 2022/1/30 6:35 下午
 * version：1.0
 * description:
 */
public class LeetCode101 {

    /**
     * 递归
     * 1.如果一个二叉树是对称的，那么他的左子树和右子树互为镜像
     * 2.如果两棵树互为镜像，那么A的左子树和B的右子树、A的右子树和B的左子树互为镜像
     * 3.结论递归成立
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        return check(root.left, root.right);
    }

    public boolean check(TreeNode left, TreeNode right) {
        //同时为空
        if (left == null && right == null) {
            return true;
        }
        //其中一个为空
        if (left == null || right == null) {
            return false;
        }
        //同时不为空
        if (left.val != right.val) {
            return false;
        }
        return check(left.left, right.right) && check(left.right, right.left);
    }

    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }
}
