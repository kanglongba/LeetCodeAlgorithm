package com.alibaba.edison;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 钧戈 on 2020/10/7.
 */
public class Sword07 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) { val = x; }
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTree1(preorder, inorder);
    }

    HashMap<Integer, Integer> dic = new HashMap<>();
    int[] po;

    private TreeNode buildTree1(int[] preorder, int[] inorder) {
        po = preorder;
        for (int i = 0; i < inorder.length; i++) { dic.put(inorder[i], i); }
        return recur(0, 0, inorder.length - 1);
    }

    private TreeNode recur(int pre_root, int in_left, int in_right) {
        if (in_left > in_right) { return null; }
        TreeNode root = new TreeNode(po[pre_root]);
        int i = dic.get(po[pre_root]);
        root.left = recur(pre_root + 1, in_left, i - 1);
        root.right = recur(pre_root + i - in_left + 1, i + 1, in_right);
        return root;
    }

    /**
     * 二刷 2020-11-01 20:53:22 LeetCode官方题解的答案更易懂 https://leetcode-cn
     * .com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/solution/cong-qian-xu-yu-zhong-xu-bian
     * -li-xu-lie-gou-zao-9/
     *
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree2(int[] preorder, int[] inorder) {
        int n = preorder.length;
        // 构造哈希映射，帮助我们快速定位根节点
        indexMap = new HashMap<Integer, Integer>();
        for (int i = 0; i < n; i++) {
            indexMap.put(inorder[i], i);
        }
        return myBuildTree(preorder, inorder, 0, n - 1, 0, n - 1);
    }

    private Map<Integer, Integer> indexMap;

    public TreeNode myBuildTree(int[] preorder, int[] inorder, int preorder_left, int preorder_right, int inorder_left,
                                int inorder_right) {
        if (preorder_left > preorder_right) {
            return null;
        }

        // 前序遍历中的第一个节点就是根节点
        int preorder_root = preorder_left;
        // 在中序遍历中定位根节点
        int inorder_root = indexMap.get(preorder[preorder_root]);

        // 先把根节点建立出来
        TreeNode root = new TreeNode(preorder[preorder_root]);
        // 得到左子树中的节点数目
        int size_left_subtree = inorder_root - inorder_left;
        // 递归地构造左子树，并连接到根节点
        // 先序遍历中「从 左边界+1 开始的 size_left_subtree」个元素就对应了中序遍历中「从 左边界 开始到 根节点定位-1」的元素
        root.left = myBuildTree(preorder, inorder, preorder_left + 1, preorder_left + size_left_subtree, inorder_left,
            inorder_root - 1);
        // 递归地构造右子树，并连接到根节点
        // 先序遍历中「从 左边界+1+左子树节点数目 开始到 右边界」的元素就对应了中序遍历中「从 根节点定位+1 到 右边界」的元素
        root.right = myBuildTree(preorder, inorder, preorder_left + size_left_subtree + 1, preorder_right,
            inorder_root + 1, inorder_right);
        return root;
    }
}
