package com.alibaba.edison;

/**
 * 环形链表，easy
 * 给你一个链表的头节点 head ，判断链表中是否有环。
 * author: qonyqian
 * created on: 2022/1/31 6:05 下午
 * version：1.0
 * description:
 */
public class LeetCode141 {

    /**
     * 典型的算法八股
     * 快慢指针，快指针一次走两步，慢指针一次走一步，如果存在环，快指针一定会追上慢指针
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head.next;
        ListNode fast = head.next.next;
        while (slow != null && fast != null) {
            if (slow == fast) {
                return true;
            }
            slow = slow.next;
            fast = fast.next == null ? null : fast.next.next;
        }
        return false;
    }
}
