package com.alibaba.edison;

import java.util.HashMap;

/**
 * 和为 K 的子数组，medium
 * 给你一个整数数组 nums 和一个整数 k ，请你统计并返回该数组中和为 k 的连续子数组的个数。
 *
 * 输入：nums = [1,2,3], k = 3
 * 输出：2
 *
 * 未独立得出最优解
 *
 * author: qonyqian
 * created on: 2022/2/15 11:24 上午
 * version：1.0
 * description:
 */
public class LeetCode560 {

    /**
     * 1.暴力法，遍历数组，然后从nums[i]往前探索，看是否能达成和为k
     * @param nums
     * @param k
     * @return
     */
    public int subarraySum(int[] nums, int k) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == k) { //他自己就等于 k
                count++;
            }
            int sum = nums[i];
            for (int j = i - 1; j >= 0; j--) { //以它为结尾，向前探索。
                sum += nums[j];
                if (sum == k) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * 前缀和 + Hash表优化
     * 官方答案：https://leetcode-cn.com/problems/subarray-sum-equals-k/solution/he-wei-kde-zi-shu-zu-by-leetcode-solution/
     * 直接看官方注解，讲的很明白。
     * @param nums
     * @param k
     * @return
     */
    public int subarraySum2(int[] nums, int k) {
        // pre表示前 i 个元素之和，即 pre = sum(nums[0],nums[1],...,nums[i])
        // 那么以 nums[i] 为结尾，向前寻找和为k的子数组，即 ans = sum(nums[j],nums[j+1],...,nums[i]) = k
        // 两者结合一下，pre - ans = pre - k = sum(nums[0],nums[1],...,nums[j-1])
        // 所以，寻找以 nums[i] 结尾的连续子数组，就相当于 在 [0, i] 区间内，寻找以 nums[0] 开头，和为 pre - k 的连续子数组的数量
        // 这样就将对数组的搜索转化为了正向搜索，使后面的计算可以复用前面的计算结果。
        int count = 0, pre = 0;
        // 可以使用HashMap优化寻找和为 pre -k 的子数组的过程。优化为O(1)
        // key表示以nums[0]为起点的子数组和，value表示子数组的数量
        HashMap<Integer, Integer> mp = new HashMap<>();
        mp.put(0, 1); //这是为了处理 nums[i] = k的情况，表明自己一个元素就能构成子数组，这个时候 key = 0，value = 1.
        for (int i = 0; i < nums.length; i++) {
            pre += nums[i];
            if (mp.containsKey(pre - k)) {
                count += mp.get(pre - k);
            }
            mp.put(pre, mp.getOrDefault(pre, 0) + 1);
        }
        return count;
    }
}
