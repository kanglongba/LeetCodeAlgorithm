package com.alibaba.edison;

/**
 * 在排序数组中查找元素的第一个和最后一个位置，medium
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 * <p>
 * 如果数组中不存在目标值 target，返回[-1, -1]。
 * <p>
 * 1.直接遍历
 * 2.先用查找算法，确定位置，然后再向后面探索
 * <p>
 * author: qonyqian
 * created on: 2022/2/7 1:33 下午
 * version：1.0
 * description:
 */
public class LeetCode34 {

    /**
     * 直接遍历
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange(int[] nums, int target) {
        int left = -1, right = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                if (left == -1) {
                    left = i;
                    right = i;
                } else {
                    right = i;
                }
            } else if (nums[i] < target) {
            } else if (nums[i] > target) {
                break;
            }
        }
        return new int[]{left, right};
    }

    /**
     * 先用查找算法，确定位置，然后再向后面探索
     * @param nums
     * @param target
     * @return
     */
    public int[] midSearchRange(int[] nums, int target) {
        int leftIdx = binarySearch(nums, target, true);
        int rightIdx = binarySearch(nums, target, false) - 1;
        if (leftIdx <= rightIdx && rightIdx < nums.length && nums[leftIdx] == target && nums[rightIdx] == target) {
            return new int[]{leftIdx, rightIdx};
        }
        return new int[]{-1, -1};
    }

    public int binarySearch(int[] nums, int target, boolean lower) {
        int left = 0, right = nums.length - 1, ans = nums.length;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] > target || (lower && nums[mid] >= target)) {
                right = mid - 1;
                ans = mid;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }

}
