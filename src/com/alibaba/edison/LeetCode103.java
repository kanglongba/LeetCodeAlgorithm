package com.alibaba.edison;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 二叉树的锯齿形层序遍历，medium
 * 给你二叉树的根节点 root ，返回其节点值的 锯齿形层序遍历 。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 * <p>
 * 跟102很像。
 * <p>
 * author: qonyqian
 * created on: 2022/2/9 5:15 下午
 * version：1.0
 * description:
 */
public class LeetCode103 {

    /**
     * 用两个辅助队列
     *
     * @param root
     * @return
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Deque<TreeNode> one = new ArrayDeque<>(); //负责从左向右遍历
        Deque<TreeNode> two = new ArrayDeque<>(); //负责从右向左遍历
        one.offer(root);
        List<Integer> temp = new ArrayList<>();
        while (!one.isEmpty() || !two.isEmpty()) {
            while (!one.isEmpty()) {
                TreeNode node = one.poll(); //从左向右遍历，没什么可说的
                temp.add(node.val);
                if (node.left != null) {
                    two.offer(node.left);
                }
                if (node.right != null) {
                    two.offer(node.right);
                }
            }
            if (!temp.isEmpty()) {
                result.add(temp);
                temp = new ArrayList<>();
            }
            while (!two.isEmpty()) {
                TreeNode node = two.pollLast(); //从右向左遍历，所以要取最后一个
                temp.add(node.val);
                if (node.right != null) { //因为two每次都取最后一个，为了使one可以从左向右遍历，我们倒叙插入子节点
                    one.offerFirst(node.right); //先插入右子节点，且使用头插法，保持倒叙插入
                }
                if (node.left != null) {
                    one.offerFirst(node.left); //再插入右子节点，且使用头插法，保持倒叙插入
                }
            }
            if (!temp.isEmpty()) {
                result.add(temp);
                temp = new ArrayList<>();
            }
        }
        return result;
    }
}
