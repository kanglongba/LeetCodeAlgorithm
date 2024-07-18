package com.alibaba.edison;

/**
 * 相交链表，easy
 * 典型的算法八股
 * 给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表不存在相交节点，返回 null 。
 *
 * 字节考过。
 *
 * author: qonyqian
 * created on: 2022/2/1 9:36 下午
 * version：1.0
 * description:
 */
public class LeetCode160 {

    /**
     * 两个指针遍历，遍历一遍以后，交换路径再次遍历，如果有交点，那么两个指针一定会相遇，并且相遇的点就是交点
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode scanA = headA;
        ListNode scanB = headB;
        while (scanA != null || scanB != null) {
            if (scanA == scanB) {
                return scanA;
            }
            if (scanA == null) {
                scanA = headB;
            } else {
                scanA = scanA.next;
            }
            if (scanB == null) {
                scanB = headA;
            } else {
                scanB = scanB.next;
            }
        }
        return null;
    }
}
