package com.alibaba.edison;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * created on: 2024/9/9 14:40
 * version：1.0
 * description:
 */
public class ProduceConsume {

    // 生产者 生产数据

    // 消费者：消费数据，无数据时，阻塞

    private static final BlockingDeque<Integer> queue = new LinkedBlockingDeque<>();

    @SuppressWarnings("all")
    public static void produce() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
//                    Thread.sleep(1000);
                    for (int i = 0; i < 20; i++) {
                        queue.put(i);
                        Thread.sleep(1000);
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }

    @SuppressWarnings("all")
    public static void consume() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (; ;) {
                    System.out.println("consume run");
                    try {
                        int value = queue.take();
                        System.out.println(value);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }).start();
    }

    public static void main(String[] args) {
        produce();
        consume();
    }
}
