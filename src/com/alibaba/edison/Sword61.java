package com.alibaba.edison;

import java.util.Arrays;

/**
 * Created by 钧戈 on 2020/10/31.
 */
public class Sword61 {

    public boolean isStraight(int[] nums) {
        return shunzi(nums);
    }

    /**
     * 二刷 2020-10-31 22:54:05
     * @param nums
     * @return
     */
    private boolean shunzi(int[] nums) {
        Arrays.sort(nums);
        int joker = 0;
        for (int i = 0; i < 4; i++) {
            if (nums[i] == 0) {
                joker++;
            } else if (nums[i] == nums[i + 1]) {
                return false;
            }
        }
        return nums[4]-nums[joker] < 5;
    }
}
