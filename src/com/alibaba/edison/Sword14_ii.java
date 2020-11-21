package com.alibaba.edison;

/**
 * Created by 钧戈 on 2020/10/20.
 */
public class Sword14_ii {

    /**
     * 与Sword14_i完全一样,就是数据范围变大了。
     *
     * 这个题和剪绳子I一样的描述，就是数据范围变大了。剪绳子可以用动态规划或者贪心做，这道题对于使用DP难度就增大了一些，因为数据范围变得比较大时，long已经不足以去存储中间结果的状态，但是由于DP
     * 做法是枚举各种剪的情况然后取最大值，因此只能通过使用BigInteger的方法去做，这点评论区已经有人给出了解答。
     *
     * 那么这个题范围变大的本意是想让我们使用贪心算法能更好的求解(毕竟BigInteger使用起来麻烦，贪心没有数据溢出的问题，它是找当下的最优解，不需要比较，中间结果可以直接取模)。
     *
     *
     * @param n
     * @return
     */
    public int cuttingRope(int n) {
        return math(n);
    }

    //数学分析
    private int math(int n) {
        if (n <= 3) { return n - 1; }
        int b = n % 3, p = 1000000007;
        long rem = 1, x = 3;
        for (int a = n / 3 - 1; a > 0; a /= 2) {
            if (a % 2 == 1) { rem = (rem * x) % p; }
            x = (x * x) % p;
        }
        if (b == 0) { return (int)(rem * 3 % p); }
        if (b == 1) { return (int)(rem * 4 % p); }
        return (int)(rem * 6 % p);
    }

    //动态规划
    private int dp(int n) {
        int[] dp = new int[n + 1];
        for (int i = 2; i <= n; i++) {
            int curMax = 0;
            for (int j = 1; j < i; j++) {
                //j表示把长度为i的绳子，从j处再切一刀
                curMax = Math.max(curMax, Math.max(j * (i - j), j * dp[i - j]));
            }
            dp[i] = curMax;
        }
        return dp[n];
    }

    //贪心
    //这个解释的非常好
    //https://leetcode-cn.com/problems/jian-sheng-zi-ii-lcof/solution/javatan-xin-si-lu-jiang-jie-by-henrylee4/
    private int greedy(int n) {
        if(n == 2) {
            return 1;
        }
        if(n == 3){
            return 2;
        }
        int mod = (int)1e9 + 7;
        long res = 1;
        while(n > 4) {
            res *= 3;
            res %= mod;
            n -= 3;
        }
        return (int)(res * n % mod);
    }

}
