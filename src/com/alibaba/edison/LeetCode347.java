package com.alibaba.edison;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 前 K 个高频元素，medium
 * 给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。
 * 进阶：你所设计算法的时间复杂度 必须 优于 O(n log n) ，其中 n 是数组大小。
 *
 * 与LeetCode215相似
 *
 * author: qonyqian
 * created on: 2022/2/13 11:59 下午
 * version：1.0
 * description:
 */
public class LeetCode347 {

    /**
     * 1.排序 + 遍历
     *
     * 本地的算法很朴素，没有银弹，就是正常的思维
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        PriorityQueue<Map.Entry<Integer, Integer>> queue = new PriorityQueue<>(new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return o1.getValue() - o2.getValue();
            }
        });
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (queue.size() < k) {
                queue.offer(entry);
            } else if (entry.getValue() > queue.peek().getValue()) {
                queue.poll();
                queue.offer(entry);
            }

        }
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = queue.poll().getKey();
        }
        return result;
    }

    public static void main(String[] args) {
        int[] input = new int[]{1,1,1,2,2,3};
        LeetCode347 leet = new LeetCode347();
        int[] rsult = leet.topKFrequent(input, 2);
    }
}
