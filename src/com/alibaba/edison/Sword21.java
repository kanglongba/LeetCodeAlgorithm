package com.alibaba.edison;

/**
 * Created by 钧戈 on 2020/10/30.
 */
public class Sword21 {

    public int[] exchange(int[] nums) {
        //双指针法
        return point2(nums);
    }

    /**
     * 双指针，一个从前往后，一个从后往前
     * @param nums
     * @return
     */
    private int[] point2(int[] nums) {
        int head = 0;
        int tail = nums.length -1;
        while (head < tail) {
            while (head < tail && nums[head] % 2 == 1) {
                head++;
            }
            while (tail > head && nums[tail] % 2 == 0) {
                tail--;
            }
            int temp = nums[head];
            nums[head] = nums[tail];
            nums[tail] = temp;
            head++;
            tail--;
        }
        return nums;
    }
}
