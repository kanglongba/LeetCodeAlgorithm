package com.alibaba.edison;

/**
 * Created by 钧戈 on 2020/10/4.
 */
public class Sword10_ii {

    public int numWays(int n) {
        return dp(n);
    }

    /**
     * 经典的动态规划问题。一维动态问题。
     * @param n
     * @return
     */
    private int dp(int n){
        //f(n) = f(n-1)+f(n-2);
        int[] dp = new int[n+3];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[n] = dp[n-1] + dp[n-2];
        }
        return dp[n];
    }
}
