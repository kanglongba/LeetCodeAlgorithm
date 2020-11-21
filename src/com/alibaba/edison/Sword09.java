package com.alibaba.edison;

import java.util.Stack;

/**
 * Created by 钧戈 on 2020/9/19.
 */
public class Sword09 {

    class CQueue {
        Stack<Integer> push;
        Stack<Integer> pop;

        public CQueue() {
            push = new Stack<>();
            pop = new Stack<>();
        }

        public void appendTail(int value) {
            push.push(value);
        }

        public int deleteHead() {
            if (pop.isEmpty()) {
                while (!push.isEmpty()) {
                    pop.push(push.pop());
                }
            }
            if (pop.isEmpty()) {
                return -1;
            } else {
                return pop.pop();
            }
        }
    }

}
