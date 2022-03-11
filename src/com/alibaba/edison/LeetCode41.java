package com.alibaba.edison;

import java.util.Arrays;

/**
 * 缺失的第一个正数，hard
 * 给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。
 * 请你实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案。
 *
 * 网易一面
 *
 * 输入：nums = [3,4,-1,1]
 * 输出：2
 *
 * 输入：nums = [7,8,9,11,12]
 * 输出：1
 *
 * author: qonyqian
 * created on: 2022/3/11 4:35 下午
 * version：1.0
 * description:
 */
public class LeetCode41 {

    /**
     * 先排序再遍历。
     * 时间复杂度不符合要求，空间复杂度符合要求
     * @param nums
     * @return
     */
    public int firstMissingPositive(int[] nums) {
        Arrays.sort(nums);
        int pre = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                if (nums[i] - pre == 1) {
                    pre = nums[i];
                } else if (nums[i] == pre) {
                    //empty
                } else {
                    pre++;
                    break;
                }
            }
            if (i == nums.length - 1) {
                pre++;
            }
        }
        return pre;
    }

    /**
     * 核心：长度为 n 的数组，未出现的最小正整数不会超过 n+1
     * 举例长度为1的数组：[1]、[2]、[3]、[4]等，未出现的最小正整数不会超过 2
     *
     * 使用辅助数组，下标表示 nums 数组中的元素，值表示元素的出现次数。
     * 最后遍历辅助数组，返回第一个 值 = 0 的下标。
     *
     * 官方题解：https://leetcode-cn.com/problems/first-missing-positive/solution/que-shi-de-di-yi-ge-zheng-shu-by-leetcode-solution/
     *
     * @param nums
     * @return
     */
    public int firstMissingPositive2(int[] nums) {
        int length = nums.length;
        int[] ans = new int[length + 2]; //结果值的范围是[1, n+1] ，所以数组的长度是 n+2（数组下标从0开始）
        for (int i = 0; i < length; i++) {
            if (nums[i] > 0 && nums[i] < length + 2) {
                ans[nums[i]]++;
            }
        }
        for (int i = 1; i < length + 2; i++) {
            if (ans[i] == 0) {
                return i;
            }
        }
        return -1;
    }
}
