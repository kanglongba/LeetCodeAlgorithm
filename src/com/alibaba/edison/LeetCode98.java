package com.alibaba.edison;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 验证二叉搜索树，medium
 * 给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
 * <p>
 * 有效 二叉搜索树定义如下：
 * 1.节点的左子树只包含 小于 当前节点的数。
 * 2.节点的右子树只包含 大于 当前节点的数。
 * 3.所有左子树和右子树自身必须也是二叉搜索树。
 * <p>
 * author: qonyqian
 * created on: 2022/2/8 9:43 下午
 * version：1.0
 * description:
 */
public class LeetCode98 {

    /**
     * 1.可以递归检查
     * 2.二叉树的中序遍历，看结果是不是升序
     *
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        Deque<Integer> queue = new ArrayDeque<>();
        mid(root, queue);
        return isValidBST;
    }

    boolean isValidBST = true;

    /**
     * 中序遍历
     * @param root
     * @param queue
     */
    public void mid(TreeNode root, Deque<Integer> queue) {
        if (!isValidBST) { //以前发现不是二叉搜索树，提前返回
            return;
        }
        if (root == null) {
            return;
        }
        mid(root.left, queue); //先遍历左子树
        visit(root, queue); //再遍历根节点
        mid(root.right, queue); //最后遍历右子树
    }

    public void visit(TreeNode root, Deque<Integer> queue) {
        if (!queue.isEmpty()) {
            int value = queue.peekLast();
            if (value >= root.val) { //校验是不是升序
                isValidBST = false;
                return;
            }
        }
        queue.offer(root.val); //遍历根节点
    }

    /**
     * 我的递归解决不了这个case：[5,4,6,null,null,3,7]
     *
     * @param root
     * @return
     */
    public boolean recursion(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (root.left != null && root.left.val >= root.val) {
            return false;
        }
        if (root.right != null && root.right.val <= root.val) {
            return false;
        }
        return recursion(root.left) && recursion(root.right);
    }

    /**
     * 官方递归代码：https://leetcode-cn.com/problems/validate-binary-search-tree/solution/yan-zheng-er-cha-sou-suo-shu-by-leetcode-solution/
     *
     * 添加了一个上限和下限，并且递归更新上下限
     *
     * @param root
     * @return
     */
    public boolean recursion1(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    /**
     * 如果是二叉搜索树，开肯定会满足左子树小于根节点，右子树大于根节点。所以左子树的上限就是根节点，而且左子树继续探索它的左子树时，会
     * 把上限更新为自己的值；继续探索它的右子树时，会把它的下限更新为自己的值。右子树同理。
     * @param node
     * @param lower
     * @param upper
     * @return
     */
    public boolean isValidBST(TreeNode node, long lower, long upper) {
        if (node == null) {
            return true;
        }
        if (node.val <= lower || node.val >= upper) {
            return false;
        }
        return isValidBST(node.left, lower, node.val) && isValidBST(node.right, node.val, upper);
    }

}
