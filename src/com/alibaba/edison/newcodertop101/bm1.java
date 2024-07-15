package com.alibaba.edison.newcodertop101;

import com.alibaba.edison.ListNode;

/**
 * 牛客top101：https://uploadfiles.nowcoder.com/bm/top101.html
 * 反转链表，easy
 * author: qonyqian
 * created on: 2022/8/31 16:25
 * version：1.0
 * description:
 */
public class bm1 {

    public ListNode ReverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode soldier = new ListNode(0);
        soldier.next = head;
        ListNode pre  = soldier;
        while (head.next != null) {
            ListNode next = head.next;
            


        }

        return soldier.next;
    }
}
