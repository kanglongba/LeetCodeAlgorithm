package com.alibaba.edison;

import java.util.Arrays;

/**
 * Created by 钧戈 on 2020/10/30.
 */
public class Sword03 {

    /**
     * 1.hashmap法
     * 2.排序法
     * 3.原地置换法
     * @param nums
     * @return
     */
    public int findRepeatNumber(int[] nums) {
        return sort(nums);
    }

    private int sort(int[] nums) {
        Arrays.sort(nums);
        int value = nums[0];
        for (int i = 0; i < nums.length-1; i++) {
            if (nums[i] == nums[i + 1]) {
                value = nums[i];
                break;
            }
        }
        return value;
    }

    private int swap(int[] nums){
        Arrays.sort(nums);
        int temp = -1;
        for(int i=0;i<nums.length;i++){
            while(nums[i] != i) {
                if(nums[i] == nums[nums[i]]){
                    return nums[i];
                }
                temp = nums[i];
                nums[i] = nums[temp];
                nums[temp] = temp;
            }
        }
        return temp;
    }
}
