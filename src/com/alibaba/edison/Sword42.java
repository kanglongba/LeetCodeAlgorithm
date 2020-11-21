package com.alibaba.edison;

/**
 * Created by 钧戈 on 2020/10/30.
 */
public class Sword42 {


    public int maxSubArray(int[] nums) {
        return dp(nums);
    }

    private int dp1(int[] nums) {
        int[] max = new int[nums.length];
        for(int i=0;i<nums.length;i++){
            max[i] = nums[i];
        }
        int maxValue = nums[0];
        for(int i=1;i<nums.length;i++){
            if(nums[i]+max[i-1] >= max[i]) {
                max[i] = nums[i] + max[i-1];
            }
            if(max[i] >= maxValue) {
                maxValue = max[i];
            }
        }
        return maxValue;
    }

    /**
     * 二刷：2020-10-31
     * 一看就知道要用dp
     * @param nums
     * @return
     */
    private int dp(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int value = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (dp[i - 1] > 0) {
                dp[i] = dp[i-1] + nums[i];
            } else {
                dp[i] = nums[i];
            }
            if (value < dp[i]) {
                value = dp[i];
            }
        }
        return value;
    }
}
