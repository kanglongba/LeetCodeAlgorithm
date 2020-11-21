package com.alibaba.edison;

/**
 * Created by 钧戈 on 2020/9/19.
 */
public class Sword24 {

    public ListNode reverseList(ListNode head) {
        return point(head);
    }

    /**
     * 双指针
     *
     * @param head
     * @return
     */
    private ListNode point(ListNode head) {
        ListNode pre = head;
        ListNode cur = null;
        ListNode temp;
        while (pre != null) {
            temp = pre.next;
            pre.next = cur;
            cur = pre;
            pre = temp;
        }
        return cur;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) { val = x; }
    }
}
