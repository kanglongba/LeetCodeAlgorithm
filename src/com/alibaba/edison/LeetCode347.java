package com.alibaba.edison;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 前 K 个高频元素，medium
 * 给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。
 * 进阶：你所设计算法的时间复杂度 必须 优于 O(n log n) ，其中 n 是数组大小。
 * <p>
 * 与LeetCode215相似
 * <p>
 * author: qonyqian
 * created on: 2022/2/13 11:59 下午
 * version：1.0
 * description:
 */
public class LeetCode347 {

    /**
     * 1.排序 + 遍历
     * <p>
     * 本题的算法很朴素，没有银弹，就是正常的思维
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


    /**
     * PriorityQueue<Integer> 是基于 最小堆（Min-Heap） 实现的，因此 queue 内部的数字是按从小到大的顺序进行排列的。
     * <p>
     * 具体来说，PriorityQueue 总是将最小的元素放在队列的头部（即优先队列的根节点），当你调用 poll() 或 peek() 方法时，它会返回队列中当前最小的元素。
     * <p>
     * 但是，PriorityQueue 并不是对整个队列进行排序，它只保证在你获取元素时，总能获得队列中最小的那个元素。因此，队列内部的元素排列不一定是全局有序的，只有通过不断调用 poll() 才能从小到大依次获取元素。
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] topKFrequent2(int[] nums, int k) {
        if (nums == null) {
            return null;
        }
        HashMap<Integer, Integer> counts = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (counts.containsKey(nums[i])) {
                counts.put(nums[i], counts.get(nums[i]) + 1);
            } else {
                counts.put(nums[i], 1);
            }
            System.out.println("count " + nums[i] + " " + counts.get(nums[i]));
        }
        PriorityQueue<Map.Entry<Integer, Integer>> queue = new PriorityQueue<>(new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return o1.getValue() - o2.getValue();
            }
        });
        for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
            System.out.println("compare " + entry.getKey() + " " + entry.getValue());
            if (queue.size() < k) {
                queue.offer(entry);
                System.out.println("1 " + entry.getKey());
            } else {
                if (queue.peek().getValue() < entry.getValue()) {
                    queue.poll();
                    queue.offer(entry);
                    System.out.println("2 " + entry.getKey());
                }
            }
        }
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = queue.poll().getKey();
        }
        return result;
    }

    public static void main(String[] args) {
        int[] input = new int[]{1, 1, 1, 2, 2, 3};
        LeetCode347 leet = new LeetCode347();
        int[] rsult = leet.topKFrequent2(input, 2);
        int[] input2 = {4, 1, -1, 2, -1, 2, 3};
        int[] result2 = leet.topKFrequent2(input2, 2);
    }
}
