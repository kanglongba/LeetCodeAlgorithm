package com.alibaba.edison;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by 钧戈 on 2020/10/10.
 */
public class Sword49 {

    /**
     * 求丑数，典型的数学问题，一定要分析丑数的数学规律。
     *
     * @param n
     * @return
     */
    public int nthUglyNumber(int n) {
        return dp(n);
    }

    /**
     * 动态规划
     *
     * @param n
     * @return
     */
    private int dp(int n) {
        int a = 0, b = 0, c = 0;
        int[] dp = new int[n];
        dp[0] = 1;
        for (int i = 1; i < n; i++) {
            int n2 = dp[a] * 2, n3 = dp[b] * 3, n5 = dp[c] * 5;
            dp[i] = Math.min(Math.min(n2, n3), n5);
            if (dp[i] == n2) { a++; }
            if (dp[i] == n3) { b++; }
            if (dp[i] == n5) { c++; }
        }
        return dp[n - 1];
    }

    private int[] uglyNumber = {2, 3, 5};

    /**
     * 小根堆
     *
     * @param n
     * @return
     */
    private int heap(int n) {
        //创建小根堆，每次出堆的都是最小值
        Queue<Long> queue = new PriorityQueue<>();
        queue.add(1L);
        //记录出堆的个数，出堆的元素完全按照从小到大排序
        int count = 0;
        while (!queue.isEmpty()) {
            long cut = queue.poll();

            //如果出堆的个数>=n,当前cut就是第n个丑数
            if (++count >= n) {
                return (int)cut;
            }
            for (int num : uglyNumber) {
                //排除重复的数字
                if (!queue.contains(num * cut)) {
                    queue.add(num * cut);
                }
            }
        }
        return -1;
    }
}
