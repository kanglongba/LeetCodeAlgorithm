package com.alibaba.edison;

import java.util.concurrent.Semaphore;

/**
 * 网易
 * https://blog.csdn.net/tanzhen1991910/article/details/53065275
 * 两个线程交替加一
 * <p>
 * 同 LeetCode1115
 * <p>
 * Created by 钧戈 on 2020/9/15.
 */
public class JiaoTiJiaYi {


    public static void main(String[] args) {
//        switchAddOne();
        switchPrintOaaEven();
    }

    /**
     * 使用锁 + 线程阻塞 实现交替加一
     * 线程阻塞用到了 notify - wait。
     * 这样写的关键就是，runnable 对象是同一个，synchronized 的对象锁是同一个， notify - wait 唤醒/阻塞的对象也是同一个。
     * 只不过是用了两个线程来执行这个对象而已。
     */
    public static void switchAddOne() {
        MyPlus myPlus = new MyPlus();
        Thread t1 = new Thread(myPlus);
        Thread t2 = new Thread(myPlus);

        t1.setName("线程1");
        t2.setName("线程2");

        t1.start();
        t2.start();
    }

    public static class MyPlus implements Runnable {

        int i = 1;

        @Override
        public void run() {
            while (i <= 100) {
                synchronized (this) { //获取对象锁，进入临界区
                    // 唤醒另一个线程。但由于另一个线程无法获得锁，还是不能进入临界区
                    notify();
                    System.out.println(Thread.currentThread().getName() + ":" + i);
                    i++;
                    try {
                        // 阻塞当前线程，释放当前线程占据的锁
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


    /**
     * 使用信号量实现 交替打印奇偶数。
     * Semaphore底层用的也是AQS
     * 信号量参考：https://yiyeli2020.github.io/2020/05/22/1115.%20%E4%BA%A4%E6%9B%BF%E6%89%93%E5%8D%B0FooBar/
     */
    public static void switchPrintOaaEven() {
        MyAdd myAdd = new MyAdd(100);
        Thread odd = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    myAdd.printOdd();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        odd.setName("线程1");
        Thread even = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    myAdd.printEven();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        even.setName("线程2");
        odd.start();
        even.start();
    }


    public static class MyAdd {

        Semaphore odd = new Semaphore(1, true);
        Semaphore even = new Semaphore(0, true);

        int count;

        public MyAdd(int count) {
            this.count = count;
        }

        /**
         * 打印偶数，从 0 开始
         */
        public void printOdd() throws InterruptedException {
            for (int i = 0; i <= count; i +=2) {
                odd.acquire();
                System.out.println(Thread.currentThread().getName() + ":" + i);
                even.release();
            }
        }

        /**
         * 打印奇数，从 1 开始
         */
        public void printEven() throws InterruptedException {
            for (int i = 1; i <= count; i +=2) {
                even.acquire();
                System.out.println(Thread.currentThread().getName() + ":" + i);
                odd.release();
            }
        }
    }

}
