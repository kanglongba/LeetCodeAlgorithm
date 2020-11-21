package com.alibaba.edison;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
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
