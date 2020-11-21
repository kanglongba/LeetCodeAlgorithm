package com.alibaba.edison;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by 钧戈 on 2020/11/1.
 */
public class Sword32_iii {

    public List<List<Integer>> levelOrder(TreeNode root) {
        return ershua(root);
    }

    /**
     * 二刷 2020-11-01 22:42:51
     * 这个又是bfs，只是要分两种，一种用队列，一种用栈，或者用双端队列
     * @param root
     */
    private List<List<Integer>> ershua(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        Deque<TreeNode> deque = new LinkedList<>();
        int level = 1;
        deque.offerFirst(root);
        while (!deque.isEmpty()) {
            TreeNode node;
            int size = deque.size();
            List<Integer> res = new ArrayList<>();
            if (level % 2 == 1) {
                for (int i = 0; i < size; i++) {
                    node = deque.pollFirst();
                    res.add(node.val);
                    if (node.left != null) {
                        deque.offerLast(node.left);
                    }
                    if (node.right != null) {
                        deque.offerLast(node.right);
                    }
                }
            } else {
                for (int i = 0; i < size; i++) {
                    node = deque.pollLast();
                    res.add(node.val);
                    if (node.right != null) {
                        deque.offerFirst(node.right);
                    }
                    if (node.left != null) {
                        deque.offerFirst(node.left);
                    }
                }
            }
            level++;
            list.add(res);
        }
        return list;
    }

    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
}
