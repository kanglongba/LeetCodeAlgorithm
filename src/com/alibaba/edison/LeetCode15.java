package com.alibaba.edison;

import java.util.*;

/**
 * 三数之和，medium
 * <p>
 * 给你一个包含 n 个整数的数组nums，判断nums中是否存在三个元素 a，b，c ，使得a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
 *
 * 未能独立解答
 *
 * author: qonyqian
 * created on: 2022/2/5 4:04 下午
 * version：1.0
 * description:
 */
public class LeetCode15 {

    /**
     * 首先想到滑动窗口，但是滑动窗口只适合连续子数组，因此肯定不能用来解答此题。
     * 暴力法，需要三重循环就可以检索到符合要求的元素，但无法解决重复元素的问题。
     * <p>
     * 最优解：排序 + 双指针。
     * 排序解决的是重复问题，保证 a<=b<=c，就能解决重复元素问题。除此之外，对于每一重循环而言，相邻两次枚举的元素不能相同。
     * 双指针用来优化第二重循环和第三重循环，当a确定时，随着b递增，c一定是递减，所以可以用双指针在一次遍历中完成两重循环的任务。
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        int n = nums.length;
        if (n < 3) {
            return Collections.emptyList();
        }
        //排序。
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();

        //循环
        for (int i = 0; i < n - 2; i++) { //n-2，防止数组超标
            if (i > 0 && nums[i - 1] == nums[i]) { // a相邻枚举的值不能相同，否则可能会造成重复元素
                continue;
            }
            int value = nums[i];
            int left = i + 1; //b从a下一位开始
            int right = n - 1; //c从数组末尾开始
            while (left < right) {
                if (nums[left] + nums[right] + value == 0) {
                    //找到了元素
                    List<Integer> list = new ArrayList<>();
                    list.add(value);
                    list.add(nums[left]);
                    list.add(nums[right]);
                    result.add(list);
                    left = findLeft(nums, left, right);
                    right = findRight(nums, left, right);
                } else if (nums[left] + nums[right] + value < 0) {
                    //nums[left] + nums[right] 的值偏小，左指针右移，增加整体的值
                    left = findLeft(nums, left, right);
                } else if (nums[left] + nums[right] + value > 0) {
                    //nums[left] + nums[right] 的值偏大，右移右指针，减小整体的值
                    right = findRight(nums, left, right);
                }
            }
        }
        return result;
    }

    private int findLeft(int[] nums, int left, int right) {
        int preLeft = left;
        left++;
        while (left < right) {
            //相邻两次探索的值不能相同，否则可能会造成重复与元素
            if (nums[preLeft] == nums[left]) {
                left++;
            } else {
                break;
            }
        }
        return left;
    }

    private int findRight(int[] nums, int left, int right) {
        int preRight = right;
        right--;
        while (left < right) {
            //相邻两次探索的值不能相同，否则可能会造成重复与元素
            if (nums[preRight] == nums[right]) {
                right--;
            } else {
                break;
            }
        }
        return right;
    }
}
