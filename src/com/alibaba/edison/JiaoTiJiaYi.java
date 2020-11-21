package com.alibaba.edison;

/**
 * 网易
 * https://blog.csdn.net/tanzhen1991910/article/details/53065275
 * 两个线程交替加一
 * Created by 钧戈 on 2020/9/15.
 */
public class JiaoTiJiaYi implements Runnable {
    int i = 1;

    public static void main(String[] args) {
        JiaoTiJiaYi t = new JiaoTiJiaYi();
        Thread t1 = new Thread(t);
        Thread t2 = new Thread(t);

        t1.setName("线程1");
        t2.setName("线程2");

        t1.start();
        t2.start();
    }

    @Override
    public void run() {
        while (true) {
            synchronized (this) {
                // 先唤醒另外一个线程
                notify();
                if (i <= 100) {
                    System.out.println(Thread.currentThread().getName() + ":" + i);
                    i++;
                    try {
                        // 打印完之后，释放资源，等待下次被唤醒
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}
