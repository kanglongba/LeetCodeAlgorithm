package com.alibaba.edison;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 钧戈 on 2020/9/14.
 */
public class Sword55_i {

    public int maxDepth(TreeNode root) {
        //1.深度优先遍历
        //2.广度优先遍历
        return 1;
    }

    /**
     * 深度优先
     * @param root
     * @return
     */
    private int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            return 1 + Math.max(dfs(root.left), dfs(root.right));
        }
    }

    /**
     * 广度优先
     * @param root
     * @return
     */
    private int wide(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int res = 0;
        List<TreeNode> queue = new ArrayList<>();
        List<TreeNode> temp;
        queue.add(root);
        while (!queue.isEmpty()) {
            temp = new ArrayList<>();
            for (TreeNode node : queue) {
                if (node.left != null) {
                    temp.add(node.left);
                }
                if (node.right != null) {
                    temp.add(node.right);
                }
            }
            res++;
            queue = temp;
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
