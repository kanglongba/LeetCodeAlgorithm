package com.alibaba.edison;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 同leetCode169
 * Created by 钧戈 on 2020/9/13.
 */
public class Sword39 {
    public int majorityElement(int[] nums) {
        //1.排序法
        //2.HashMap法\TreeMap法
        //3.摩尔投票法
        //4.分治法
        return majorityElementRec(nums, 0, nums.length-1);
    }

    private int hashMap(int[] nums) {
        HashMap<Integer, Integer> hashMap = new HashMap<>(nums.length);
        int length = nums.length;
        int k = length/2 + 1;
        int value = 0;
        for (int i = 0; i < nums.length; i++) {
            if (hashMap.containsKey(nums[i])) {
                int old = hashMap.get(nums[i]);
                hashMap.put(nums[i], old+1);
                if (old + 1 >= k) {
                    value = nums[i];
                    break;
                }
            } else {
                hashMap.put(nums[i], 1);
            }
        }
        return value;
    }

    /**
     * 先对数组排序，然后搜索坐标为i和i+k的元素是不是相同
     * 这种是通用方法，可以求超过1/2、1/3、1/4等
     * @param nums
     * @return
     */
    private int sort(int[] nums) {
        Arrays.sort(nums);
        int length = nums.length;
        int k = length/2 + 1;
        int value = 0;
        for (int i = 0; i < length-k+1; i++) {
            if (nums[i] == nums[i + k - 1]) {
                value = nums[i];
                break;
            }
        }
        return value;
    }

    /**
     * 摩尔投票法只适用求半数问题
     * @param nums
     * @return
     */
    private int vote(int[] nums) {
        int vote = 1;
        int num = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (vote == 0) {
                num=nums[i];
                vote++;
            } else {
                if (num == nums[i]) {
                    vote++;
                } else {
                    vote--;
                }
            }
        }
        return num;
    }

    /**
     * 使用分治法求解
     * https://leetcode-cn.com/problems/majority-element/solution/duo-shu-yuan-su-by-leetcode-solution/
     * @param nums
     * @param lo
     * @param hi
     * @return
     */
    private int majorityElementRec(int[] nums, int lo, int hi) {
        // base case; the only element in an array of size 1 is the majority
        // element.
        if (lo == hi) {
            return nums[lo];
        }

        // recurse on left and right halves of this slice.
        int mid = (hi-lo)/2 + lo;
        int left = majorityElementRec(nums, lo, mid);
        int right = majorityElementRec(nums, mid+1, hi);

        // if the two halves agree on the majority element, return it.
        if (left == right) {
            return left;
        }

        // otherwise, count each element and return the "winner".
        int leftCount = countInRange(nums, left, lo, hi);
        int rightCount = countInRange(nums, right, lo, hi);

        return leftCount > rightCount ? left : right;
    }

    private int countInRange(int[] nums, int num, int lo, int hi) {
        int count = 0;
        for (int i = lo; i <= hi; i++) {
            if (nums[i] == num) {
                count++;
            }
        }
        return count;
    }
}
