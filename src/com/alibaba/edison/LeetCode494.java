package com.alibaba.edison;


/**
 * 目标和，medium
 * 给你一个整数数组 nums 和一个整数 target 。
 * 向数组中的每个整数前添加'+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：
 * 例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。
 * 返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。
 *
 *
 * author: qonyqian
 * created on: 2022/2/14 11:16 下午
 * version：1.0
 * description:
 */
public class LeetCode494 {

    /**
     * 典型的回溯法
     * 我这算法，时间复杂度不太行：
     * 执行用时：799 ms, 在所有 Java 提交中击败了 5.00% 的用户
     * 内存消耗：38.9 MB, 在所有 Java 提交中击败了 20.15% 的用户
     *
     * @param nums
     * @param target
     * @return
     */
    public int findTargetSumWays(int[] nums, int target) {
        recursion(nums, nums.length, target,0,0,true);
        recursion(nums, nums.length, target,0,0,false);
        return count;
    }

    int count=0;

    /**
     * 注意题目要求每个元素都必须用上。并且不探索到最后一个元素，就不知道结果。所以只有探索，没有回溯。
     *
     * @param nums
     * @param length
     * @param target
     * @param index
     * @param sum
     * @param add
     * @return
     */
    public boolean recursion(int[] nums, int length, int target, int index, int sum, boolean add) {
        if (index >= length) { // 数组越界检查，甭管有用没有用，写上准没错
            return false;
        }
        if (add) {
            sum += nums[index];
        } else {
            sum -= nums[index];
        }
        if (index == length - 1) { // 已经探索到了最后一个位置
            if (sum == target) {
                count++;
                return true;
            } else {
                return false;
            }
        }
        boolean result1 = recursion(nums, length, target, index+1, sum, true); // 继续探索下一个位置
        boolean result2 = recursion(nums, length, target, index+1, sum, false); // 继续探索下一个位置
        return result1 || result2;
    }


    /**
     * 这道题竟然还可以用动态规划
     * 官方答案：https://leetcode-cn.com/problems/target-sum/solution/mu-biao-he-by-leetcode-solution-o0cp/
     *
     * 这道题本质属于背包问题的一种，力扣上的背包问题汇总：
     * 01背包：416. 分割等和子集 474. 一和零 494. 目标和 879. 盈利计划 1049. 最后一块石头的重量 II 1230. 抛掷硬币
     * 完全背包：1449. 数位成本和为目标值的最大数字 322. 零钱兑换 518. 零钱兑换 II 279. 完全平方数
     *
     * @param nums
     * @param target
     * @return
     */
    public int findTargetSumWaysDP(int[] nums, int target) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        int diff = sum - target;
        if (diff < 0 || diff % 2 != 0) {
            return 0;
        }
        int n = nums.length, neg = diff / 2;
        int[][] dp = new int[n + 1][neg + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            int num = nums[i - 1];
            for (int j = 0; j <= neg; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j >= num) {
                    dp[i][j] += dp[i - 1][j - num];
                }
            }
        }
        return dp[n][neg];
    }

}
