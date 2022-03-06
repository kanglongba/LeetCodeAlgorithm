package com.alibaba.edison;

/**
 * 把二叉搜索树转换为累加树，medium
 * 给出二叉 搜索 树的根节点，该树的节点值各不相同，请你将其转换为累加树（Greater Sum Tree），使每个节点 node 的新值等于原树中大于或等
 * 于node.val的值之和。
 *
 * 提醒一下，二叉搜索树满足下列约束条件：
 * 1.节点的左子树仅包含键 小于 节点键的节点。
 * 2.节点的右子树仅包含键 大于 节点键的节点。
 * 3.左右子树也必须是二叉搜索树。
 *
 * author: qonyqian
 * created on: 2022/2/14 11:42 下午
 * version：1.0
 * description:
 */
public class LeetCode538 {

    /**
     * 注意观察累加数的特点，每一个点都等于位于它右边的所有点之和，再加上他自己的值。
     * 所以，本题可以转化为 二叉树中序遍历的变种，即按 右子树->根节点->左子树 的顺序遍历树，依次累加值即可。
     *
     * 很明显就是递归了。
     *
     * 本题的关键，就是要先分析出规律，分析出规律以后，算法自然就出来了。没有什么银弹。
     *
     * @param root
     * @return
     */
    public TreeNode convertBST(TreeNode root) {
        if (root == null) { //别管有用没用，写上准没错
            return root;
        }
        dfs(root);
        return root;
    }

    public void dfs(TreeNode root) {
        if (root == null) { //直接返回，不解释
            return;
        }

        dfs(root.right); //先遍历右子树

        root.val += sum; //在遍历根节点
        sum = root.val; //更新sum

        dfs(root.left); //在遍历左子树
    }

    int sum = 0;
}
