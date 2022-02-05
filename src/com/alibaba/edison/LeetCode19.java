package com.alibaba.edison;

/**
 * 删除链表的倒数第 N 个结点，medium
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 * <p>
 * 典型的算法八股
 * <p>
 * author: qonyqian
 * created on: 2022/2/5 9:01 下午
 * version：1.0
 * description:
 */
public class LeetCode19 {

    /**
     * 双指针，一个先走n步，然后再两个同步前进，当先走的到达终点，后走的那个就指向第n个节点。
     *
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        //设置一个哨兵，方便处理边界问题
        ListNode pre = new ListNode(0);
        pre.next = head;
        ListNode left = pre; //left从哨兵开始，当right到达终点后，left正好指向第n-1个节点，方便执行删除操作。
        ListNode right = head; //right从起点开始

        for (int i = 0; i < n - 1; i++) { //从起点开始，走n-1步到达第n个节点；如果从哨兵开始，走n步到达第n个节点
            right = right.next;
        }
        while (right.next != null) { //right到达终点
            //同步移动双指针
            right = right.next;
            left = left.next;
        }
        //right到达终点，left到达第n-1个节点，删除第n个节点
        ListNode temp = left.next;
        left.next = temp.next;
        temp.next = null;
        return pre.next;
    }
}
