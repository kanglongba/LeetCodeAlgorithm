package com.alibaba.edison;

/**
 * 公用的链表节点
 * author: qonyqian
 * created on: 2022/1/31 6:05 下午
 * version：1.0
 * description:
 */
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int x) {
        val = x;
        next = null;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
