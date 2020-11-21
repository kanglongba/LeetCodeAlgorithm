package com.alibaba.edison;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by 钧戈 on 2020/10/2.
 */
public class Sword59_i {

    public int[] maxSlidingWindow(int[] nums, int k) {
        return queue(nums, k);
    }

    private int[] queue(int[] nums, int k){
        if (nums == null || nums.length ==0 || k==0) {
            return new int[0];
        }
        Deque<Integer> deque = new LinkedList<>();
        int[] res = new int[nums.length-k+1];
        for(int i=0;i<k;i++){
            while(!deque.isEmpty() && deque.peekLast() < nums[i]) {
                deque.pollLast();
            }
            deque.offerLast(nums[i]);
        }
        res[0] = deque.peekFirst();

        for (int i = k; i < nums.length; i++) {
            //滑动窗口右移，队列中队头源头该被移除了
            if(deque.peekFirst() == nums[i - k]) {
                deque.removeFirst();
            }
            while(!deque.isEmpty() && deque.peekLast() < nums[i]) {
                deque.pollLast();
            }
            deque.offerLast(nums[i]);
            res[i-k+1] = deque.peekFirst();
        }
        return res;
    }

    /**
     * 二刷 2020-10-31 23:21:08
     * @param nums
     * @param k
     * @return
     */
    private int[] search2(int[] nums, int k) {
        if (k <= 1 || nums == null) {
            return nums;
        }
        int[] dp = new int[nums.length-k+1];
        int[] res = new int[nums.length-k+1];
        dp[0] = 0;
        res[0] = nums[dp[0]];
        for (int i = 1; i < k; i++) {
            if (nums[i] >= res[0]) {
                res[0] = nums[i];
                dp[0] = i;
            }
        }
        for (int i = k; i < nums.length; i++) {
            int start = i - k + 1;
            if (dp[i - k] >= start) {
                dp[i-k+1] = nums[i] >= nums[dp[i-k]] ? i : dp[i-k];
            } else {
                int max = nums[start];
                int index = start;
                for (int j = start+1; j <= i; j++) {
                    if (nums[j] >= max) {
                        max = nums[j];
                        index = j;
                    }
                }
                dp[i-k+1] = index;
            }
            res[i-k+1] = nums[dp[i-k+1]];
        }
        return res;
    }
}
