package com.alibaba.edison;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 钧戈 on 2020/9/16.
 */
public class Sword22 {

    public ListNode getKthFromEnd(ListNode head, int k) {
        //1.暴力法。计算出总长度，然后定位到倒数第k个
        return force(head, k);
    }

    //2.快慢指针方法
    private ListNode point(ListNode head, int k) {
        ListNode fast = head;
        ListNode slow = head;
        for (int i = 1; i < k; i++) {
            fast = fast.next;
        }
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

    private ListNode force(ListNode head, int k) {
        List<ListNode> listNodes = new ArrayList<>();
        ListNode temp = head;
        while (temp != null) {
            listNodes.add(temp);
            temp = temp.next;
        }
        return listNodes.get(listNodes.size()-k);
    }



    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) { val = x; }
    }
}
