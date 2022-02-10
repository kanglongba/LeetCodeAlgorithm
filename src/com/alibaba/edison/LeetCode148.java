package com.alibaba.edison;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 排序链表，medium
 * 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
 * 进阶：你可以在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？
 * <p>
 * 未能想到最优解
 * <p>
 * author: qonyqian
 * created on: 2022/2/10 8:33 下午
 * version：1.0
 * description:
 */
public class LeetCode148 {

    /**
     * 普通解：先排序，再
     *
     * @param head
     * @return
     */
    public ListNode sortList(ListNode head) {
        if (head == null) {
            return null;
        }
        PriorityQueue<ListNode> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.val));
        while (head != null) {
            queue.offer(head);
            head = head.next;
        }
        ListNode pre = new ListNode(0);
        ListNode current = pre;
        while (!queue.isEmpty()) {
            current.next = queue.poll();
            current = current.next;
        }
        current.next = null;
        return pre.next;
    }

    /**
     * 插入排序，边遍历，边把节点插入正确的位置
     * 常数空间复杂度。
     * 这个算法无法通过：[-1,5,3,4,0]
     *
     * @param head
     * @return
     */
    public ListNode youhua(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode pre = new ListNode(0);
        pre.next = head;
        ListNode preCurrent = head; //第一个元素初始状态不用排序
        ListNode current = head.next; //第一个需要排序的元素
        while (current != null) {
            preCurrent.next = current.next;
            current.next = null;
            ListNode temp = current;
            current = preCurrent.next;
            sortList(pre, preCurrent, temp);
        }
        return pre.next;
    }

    public void sortList(ListNode left, ListNode right, ListNode sort) {
        if (sort.val >= right.val) {
            ListNode temp = right.next;
            right.next = sort;
            sort.next = temp;
            right = sort;
        } else {
            ListNode pre = left;
            ListNode current = left.next;
            while (current != right.next) {
                if (sort.val <= current.val) {
                    pre.next = sort;
                    sort.next = current;
                    break;
                } else {
                    pre = current;
                    current = current.next;
                }
            }
        }
    }

    /**
     * 官方代码：https://leetcode-cn.com/problems/sort-list/solution/pai-xu-lian-biao-by-leetcode-solution/
     * 自顶向下归并排序
     *
     * 归并排序基于分治算法。最容易想到的实现方式是自顶向下的递归实现，考虑到递归调用的栈空间，自顶向下归并排序的空间复杂
     * 度是 O(log n)。
     *
     * @param head
     * @return
     */
    public ListNode sortList1(ListNode head) {
        return sortList(head, null);
    }

    public ListNode sortList(ListNode head, ListNode tail) {
        if (head == null) {
            return head;
        }
        if (head.next == tail) {
            head.next = null;
            return head;
        }
        ListNode slow = head, fast = head;
        while (fast != tail) {
            slow = slow.next;
            fast = fast.next;
            if (fast != tail) {
                fast = fast.next;
            }
        }
        ListNode mid = slow;
        ListNode list1 = sortList(head, mid);
        ListNode list2 = sortList(mid, tail);
        ListNode sorted = merge(list1, list2);
        return sorted;
    }

    public ListNode merge(ListNode head1, ListNode head2) {
        ListNode dummyHead = new ListNode(0);
        ListNode temp = dummyHead, temp1 = head1, temp2 = head2;
        while (temp1 != null && temp2 != null) {
            if (temp1.val <= temp2.val) {
                temp.next = temp1;
                temp1 = temp1.next;
            } else {
                temp.next = temp2;
                temp2 = temp2.next;
            }
            temp = temp.next;
        }
        if (temp1 != null) {
            temp.next = temp1;
        } else if (temp2 != null) {
            temp.next = temp2;
        }
        return dummyHead.next;
    }


    /**
     * 官方代码：https://leetcode-cn.com/problems/sort-list/solution/pai-xu-lian-biao-by-leetcode-solution/
     * 自底向上归并排序
     *
     * 如果要达到 O(1) 的空间复杂度，则需要使用自底向上的实现方式。
     *
     * @param head
     * @return
     */
    public ListNode sortList2(ListNode head) {
        if (head == null) {
            return head;
        }
        int length = 0;
        ListNode node = head;
        while (node != null) {
            length++;
            node = node.next;
        }
        ListNode dummyHead = new ListNode(0, head);
        for (int subLength = 1; subLength < length; subLength <<= 1) {
            ListNode prev = dummyHead, curr = dummyHead.next;
            while (curr != null) {
                ListNode head1 = curr;
                for (int i = 1; i < subLength && curr.next != null; i++) {
                    curr = curr.next;
                }
                ListNode head2 = curr.next;
                curr.next = null;
                curr = head2;
                for (int i = 1; i < subLength && curr != null && curr.next != null; i++) {
                    curr = curr.next;
                }
                ListNode next = null;
                if (curr != null) {
                    next = curr.next;
                    curr.next = null;
                }
                ListNode merged = merge2(head1, head2);
                prev.next = merged;
                while (prev.next != null) {
                    prev = prev.next;
                }
                curr = next;
            }
        }
        return dummyHead.next;
    }

    public ListNode merge2(ListNode head1, ListNode head2) {
        ListNode dummyHead = new ListNode(0);
        ListNode temp = dummyHead, temp1 = head1, temp2 = head2;
        while (temp1 != null && temp2 != null) {
            if (temp1.val <= temp2.val) {
                temp.next = temp1;
                temp1 = temp1.next;
            } else {
                temp.next = temp2;
                temp2 = temp2.next;
            }
            temp = temp.next;
        }
        if (temp1 != null) {
            temp.next = temp1;
        } else if (temp2 != null) {
            temp.next = temp2;
        }
        return dummyHead.next;
    }

}
