package com.alibaba.edison;

import java.util.LinkedList;

/**
 * hard，K 个一组翻转链表
 * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 * k 是一个正整数，它的值小于或等于链表的长度。
 * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 *
 * 进阶：
 * 你可以设计一个只使用常数额外空间的算法来解决此问题吗？
 * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 *
 * 输入：head = [1,2,3,4,5], k = 2
 * 输出：[2,1,4,3,5]
 *
 *
 * author: qonyqian
 * created on: 2022/3/7 9:39 下午
 * version：1.0
 * description:
 */
public class LeetCode25 {

    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null) {
            return head;
        }
        LinkedList<ListNode> stack = new LinkedList<>();
        ListNode pre = new ListNode(0);
        pre.next = head;
        ListNode current = pre;
        ListNode next = head;
        while (head != null) {
            int i = 1;
            while (i <= k && head != null) {
                next = head.next;
                head.next = null;
                stack.push(head);
                head = next;
                i++;
            }
            while (!stack.isEmpty()) {
                ListNode node;
                if (i > k) {
                    node = stack.pop();
                } else {
                    node = stack.pollLast();
                }
                current.next = node;
                current = node;
            }
        }
        return pre.next;
    }

}
