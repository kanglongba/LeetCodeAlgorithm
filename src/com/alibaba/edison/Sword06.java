package com.alibaba.edison;

import java.util.Stack;

/**
 * Created by 钧戈 on 2020/9/19.
 */
public class Sword06 {
    public int[] reversePrint(ListNode head) {
        return stack(head);
    }

    /**
     * 都要借助额外的数据结构
     * @param head
     * @return
     */
    private int[] stack(ListNode head) {
        Stack<ListNode> stack = new Stack<>();
        while (head != null) {
            stack.push(head);
            head = head.next;
        }
        int size = stack.size();
        int[] result = new int[size];
        for (int i = 0; i < size; i++) {
            result[i] = stack.pop().val;
        }
        return result;
    }

    public class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
    }
}
