package com.alibaba.edison;

import java.util.Arrays;

/**
 * 最短无序连续子数组，medium
 * 给你一个整数数组 nums ，你需要找出一个 连续子数组 ，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。
 * 请你找出符合题意的 最短 子数组，并输出它的长度。
 *
 * 输入：nums = [2,6,4,8,10,9,15]
 * 输出：5
 *
 * 未能得出最优解
 *
 * author: qonyqian
 * created on: 2022/2/15 3:56 下午
 * version：1.0
 * description:
 */
public class LeetCode581 {

    /**
     * 看着像滑动窗口
     * 从两边向中间探索，如果边界分别是最小值和最大值，就继续向中间移动
     *
     * 暴力滑，性能不太好
     * 执行用时：106 ms, 在所有 Java 提交中击败了 5.14% 的用户
     * 内存消耗：42 MB, 在所有 Java 提交中击败了 10.23% 的用户
     *
     * @param nums
     * @return
     */
    public int findUnsortedSubarray(int[] nums) {
        int length = nums.length;
        if (length < 2) {
            return 0;
        }
        int left = 0, right = length -1;
        boolean findLeft = false, findRight = false;
        while (left < right) {
            if (min(nums, left, right) && !findLeft) {
                left++;
            } else {
                findLeft = true;
            }
            if (max(nums, left, right) && !findRight) {
                right--;
            } else {
                findRight = true;
            }
            if (findLeft && findRight) {
                break;
            }
        }
        if (right == left) {
            return 0;
        } else {
            return right - left + 1;
        }
    }

    private boolean min(int[] nums, int left, int right) {
        for (int i = left+1; i <= right; i++) {
            if (nums[i] < nums[left]) {
                return false;
            }
        }
        return true;
    }

    private boolean max(int[] nums, int left, int right) {
        for (int i = right-1; i >= left ; i--) {
            if (nums[i] > nums[right]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 排序法：需要经过认真的分析，找到规律，才能想到排序法
     * 大神题解：https://leetcode-cn.com/problems/shortest-unsorted-continuous-subarray/solution/gong-shui-san-xie-yi-ti-shuang-jie-shuan-e1le/
     * 一看就懂，比官方代码写得好
     * @param nums
     * @return
     */
    public int sort(int[] nums) {
        int n = nums.length;
        // 复制数组
        // int[] numsSorted = new int[nums.length];
        // System.arraycopy(nums, 0, numsSorted, 0, nums.length);
        int[] arr = nums.clone();
        Arrays.sort(arr);
        int i = 0, j = n - 1;
        while (i <= j && nums[i] == arr[i]) i++;
        while (i <= j && nums[j] == arr[j]) j--;
        return j - i + 1;
    }

    /**
     * 一次遍历
     * 官方题解：https://leetcode-cn.com/problems/shortest-unsorted-continuous-subarray/solution/zui-duan-wu-xu-lian-xu-zi-shu-zu-by-leet-yhlf/
     * @param nums
     * @return
     */
    public int point(int[] nums) {
        int n = nums.length;
        int maxn = Integer.MIN_VALUE, right = -1;
        int minn = Integer.MAX_VALUE, left = -1;
        for (int i = 0; i < n; i++) {
            if (maxn > nums[i]) {
                right = i;
            } else {
                maxn = nums[i];
            }
            if (minn < nums[n - i - 1]) {
                left = n - i - 1;
            } else {
                minn = nums[n - i - 1];
            }
        }
        return right == -1 ? 0 : right - left + 1;
    }

}
