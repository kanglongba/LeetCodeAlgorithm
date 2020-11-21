package com.alibaba.edison;

import java.util.stream.IntStream;

/**
 * Created by 钧戈 on 2020/10/4.
 */
public class Sword64 {
    public int sumNums(int n) {
        //这道题牛逼了，我连暴力法都不知道该怎么写。
        //等等，我突然想到可以用lambda表达式
        return lambda(n);
    }

    private int lambda(int n) {
        return IntStream.range(1, n+1).sum();
    }

    /**
     * 二刷 2020-11-01 18:10:04
     * @param n
     * @return
     */
    private int ershua(int n) {
        if (n > 0) {
            return n + ershua(n-1);
        } else {
            return 0;
        }
    }
}
