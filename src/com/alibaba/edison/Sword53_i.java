package com.alibaba.edison;

/**
 * Created by 钧戈 on 2020/10/31.
 */
public class Sword53_i {
    public int search(int[] nums, int target) {
        return bianli(nums, target);
    }

    /**
     * 二刷 2020-10-31 17:32:35
     * @param nums
     * @param target
     * @return
     */
    private int bianli(int[] nums, int target) {
        int count = 0;
        for (int i = 0; i < nums.length && nums[i] <= target; i++) {
            if (nums[i] == target) {
                count++;
            }
        }
        return count;
    }
}
