package com.alibaba.edison;

/**
 * 反转链表，easy
 * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
 *
 * 经典的算法八股
 * author: qonyqian
 * created on: 2022/2/1 10:18 下午
 * version：1.0
 * description:
 */
public class LeetCode206 {

    /**
     * 双指针法，一前一后，边遍历，边翻转
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode pre = head;
        ListNode curr = head.next;
        pre.next = null;
        ListNode temp = null;
        while (curr != null) {
            temp = curr.next;
            curr.next = pre;
            pre = curr;
            curr = temp;
        }
        return pre;
    }
}
