package com.alibaba.edison;

/**
 * Created by 钧戈 on 2020/10/30.
 */
public class Sword52 {

    //双指针法太巧妙了。这道题不看题解，根本想不来。
    //双指针法的思路：假设公共部分是C，链表1的独立部分是A，链表2的独立部分是B，两个指针P1和P2，分别从链表1和链表2出         //发，那么P1走完A+C+B的时间，与P2走完B+C+A的时间一定相同。
    //下面是自己写的代码，题解的代码更精炼
    //2020-10-30，实在是精妙，这就是速度一样，靠路程计量时间
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode A = headA;
        ListNode B = headB;
        while (A != null || B != null) {
            if (A == B) {
                break;
            }
            if (A == null) {
                A = headB;
            } else {
                A = A.next;
            }
            if (B == null) {
                B = headA;
            } else {
                B = B.next;
            }
        }
        return A;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
