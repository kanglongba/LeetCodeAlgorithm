package com.alibaba.edison;

import java.util.*;

/**
 * Created by 钧戈 on 2020/9/19.
 */
public class Sword54 {

    public int kthLargest(TreeNode root, int k) {
        return tree(root, k);
    }

    int k,res;

    /**
     * 二叉树遍历
     * @param root
     * @param k
     * @return
     */
    private int tree(TreeNode root, int k) {
        this.k = k;
        dfs(root);
        return res;
    }

    private void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        dfs(root.right);
        if (k == 0) {
            return;
        }
        if (--k == 0) {
            res = root.val;
            return;
        }
        dfs(root.left);
    }

    /**
     * 小根堆+bfs
     * @param root
     * @param k
     * @return
     */
    private int heap(TreeNode root, int k) {
        PriorityQueue<TreeNode> heap = new PriorityQueue<>(k, (t1, t2) -> t1.val-t2.val);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (k > 0) {
                heap.offer(node);
                k--;
            } else {
                if (node.val > heap.peek().val) {
                    heap.poll();
                    heap.offer(node);
                }
            }
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        return heap.peek().val;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) { val = x; }
    }
}
