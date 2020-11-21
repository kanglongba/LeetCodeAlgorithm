package com.alibaba.edison;

import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by 钱海龙 on 2020/11/10.
 */
public class ProducerConsumerTX {

    private Random random = new Random();

    private class Egg {
        public float weight;

        public Egg(float weight) {
            this.weight = weight;
        }
    }

    private class BasketModel {
        /**
         * 简化为一个basket锁
         */
        private LinkedList<Egg> basket;
        private int LIMIT = 20;

        public BasketModel() {
            basket = new LinkedList<>();
        }

        private  void putEgg(Egg egg) {
            synchronized (basket) {
                if (basket.size() == 0) {
                    basket.offer(egg);
                    //篮子又有鸡蛋了，唤醒消费者
                    basket.notifyAll();
                } else if (basket.size() < LIMIT) {
                    basket.offer(egg);
                } else {
                    try {
                        //已经满了，阻塞生产者
                        basket.wait();
                        //被消费者唤醒后，继续放入鸡蛋
                        basket.offer(egg);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        private Egg getEgg() {
            synchronized (basket) {
                Egg egg = null;
                if (basket.size() == 0) {
                    try {
                        //篮子为空，阻塞消费者
                        basket.wait();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    //被生产者唤醒后，继续消费
                    egg = basket.poll();
                } else if (basket.size() < LIMIT) {
                    egg = basket.poll();
                } else {
                    egg = basket.poll();
                    //消费一个鸡蛋后，篮子有空位，唤醒生产者
                    basket.notifyAll();
                }
                return egg;
            }
        }
    }


    private class Producer {

        private BasketModel basket;

        public Producer(BasketModel basket) {
            this.basket = basket;
        }

        public void createEgg() {
            float weigh = random.nextFloat();
            System.out.println("creat egg:"+weigh);
            Egg egg = new Egg(weigh);
            basket.putEgg(egg);
        }
    }

    private class Consumer {

        private BasketModel basket;

        public Consumer(BasketModel basket) {
            this.basket = basket;
        }

        public void eatEgg() {
            Egg egg = basket.getEgg();
            System.out.println("eat egg:"+egg.weight);
        }
    }

    public  void run() {
        BasketModel basket = new BasketModel();
        Producer p1 = new Producer(basket);
        Producer p2 = new Producer(basket);
        Producer p3 = new Producer(basket);

        Consumer c1 = new Consumer(basket);
        Consumer c2 = new Consumer(basket);
        Consumer c3 = new Consumer(basket);
        Consumer c4 = new Consumer(basket);
        Consumer c5 = new Consumer(basket);

        ExecutorService pExecutor = Executors.newScheduledThreadPool(3);

        Thread t1 = new Thread(() -> p1.createEgg());
        Thread t2 = new Thread(() -> p2.createEgg());
        Thread t3 = new Thread(() -> p3.createEgg());

        //模拟生产者每三秒生产一个鸡蛋
        ((ScheduledExecutorService)pExecutor).scheduleAtFixedRate(t1, 1000, 3000, TimeUnit.MILLISECONDS);
        ((ScheduledExecutorService)pExecutor).scheduleAtFixedRate(t2, 1500, 3000, TimeUnit.MILLISECONDS);
        ((ScheduledExecutorService)pExecutor).scheduleAtFixedRate(t3, 2000, 3000, TimeUnit.MILLISECONDS);

        ExecutorService cExecutor = Executors.newScheduledThreadPool(5);

        Thread t4 = new Thread(() -> c1.eatEgg());
        Thread t5 = new Thread(() -> c2.eatEgg());
        Thread t6 = new Thread(() -> c3.eatEgg());
        Thread t7 = new Thread(() -> c4.eatEgg());
        Thread t8 = new Thread(() -> c5.eatEgg());

        //模拟消费者，每8秒消费一个鸡蛋
        ((ScheduledExecutorService)cExecutor).scheduleAtFixedRate(t4, 1000, 8000,TimeUnit.MILLISECONDS);
        ((ScheduledExecutorService)cExecutor).scheduleAtFixedRate(t5, 1500, 8000,TimeUnit.MILLISECONDS);
        ((ScheduledExecutorService)cExecutor).scheduleAtFixedRate(t6, 2000, 8000,TimeUnit.MILLISECONDS);
        ((ScheduledExecutorService)cExecutor).scheduleAtFixedRate(t7, 3500, 8000,TimeUnit.MILLISECONDS);
        ((ScheduledExecutorService)cExecutor).scheduleAtFixedRate(t8, 4000, 8000,TimeUnit.MILLISECONDS);
    }

    public static void main(String[] args) {
        ProducerConsumerTX tx = new ProducerConsumerTX();
        tx.run();
    }
}
