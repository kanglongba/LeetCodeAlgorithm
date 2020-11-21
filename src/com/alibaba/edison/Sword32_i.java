package com.alibaba.edison;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by 钧戈 on 2020/10/9.
 */
public class Sword32_i {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) { val = x; }
    }

    public int[] levelOrder(TreeNode root) {
        return bfs(root);
    }

    /**
     * 典型的广度优先遍历。
     * @param root
     * @return
     */
    private int[] bfs(TreeNode root) {
        if (root == null) {
            return new int[0];
        }
        List<TreeNode> list = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        ((LinkedList<TreeNode>)queue).offerLast(root);
        while (!queue.isEmpty()) {
            TreeNode node = ((LinkedList<TreeNode>)queue).pollFirst();
            list.add(node);
            if (node.left != null) {
                ((LinkedList<TreeNode>)queue).offerLast(node.left);
            }
            if (node.right != null) {
                ((LinkedList<TreeNode>)queue).offerLast(node.right);
            }
        }
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i).val;
        }
        return res;
    }

    /**
     * 二刷 2020-11-01 21:21:17
     * bfs
     * @param root
     * @return
     */
    public int[] ershua(TreeNode root) {
        if (root == null) {
            return new int[0];
        }
        ArrayList<Integer> list = new ArrayList();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            list.add(node.val);
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }
}
