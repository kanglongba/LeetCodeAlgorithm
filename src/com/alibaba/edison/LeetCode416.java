package com.alibaba.edison;

/**
 * 分割等和子集，medium
 * 给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 *
 * 输入：nums = [1,5,11,5]
 * 输出：true
 * 解释：数组可以分割成 [1, 5, 5] 和 [11] 。
 *
 * 本题是经典的「NP 完全问题」
 * 「0−1 背包问题」
 *
 * 这道题可以换一种表述：给定一个只包含正整数的非空数组 nums[0]，判断是否可以从数组中选出一些数字，使得这些数字的和等于整个数组的元素
 * 和的一半。因此这个问题可以转换成「0−1 背包问题」。这道题与传统的「0−1 背包问题」的区别在于，传统的「0−1 背包问题」要求选取的物品的
 * 重量之和不能超过背包的总容量，这道题则要求选取的数字的和恰好等于整个数组的元素和的一半。类似于传统的「0-10−1 背包问题」，可以使用动
 * 态规划求解。
 *
 * 未能独立求解
 *
 * 过于复杂，直接跳过
 *
 * author: qonyqian
 * created on: 2022/2/14 6:36 下午
 * version：1.0
 * description:
 */
public class LeetCode416 {

    /**
     * 凭直觉就是动态规划:
     * 官方题解https://leetcode-cn.com/problems/partition-equal-subset-sum/solution/fen-ge-deng-he-zi-ji-by-leetcode-solution/
     * 大神题解：https://leetcode-cn.com/problems/partition-equal-subset-sum/solution/0-1-bei-bao-wen-ti-xiang-jie-zhen-dui-ben-ti-de-yo/
     *
     * 在使用动态规划求解之前，首先需要进行以下判断：
     * 1.根据数组的长度 n 判断数组是否可以被划分。如果 n<2，则不可能将数组分割成元素和相等的两个子集，因此直接返回 false。
     * 2.计算整个数组的元素和 sum 以及最大元素 maxNum。如果 sum 是奇数，则不可能将数组分割成元素和相等的两个子集，因此直接返回 false。如
     * 果 sum 是偶数，则令 target= sum / 2，需要判断是否可以从数组中选出一些数字，使得这些数字的和等于 target。如果 maxNum>target，则
     * 除了 maxNum 以外的所有元素之和一定小于 target，因此不可能将数组分割成元素和相等的两个子集，直接返回 false。
     *
     * 创建二维数组 dp，包含 n 行 target+1 列，其中 dp[i][j] 表示从数组的 [0,i] 下标范围内选取若干个正整数（可以是 0 个），是否存在
     * 一种选取方案使得被选取的正整数的和等于 j。初始时，dp 中的全部元素都是 false。
     *
     * 在定义状态之后，需要考虑边界情况。以下两种情况都属于边界情况。
     * 1.如果不选取任何正整数，则被选取的正整数等于 0。因此对于所有 0≤i<n，都有 dp[i][0]=true。
     * 2.当 i==0 时，只有一个正整数 nums[0] 可以被选取，因此 dp[0][nums[0]]=true。
     *
     * 对于 i>0 且 j>0 的情况，如何确定 dp[i][j] 的值？需要分别考虑以下两种情况：
     * 1.如果 j≥nums[i]，则对于当前的数字 nums[i]，可以选取也可以不选取，两种情况只要有一个为 true，就有 dp[i][j]=true。
     * 1.1 如果不选取 nums[i]，则 dp[i][j]=dp[i−1][j]；
     * 1.2 如果选取 nums[i]，则 dp[i][j]=dp[i−1][j−nums[i]]
     * 2.如果 j<nums[i]，则在选取的数字的和等于 j 的情况下无法选取当前的数字 nums[i]，因此有 dp[i][j]=dp[i−1][j]。
     *
     * 最终得到 dp[n−1][target] 即为答案。
     *
     *
     * 官方代码
     * @param nums
     * @return
     */
    public boolean canPartition(int[] nums) {
        int n = nums.length;
        if (n < 2) {
            return false;
        }
        int sum = 0, maxNum = 0;
        for (int num : nums) {
            sum += num;
            maxNum = Math.max(maxNum, num);
        }
        if (sum % 2 != 0) {
            return false;
        }
        int target = sum / 2;
        if (maxNum > target) {
            return false;
        }
        boolean[][] dp = new boolean[n][target + 1];
        for (int i = 0; i < n; i++) {
            dp[i][0] = true;
        }
        dp[0][nums[0]] = true;
        for (int i = 1; i < n; i++) {
            int num = nums[i];
            for (int j = 1; j <= target; j++) {
                if (j >= num) {
                    dp[i][j] = dp[i - 1][j] | dp[i - 1][j - num];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[n - 1][target];
    }

}
