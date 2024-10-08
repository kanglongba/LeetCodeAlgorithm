package com.alibaba.edison;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 数组中的第K个最大元素， medium
 *
 * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
 *
 * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 *
 * 你必须设计并实现时间复杂度为 O(n) 的算法解决此问题。
 *
 *
 *
 * 示例 1:
 *
 * 输入: [3,2,1,5,6,4], k = 2
 * 输出: 5
 * 示例 2:
 *
 * 输入: [3,2,3,1,2,4,5,5,6], k = 4
 * 输出: 4
 *
 * https://leetcode.cn/problems/kth-largest-element-in-an-array/description/
 *
 * 同Sword40
 * Created by 钧戈 on 2020/9/13.
 */
public class LeetCode215 {
    public int findKthLargest(int[] nums, int k) {
        return heap(nums, k);
    }

    private int sort(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length-k];
    }

    /**
     * 维护一个小根堆
     * @param nums
     * @param k
     * @return
     */
    private int heap(int[] nums, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (int i = 0; i < nums.length; i++) {
            if (i < k) {
                heap.add(nums[i]);
            } else {
                if (heap.peek() < nums[i]) {
                    heap.remove();
                    heap.add(nums[i]);
                }
            }
        }
        return heap.peek();
    }
}
