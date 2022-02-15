package com.alibaba.edison;

import java.util.concurrent.Semaphore;

/**
 * 交替打印 FooBar，medium
 * 参考文章：https://yiyeli2020.github.io/2020/05/22/1115.%20%E4%BA%A4%E6%9B%BF%E6%89%93%E5%8D%B0FooBar/
 * 大神题解：https://leetcode-cn.com/problems/print-foobar-alternately/solution/chang-you-duo-xian-cheng-zhi-1115-by-a-f-mf5u/
 * 大神题解：https://leetcode-cn.com/problems/print-foobar-alternately/solution/duo-xian-cheng-liu-mai-shen-jian-ni-xue-d220n/
 *
 * author: qonyqian
 * created on: 2022/2/15 8:48 下午
 * version：1.0
 * description:
 */
public class LeetCode1115 {

    Semaphore foo = new Semaphore(1);
    Semaphore bar = new Semaphore(0);

    private int n;

    public LeetCode1115(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            foo.acquire();
            // printFoo.run() outputs "foo". Do not change or remove this line.
            printFoo.run();
            bar.release();
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            bar.acquire();
            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();
            foo.release();
        }
    }
}
