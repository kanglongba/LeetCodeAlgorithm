package com.alibaba.edison;

/**
 * Created by 钧戈 on 2020/10/17.
 */
public class Sword14_i {

    public int cuttingRope(int n) {
        return dp2(n);
    }

    /**
     * 数学推导解法
     *
     * @param n
     * @return
     */
    private int math(int n) {
        if (n <= 3) { return n - 1; }
        int a = n / 3, b = n % 3;
        if (b == 0) { return (int)Math.pow(3, a); }
        if (b == 1) { return (int)Math.pow(3, a - 1) * 4; }
        return (int)Math.pow(3, a) * 2;
    }

    /**
     * 看题解之前，我都不知道可以用动态规划
     * 动态规划解法 dp[i]代表长度为i的绳子所构成乘积的最大值。
     * https://leetcode-cn.com/problems/jian-sheng-zi-lcof/solution/xiang-jie-bao-li-di-gui-ji-yi-hua-ji-zhu-dong-tai-/
     *
     * @param n
     * @return
     */
    private int dp(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                dp[i] = Math.max(dp[i], Math.max(j, dp[j]) * (i - j));
            }
        }
        return dp[n];
    }

    /**
     * https://leetcode-cn.com/problems/jian-sheng-zi-lcof/solution/dong-tai-gui-hua-shu-xue-by-sophia_fez/
     * 动态转移方程缺少证明，理解不了
     * 解释：因为dp[j]*dp[i-j]已经包含在j*dp[i-j]中，dp[j]的意思是将j拆成至少两个更小的正整数之和，这些正整数的最大乘积，如果i拆成了m个数之和，那一定能将这m个数分成1个数和其余的m-1个数，只需要遍历那1个数的所有可能取值即可，其余的m-1个数的最大乘积显然之前已经计算过了。如果考虑dp[j]*dp[i-j]，就是不必要地重复考虑了之前已经计算过的情况。
     * @param n
     * @return
     */
    private int dp2(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                dp[i]= Math.max(dp[i], Math.max(j * (i - j), j * dp[i - j]));
            }
        }
        return dp[n];
    }
}

