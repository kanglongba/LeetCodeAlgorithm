package com.alibaba.edison;

/**
 * Created by 钧戈 on 2020/10/31.
 */
public class Sword18 {

    public ListNode deleteNode(ListNode head, int val){
        return delete(head, val);
    }

    public ListNode delete1(ListNode head, int val) {
        //这道题的关键是引入一个伪头结点
        ListNode temp = head;
        ListNode pre = new ListNode(0);
        pre.next = temp;
        ListNode res = pre;
        while(temp != null) {
            if(temp.val == val) {
                pre.next = temp.next;
                temp.next = null;
                break;
            } else {
                pre = temp;
                temp = temp.next;
            }
        }
        return res.next;
    }

    /**
     * 二刷：2020-10-31
     * 双指针+伪头结点
     * @param head
     * @param val
     * @return
     */
    public ListNode delete(ListNode head, int val) {
        ListNode node = new ListNode(0);
        node.next = head;
        ListNode pre = node;
        while (head != null) {
            if (head.val == val) {
                pre.next = head.next;
                head = pre.next;
            } else {
                head = head.next;
                pre = pre.next;
            }
        }
        return node.next;
    }

    public class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
  }
}
