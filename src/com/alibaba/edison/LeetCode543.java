package com.alibaba.edison;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树的直径，easy
 * 给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。
 *
 * author: qonyqian
 * created on: 2022/2/3 12:14 上午
 * version：1.0
 * description:
 */
public class LeetCode543 {

    /**
     * 凭直觉就应该是递归。
     * 经过节点的直径 = 左子树的深度 + 1 + 右子树的深度。那么遍历这棵树，比较每个节点的直径，取最大值就可以了。
     *
     * @param root
     * @return
     */
    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int direct = 0;
        //bfs遍历树
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            direct = Math.max(direct, depth(node.left) + 1 + depth(node.right));
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        return direct-1;
    }

    /**
     * 一个节点的深度计算过后，可以直接缓存起来，以后再用的时候，就不用重新递归了。这里有点像dp思想。
     * @param root
     * @return
     */
    public int depth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(depth(root.left), depth(root.right));
    }
}
