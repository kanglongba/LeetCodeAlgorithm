package com.alibaba.edison;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 二叉树展开为链表，medium
 * 给你二叉树的根结点 root ，请你将它展开为一个单链表：
 * 1.展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
 * 2.展开后的单链表应该与二叉树 先序遍历 顺序相同。
 * <p>
 * <p>
 * author: qonyqian
 * created on: 2022/2/9 5:56 下午
 * version：1.0
 * description:
 */
public class LeetCode114 {

    /**
     * 借助辅助队列
     *
     * @param root
     */
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        Queue<TreeNode> queue = new ArrayDeque<>();
        recursion(root, queue);
        TreeNode pre = new TreeNode();
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            pre.right = node;
            pre.left = null;
            pre = pre.right;
        }
    }

    public void recursion(TreeNode root, Queue<TreeNode> queue) {
        if (root == null) {
            return;
        }
        queue.offer(root);
        recursion(root.left, queue);
        recursion(root.right, queue);
    }

}
