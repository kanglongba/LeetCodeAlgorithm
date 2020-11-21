package com.alibaba.edison;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by 钧戈 on 2020/10/18.
 */
public class Sword59 {

    /**
     * 双端队列+辅助栈
     */
    class MaxQueue {

        //助攻
        Deque<Integer> deque = new LinkedList<>();
        //主攻
        Queue<Integer> queue = new LinkedList<>();

        public MaxQueue() {

        }

        public int max_value() {
            if (deque.isEmpty()) {
                return -1;
            }
            return deque.peekFirst();
        }

        public void push_back(int value) {
            while (!deque.isEmpty() && deque.peekLast() < value) {
                deque.pollLast();
            }
            deque.offerLast(value);
            queue.offer(value);
        }

        public int pop_front() {
            if (queue.isEmpty()) {
                return -1;
            }
            int value = queue.poll();
            if (value == deque.peekFirst()) {
                deque.pollFirst();
            }
            return value;
        }
    }
}
