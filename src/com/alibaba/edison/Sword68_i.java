package com.alibaba.edison;

/**
 * Created by 钧戈 on 2020/9/22.
 */
public class Sword68_i {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return digui(root, p, q);
    }

    public TreeNode bfs(TreeNode root, TreeNode p, TreeNode q) {
        while (root != null) {
            if (root.val < p.val && root.val < q.val) // p,q 都在 root 的右子树中
            {
                root = root.right; // 遍历至右子节点
            } else if (root.val > p.val && root.val > q.val) // p,q 都在 root 的左子树中
            {
                root = root.left; // 遍历至左子节点
            } else { break; }
        }
        return root;
    }

    public TreeNode digui(TreeNode root, TreeNode p, TreeNode q) {
        if (root.val < p.val && root.val < q.val) { return digui(root.right, p, q); }
        if (root.val > p.val && root.val > q.val) { return digui(root.left, p, q); }
        return root;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) { val = x; }
    }
}
