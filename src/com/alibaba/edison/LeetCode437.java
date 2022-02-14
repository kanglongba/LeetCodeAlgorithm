package com.alibaba.edison;

/**
 * 路径总和 III，medium
 * 给定一个二叉树的根节点 root，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的 路径 的数目。
 * 路径 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
 *
 * 主要是考察 dfs + 递归
 *
 * 未能独立求解
 *
 * author: qonyqian
 * created on: 2022/2/14 9:32 下午
 * version：1.0
 * description:
 */
public class LeetCode437 {

    /**
     * 1.暴力法，递归求解所有节点的路径和
     * https://leetcode-cn.com/problems/path-sum-iii/solution/lu-jing-zong-he-iii-by-leetcode-solution-z9td/
     *
     * 官方代码
     * @param root
     * @param targetSum
     * @return
     */
    public int pathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return 0;
        }

        int ret = rootSum(root, targetSum);
        ret += pathSum(root.left, targetSum);
        ret += pathSum(root.right, targetSum);
        return ret;
    }

    public int rootSum(TreeNode root, int targetSum) {
        int ret = 0;

        if (root == null) {
            return 0;
        }
        int val = root.val;
        if (val == targetSum) {
            ret++;
        }

        ret += rootSum(root.left, targetSum - val);
        ret += rootSum(root.right, targetSum - val);
        return ret;
    }

}
