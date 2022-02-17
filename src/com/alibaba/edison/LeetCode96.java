package com.alibaba.edison;

/**
 * 不同的二叉搜索树，medium
 * 给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？返回满足题意的二叉搜索树的种数。
 *
 * 二叉查找树（英语：Binary Search Tree），也称为二叉搜索树、二叉查找树、有序二叉树（ordered binary tree）或排序二叉
 * 树（sorted binary tree），是指一棵空树或者具有下列性质的二叉树：
 * 1.若任意节点的左子树不空，则左子树上所有节点的值均小于它的根节点的值；
 * 2.若任意节点的右子树不空，则右子树上所有节点的值均大于它的根节点的值；
 * 3.任意节点的左、右子树也分别为二叉查找树；
 *
 * 输入：n = 3
 * 输出：5
 *
 * 未能独立解答
 *
 * author: qonyqian
 * created on: 2022/2/8 7:39 下午
 * version：1.0
 * description:
 */
public class LeetCode96 {

    /**
     * 二叉搜索树的中序遍历是升序的
     *
     * 感觉又是回溯法，探索所有可能。【卧槽，竟然是动态规划】
     *
     * 官方题解：https://leetcode-cn.com/problems/unique-binary-search-trees/solution/bu-tong-de-er-cha-sou-suo-shu-by-leetcode-solution/
     *
     * @param n
     * @return
     */
    public int numTrees(int n) {
        int[] G = new int[n + 1];
        G[0] = 1;
        G[1] = 1;

        for (int i = 2; i <= n; ++i) {
            for (int j = 1; j <= i; ++j) {
                G[i] += G[j - 1] * G[i - j];
            }
        }
        return G[n];
    }
}
