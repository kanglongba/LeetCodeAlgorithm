package com.alibaba.edison;

/**
 * Created by 钧戈 on 2020/10/19.
 */
public class Sword44 {

    public int findNthDigit(int n) {
        return math(n);
    }

    /**
     * 第一感觉就应该是数学分析法
     * 本题纯粹找规律，没什么价值
     * @param n
     * @return
     */
    private int math(int n) {
        int digit = 1;
        long start = 1;
        long count = 9;
        while (n > count) { // 1.
            n -= count;
            digit += 1;
            start *= 10;
            count = digit * start * 9;
        }
        long num = start + (n - 1) / digit; // 2.
        return Long.toString(num).charAt((n - 1) % digit) - '0'; // 3.
    }
}
