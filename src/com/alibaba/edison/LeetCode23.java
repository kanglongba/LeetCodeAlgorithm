package com.alibaba.edison;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 合并K个升序链表，hard
 * 给你一个链表数组，每个链表都已经按升序排列。
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 *
 * 输入：lists = [[1,4,5],[1,3,4],[2,6]]
 * 输出：[1,1,2,3,4,4,5,6]
 *
 * author: qonyqian
 * created on: 2022/3/12 4:25 下午
 * version：1.0
 * description:
 */
public class LeetCode23 {

    /**
     * 两两合并，但是性能不太高
     * 执行用时：99 ms, 在所有 Java 提交中击败了24.08%的用户
     * 内存消耗：42.9 MB, 在所有 Java 提交中击败了46.66%的用户
     *
     * 官方还有一个分治合并：https://leetcode-cn.com/problems/merge-k-sorted-lists/solution/he-bing-kge-pai-xu-lian-biao-by-leetcode-solutio-2/
     * 合并思路和我一样，只不过我是顺序合并，官方使用归并的思想
     *
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        int length = lists.length;
        if (length == 1) {
            return lists[0];
        } else if (length == 0) {
            return null;
        }
        int index = 1;
        ListNode one = lists[0];
        while (index < length) {
            ListNode two = lists[index];
            one = merge(one, two);
            index++;
        }

        return one;
    }

    public ListNode merge(ListNode one, ListNode two) {
        ListNode pre = new ListNode(0);
        ListNode current = pre;
        while (one != null && two != null) {
            if (one.val < two.val) {
                current.next = one;
                one = one.next;
            } else {
                current.next = two;
                two = two.next;
            }
            current = current.next;
        }
        if (one != null) {
            current.next = one;
        } else if (two != null) {
            current.next = two;
        }
        return pre.next;
    }

    /**
     * 还有一个思路，使用优先队列对节点排序，然后再把节点串起来
     * @param lists
     * @return
     */
    public ListNode mergeKLists2(ListNode[] lists) {
        int length = lists.length;
        if (length == 1) {
            return lists[0];
        } else if (length == 0) {
            return null;
        }
        PriorityQueue<ListNode> queue = new PriorityQueue<>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val - o2.val;
            }
        });
        for (int i = 0; i < length; i++) {
            ListNode node = lists[i];
            while (node != null) {
                queue.offer(node);
                node = node.next;
            }
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
}
