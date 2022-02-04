package com.alibaba.edison;

/**
 * 相同的树，easy
 * 给你两棵二叉树的根节点 p 和 q ，编写一个函数来检验这两棵树是否相同。
 * <p>
 * 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
 * <p>
 * author: qonyqian
 * created on: 2022/2/3 4:00 下午
 * version：1.0
 * description:
 */
public class LeetCode100 {

    /**
     * 典型的递归
     * @param p
     * @param q
     * @return
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        return (p.val == q.val) && (isSameTree(p.left, q.left) && isSameTree(p.right, q.right));
    }
}
