package com.alibaba.edison;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 二叉树的最近公共祖先，medium
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，满足 x 是 p、q 的祖先且 x 的深度
 * 尽可能大（一个节点也可以是它自己的祖先）。”
 * <p>
 * 同Sword 68-ii :https://leetcode-cn.com/problems/er-cha-shu-de-zui-jin-gong-gong-zu-xian-lcof/
 *
 * 未能想到最优解
 *
 * author: qonyqian
 * created on: 2022/2/11 6:56 下午
 * version：1.0
 * description:
 */
public class LeetCode236 {

    /**
     * 非常非常经典的一道题
     * <p>
     * 暴力+dfs，求出每个节点的dfs路径，然后遍历路径，找到路径开始不同的那个点。
     * <p>
     * 但是我这种算法，性能不太好：
     * 执行用时：10 ms, 在所有 Java 提交中击败了 16.44% 的用户
     * 内存消耗：42 MB, 在所有 Java 提交中击败了 13.39% 的用户
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        Deque<TreeNode> pQueue = new ArrayDeque<>();
        Deque<TreeNode> qQueue = new ArrayDeque<>();
        isFind = false;
        recursion(root, p, pQueue);
        isFind = false;
        recursion(root, q, qQueue);
        TreeNode pre = root;
        while (!pQueue.isEmpty() && !qQueue.isEmpty()) { //从头开始，同步遍历两个集合
            TreeNode temp = pQueue.poll();
            if (temp == qQueue.poll()) {
                pre = temp;
            } else { //遇到一地个不等节点，他们的前驱节点就是最近公共祖先
                break;
            }
        }
        return pre;
    }

    boolean isFind = false;

    /**
     * 回溯 +递归
     *
     * @param root
     * @param node
     * @param queue
     */
    public void recursion(TreeNode root, TreeNode node, Deque<TreeNode> queue) {
        if (root == null) {
            return;
        }
        if (root == node) { //找到了节点，任务完成
            isFind = true;
            queue.offer(root); //把节点添加到集合
            return;
        }
        queue.add(root);
        recursion(root.left, node, queue); //dfs探索左子树
        if (isFind) { //如果找到了就提前返回
            return;
        }
        recursion(root.right, node, queue); //dfs探索右子树
        if (isFind) { //如果找到了就提前返回
            return;
        }
        queue.pollLast(); //没有找到，回溯
    }


    /**
     * 大神题解，比官方要好：https://leetcode-cn.com/problems/er-cha-shu-de-zui-jin-gong-gong-zu-xian-lcof/solution/mian-shi-ti-68-ii-er-cha-shu-de-zui-jin-gong-gon-7/
     * <p>
     * 大神的算法很精妙，p和q位置经过抽象就这么两种情况：
     * 1.如果p和q分别在root的左右子树，那么root就是公共祖先
     * 2.如果p或q 其中一个是root，那么p或q自己就是公共祖先
     * 采用递归搜索root的左右子树。
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        //抽象的第二种情况
        if (root == null || root == p || root == q) { //如果p或q 其中一个是root，那么p或q自己就是公共祖先
            return root;
        }
        //抽象的第一种情况
        TreeNode left = lowestCommonAncestor2(root.left, p, q); //在左子树中寻找 p 或 q，dfs搜索，找到就返回。
        TreeNode right = lowestCommonAncestor2(root.right, p, q); //在右子树中寻找 p 或 q，dfs搜索，找到就返回。
        if (left == null) { //左子树中没有 p 和 q，那么 p 和 q 的公共祖先一定是右子树
            return right;
        } else if (right == null) { //右子树中没有 p 和 q，那么 p 和 q 的公共祖先一定是左子树
            return left;
        } else {
            return root; //如果分别在左右子树中找到 p 和 q，那么root就是公共祖先。
        }
    }
}
