package com.alibaba.edison;

import java.util.HashMap;
import java.util.Map;

/**
 * 打家劫舍 III，medium
 * 小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为 root 。
 * 除了 root 之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。 如果
 * 两个直接相连的房子在同一天晚上被打劫 ，房屋将自动报警。
 * 给定二叉树的 root 。返回在不触动警报的情况下，小偷能够盗取的最高金额。
 *
 * LeetCode198 打家劫舍 I ，房子按数组排列，动态规划
 * LeetCode231 打家劫舍 II
 *
 * 未能独立解答。在参考了官方的解题思想后，独立实现了代码。
 *
 * 也是很经典的一道题。
 *
 * author: qonyqian
 * created on: 2022/2/13 10:44 下午
 * version：1.0
 * description:
 */
public class LeetCode337 {

    /**
     * 1.回溯法，探索所有可能，然后得最大值。【时间复杂度抗不住】
     * 2.动态规划 【这竟然也能动态规划】。由于数据是树形结构，相应的dp结构也不能是数组了，在本题中是一个HashMap。
     *
     * 官方题解：https://leetcode-cn.com/problems/house-robber-iii/solution/da-jia-jie-she-iii-by-leetcode-solution/
     *
     * 动态规划 + DFS + 递归
     *
     * dp1(root)表示选中这个节点的偷盗最大值
     * dp2(root)表示不选中这个节点的偷盗最大值
     *
     * 状态转移方程：
     * dp1(root) = dp2(left) + dp2(right) + root，选中了root，那么左右子树都不能选中
     * dp2(root) = max(dp1(left), dp2(left)) + max(dp1(right), dp2(right))，未选中root，那么左右子树可以被选中，也可以不被选中
     *
     * max = max(dp1(root), dp2(root))
     *
     * 由于是树形结构，不能使用数组表示dp，在本题中使用HashMap
     *
     * @param root
     * @return
     */
    public int rob(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int select = dfs(root, true); //选中root
        int unSelect = dfs(root, false); //不选中root
        return Math.max(select, unSelect);
    }

    /**
     * 因为是自顶向下，所以必须使用递归
     *
     * @param root
     * @return
     */
    public int dfs(TreeNode root, boolean select) {
        if (root == null) {
            return 0;
        }
        if (select && dp1.containsKey(root)) { //选中，并且dp1有值，直接取出
            return dp1.get(root);
        }
        if (!select && dp2.containsKey(root)) { //未选中，并且dp2有值，直接取出
            return dp2.get(root);
        }
        int count = 0;
        if (select) { //选中
            count = root.val + dfs(root.left, false) + dfs(root.right, false); //自己的值 + 未选中左子树的值 + 未选中右子树的值
            dp1.put(root, count);
        } else { //未选中。不能加自己的值了，只能用左右子树的值。
            count = Math.max(dfs(root.left, false), dfs(root.left, true)) + Math.max(dfs(root.right, false), dfs(root.right, true));
            dp2.put(root, count);
        }
        return count;
    }

    /**
     * 选中root时，偷盗最大值
     */
    HashMap<TreeNode, Integer> dp1 = new HashMap<>();
    /**
     * 不选中root时，偷盗最大值
     */
    HashMap<TreeNode, Integer> dp2 = new HashMap<>();


    /**
     * 官方代码。官方代码的递归写得很有参考意义。
     *
     * @param root
     * @return
     */
    public int rob2(TreeNode root) {
        dfs2(root);
        return Math.max(f.getOrDefault(root, 0), g.getOrDefault(root, 0));
    }

    public void dfs2(TreeNode node) {
        if (node == null) {
            return;
        }
        dfs2(node.left);
        dfs2(node.right);
        f.put(node, node.val + g.getOrDefault(node.left, 0) + g.getOrDefault(node.right, 0));
        g.put(node, Math.max(f.getOrDefault(node.left, 0), g.getOrDefault(node.left, 0)) + Math.max(f.getOrDefault(node.right, 0), g.getOrDefault(node.right, 0)));
    }

    Map<TreeNode, Integer> f = new HashMap<TreeNode, Integer>();
    Map<TreeNode, Integer> g = new HashMap<TreeNode, Integer>();
}
