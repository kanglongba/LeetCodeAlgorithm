package com.alibaba.edison;

/**
 * 下一个排列，medium
 *
 * https://leetcode-cn.com/problems/next-permutation/
 *
 * 题目描述太拗口了，看不懂。
 *
 * 未能独立解答。
 *
 * 大神解释：
 * 题干的意思是：找出这个数组排序出的所有数中，刚好比当前数大的那个数
 * 比如当前 nums = [1,2,3]。这个数是123，找出1，2，3这3个数字排序可能的所有数，排序后，比123大的那个数 也就是132
 * 如果当前 nums = [3,2,1]。这就是1，2，3所有排序中最大的那个数，那么就返回1，2，3排序后所有数中最小的那个，也就是1，2，3 -> [1,2,3]
 *
 * author: qonyqian
 * created on: 2022/2/5 11:18 下午
 * version：1.0
 * description:
 */
public class LeetCode31 {


    /**
     * 直接看答案吧：https://leetcode-cn.com/problems/next-permutation/solution/xia-yi-ge-pai-lie-by-leetcode-solution/
     * 感觉这题价值不大。
     *
     * 以  [4,5,2,6,3,1]  为例，从右向左寻找 i 对应 2，j 对应 3
     *
     * @param nums
     */
    public void nextPermutation(int[] nums) {
        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        if (i >= 0) {
            int j = nums.length - 1;
            while (j >= 0 && nums[i] >= nums[j]) {
                j--;
            }
            swap(nums, i, j);
        }
        reverse(nums, i + 1);
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public void reverse(int[] nums, int start) {
        int left = start, right = nums.length - 1;
        while (left < right) {
            swap(nums, left, right);
            left++;
            right--;
        }
    }

}
