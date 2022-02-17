package com.alibaba.edison;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 数组中的第K个最大元素，medium
 * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
 *
 * 输入: [3,2,1,5,6,4] 和 k = 2
 * 输出: 5
 *
 * author: qonyqian
 * created on: 2022/2/5 11:37 下午
 * version：1.0
 * description:
 */
public class LeetCode215_ii {

    /**
     * 1.先排序，然后直接取第k个元素
     * 2.冒泡，冒K次，就是第k大【最优解】
     * 3.使用大根堆，Java中有现成的数据结构：PriorityQueue
     *
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest(int[] nums, int k) {
        int n = nums.length;
        for (int i = 0; i < k; i++) {
            int length = n-i;
            for (int j = 0; j < length; j++) {
                if (j+1 < length && nums[j] > nums[j+1]) {
                    int temp = nums[j+1];
                    nums[j+1] = nums[j];
                    nums[j] = temp;
                }
            }
        }
        return nums[nums.length-k];
    }

    public int sort(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length-k];
    }

    /**
     * 这种方式相比冒泡法，时间复杂度更优，但是冒泡法空间复杂度更优。
     * @param nums
     * @param k
     * @return
     */
    private int heap(int[] nums, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>(); //优先队列，默认按从小到大排序，可以当成大根堆/小根堆来用。
        for (int i = 0; i < nums.length; i++) {
            if (i < k) {
                heap.add(nums[i]); //前k个直接塞进去，并且按从大到小排好序
            } else {
                if (heap.peek() < nums[i]) { //peek，返回队头元素，但是不出队
                    heap.remove();
                    heap.add(nums[i]); //把队头替换成更大的元素，并且重新排序
                }
            }
        }
        return heap.peek(); //循环结束后，优先队列中就是前K个最大值，并且队头就是第K大。
    }
}
