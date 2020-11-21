package com.alibaba.edison;

/**
 * Created by 钧戈 on 2020/11/1.
 */
public class Sword53_ii {

    public int missingNumber(int[] nums) {
        return erfen(nums);
    }

    /**
     * 二刷 2020-11-01 16:59:14
     * @return
     */
    private int erfen(int[] nums) {
        int low = 0;
        int high = nums.length-1;
        int mid;
        while (low < high) {
            mid = (low+high)/2;
            if (nums[mid] == mid) {
                low = mid+1;
            } else {
                high = mid;
            }
        }
        if (nums[low] == low) {
            return nums[low] + 1;
        } else {
            return nums[low] - 1;
        }
    }
}
