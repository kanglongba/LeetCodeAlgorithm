package com.alibaba.edison;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by 钧戈 on 2020/9/22.
 */
public class Sword32_ii {

    public List<List<Integer>> levelOrder(TreeNode root) {
        return bfs(root);
    }

    public List<List<Integer>> bfs(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> res = new ArrayList<>();
        if (root != null) { queue.add(root); }
        while (!queue.isEmpty()) {
            List<Integer> tmp = new ArrayList<>();
            for (int i = queue.size(); i > 0; i--) {
                TreeNode node = queue.poll();
                tmp.add(node.val);
                if (node.left != null) { queue.add(node.left); }
                if (node.right != null) { queue.add(node.right); }
            }
            res.add(tmp);
        }
        return res;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) { val = x; }
    }
}
