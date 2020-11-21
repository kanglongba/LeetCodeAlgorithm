package com.alibaba.edison;

/**
 * Created by 钧戈 on 2020/10/4.
 */
public class Sword56_i {

    public int[] singleNumbers(int[] nums) {
        return singleNumbers1(nums);
    }

    public int[] singleNumbers1(int[] nums) {
        int ret = 0;
        for (int n : nums) {
            ret ^= n;
        }
        int div = 1;
        while ((div & ret) == 0) {
            div <<= 1;
        }
        int a = 0, b = 0;
        for (int n : nums) {
            if ((div & n) != 0) {
                a ^= n;
            } else {
                b ^= n;
            }
        }
        return new int[]{a, b};
    }

    /**
     * 二刷 2020-11-01 20:14:51
     * @param nums
     * @return
     */
    private int[] ershua(int[] nums) {
        int res = 0;
        for (int num : nums) {
            res ^= num;
        }
        int div = 1;
        while ((res & div) == 0) {
            div <<= 1;
        }
        int a=0,b=0;
        for (int num : nums) {
            if ((num & div) == 1) {
                a ^= num;
            } else {
                b ^= num;
            }
        }
        return new int[]{a,b};
    }
}
