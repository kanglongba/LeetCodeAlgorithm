package com.alibaba.edison;

/**
 * 两数相加，medium
 * 给你两个非空 的链表，表示两个非负的整数。它们每位数字都是按照逆序的方式存储的，并且每个节点只能存储一位数字。
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 * 你可以假设除了数字 0 之外，这两个数都不会以 0开头。
 *
 * 输入：l1 = [2,4,3], l2 = [5,6,4]
 * 输出：[7,0,8]
 * 解释：342 + 465 = 807.
 *
 * 未能独立得出最优解
 *
 * author: qonyqian
 * created on: 2022/1/17 10:47 下午
 * version：1.0
 * description:
 */
public class LeetCode2 {

    /**
     * 朴素的算法，在遍历的过程中模拟数字相加的规则，只需注意处理边界条件。
     * 仔细审题，这道题可以直接遍历解决
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode();
        ListNode curr = head;
        int step = 0; //进位
        while (l1 != null || l2 != null) {
            if (l1 == null) {
                int sum = l2.val + step;
                if (sum < 10) {
                    l2.val = sum;
                    curr.next = l2;
                    step = 0;
                    break;
                } else {
                    ListNode node = new ListNode(sum - 10);
                    step = 1;
                    curr.next = node;
                    curr = node;
                    l2 = l2.next;
                }
            } else if (l2 == null) {
                int sum = l1.val + step;
                if (sum < 10) {
                    l1.val = sum;
                    curr.next = l1;
                    step = 0;
                    break;
                } else {
                    ListNode node = new ListNode(sum - 10);
                    step = 1;
                    curr.next = node;
                    curr = node;
                    l1 = l1.next;
                }
            } else {
                int sum = l1.val + l2.val + step;
                if (sum < 10) {
                    ListNode node = new ListNode(sum);
                    curr.next = node;
                    curr = node;
                    step = 0;
                    l1 = l1.next;
                    l2 = l2.next;
                } else {
                    ListNode node = new ListNode(sum - 10);
                    step = 1;
                    curr.next = node;
                    curr = node;
                    l1 = l1.next;
                    l2 = l2.next;
                }
            }
        }
        if (step == 1) { //把进位挂上，千万别忘
            curr.next = new ListNode(1);
        }
        return head.next;
    }

    /**
     * 笨办法，把链表转化为数字，利用数相加求出和，在把数字转化为链表。
     * 当数字过大时，会超出long的限制
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode stupid(ListNode l1, ListNode l2) {
        StringBuilder builder = new StringBuilder();
        while (l1 != null) {
            builder.insert(0, l1.val);
            l1 = l1.next;
        }
        long value1 = Long.parseLong(builder.toString());
        StringBuilder builder2 = new StringBuilder();
        while (l2 != null) {
            builder2.insert(0, l2.val);
            l2 = l2.next;
        }
        long value2 = Long.parseLong(builder2.toString());
        long sum = Long.sum(value1, value2);
        String sumStr = String.valueOf(sum);
        ListNode head = new ListNode();
        ListNode curr = head;
        for (int i = sumStr.length() - 1; i >= 0; i--) {
            curr.next = new ListNode(Integer.parseInt(String.valueOf(sumStr.charAt(i))));
            curr = curr.next;
        }
        return head.next;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
