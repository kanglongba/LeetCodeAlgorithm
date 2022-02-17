package com.alibaba.edison;

/**
 * 乘积最大子数组，medium
 * 给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
 *
 * 输入: nums = [2,3,-2,4]
 * 输出: 6
 * 解释: 子数组 [2,3] 有最大乘积 6。
 *
 * 未能独立解答
 * <p>
 * author: qonyqian
 * created on: 2022/2/10 10:27 下午
 * version：1.0
 * description:
 */
public class LeetCode152 {

    /**
     * 凭直觉就是动态规划，但是没有独立找出状态转移方程
     * <p>
     * https://leetcode-cn.com/problems/maximum-product-subarray/solution/cheng-ji-zui-da-zi-shu-zu-by-leetcode-solution/
     * <p>
     * 我们可以根据正负性进行分类讨论
     * 考虑当前位置如果是一个负数的话，那么我们希望以它前一个位置结尾的某个段的积也是个负数，这样就可以负负得正，并且我们希望这个积尽
     * 可能「负得更多」，即尽可能小。如果当前位置是一个正数的话，我们更希望以它前一个位置结尾的某个段的积也是个正数，并且希望它尽可能
     * 地大。于是这里我们可以维护两个状态转移方程：
     * 1. fmin(i)，它表示以第 i 个元素结尾的乘积最小子数组的乘积。
     * 2. fmax(i)，它表示以第 i 个元素结尾的乘积最大子数组的乘积。
     *
     * @param nums
     * @return
     */
    public int maxProduct(int[] nums) {
        int length = nums.length;
        int[] maxF = new int[length];
        int[] minF = new int[length];
        System.arraycopy(nums, 0, maxF, 0, length);
        System.arraycopy(nums, 0, minF, 0, length);
        for (int i = 1; i < length; ++i) {
            maxF[i] = Math.max(maxF[i - 1] * nums[i], Math.max(nums[i], minF[i - 1] * nums[i]));
            minF[i] = Math.min(minF[i - 1] * nums[i], Math.min(nums[i], maxF[i - 1] * nums[i]));
        }
        int ans = maxF[0];
        for (int i = 1; i < length; ++i) {
            ans = Math.max(ans, maxF[i]);
        }
        return ans;

    }
}
