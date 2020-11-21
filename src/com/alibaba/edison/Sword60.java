package com.alibaba.edison;

import java.util.Arrays;

/**
 * Created by 钧戈 on 2020/10/1.
 */
public class Sword60 {

    public static void main(String[] args) {
        Sword60 sword60 = new Sword60();
        System.out.println("args = [" + sword60.twoSum(1) + "]");
    }

    public double[] twoSum(int n) {
        return dp1(n);
    }

    //真没想到这道题竟然要用动态规划，而且抽象起来这么复杂，直接扔给我，我是写不出来的。

    /**
     * 状态：dp[n][k]表示n个色子，掷出点数为k的次数 状态转移方程：dp[n][k] =
     * dp[n-1][k-1]+dp[n-1][k-2]+dp[n-1][k-3]+dp[n-1][k-4]+dp[n-1][k-5]+dp[n-1][k-6]
     *
     * @param n
     * @return
     */
    private double[] dp1(int n) {
        int[][] dp = new int[n + 1][n * 6 + 1];
        for (int i = 1; i <= 6; i++) {
            dp[1][i] = 1;
        }
        for (int i = 2; i <= n; i++) {
            for (int j = i; j <= 6 * i; j++) {
                for (int k = 1; k <= 6; k++) {
                    if (j > k) {
                        dp[i][j] += dp[i - 1][j - k];
                    }
                }
            }
        }
        double count = Math.pow(6, n);
        double[] res = new double[6 * n + 1];
        for (int i = n; i <= 6 * n; i++) {
            res[i] = dp[n][i] / count;
        }
        return Arrays.copyOfRange(res,1,res.length);

    }

}
