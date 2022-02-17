package com.alibaba.edison;

import java.util.HashSet;
import java.util.Set;

/**
 * 环形链表 II，medium
 * 给定一个链表，返回链表开始入环的第一个节点。如果链表无环，则返回null。
 * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表
 * 尾连接到链表中的位置（索引从 0 开始）。如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
 * 不允许修改 链表。
 *
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：返回索引为 1 的链表节点
 * 解释：链表中有一个环，其尾部连接到第二个节点。
 * <p>
 * author: qonyqian
 * created on: 2022/2/10 12:26 上午
 * version：1.0
 * description:
 */
public class LeetCode142 {


    /**
     * 1.不在乎空间复杂度，可以使用辅助集合
     *
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {
        if (head == null) {
            return head;
        }
        Set<ListNode> set = new HashSet<>();
        ListNode current = head;
        while (current != null) {
            if (set.isEmpty()) {
                set.add(current);
            } else {
                if (set.contains(current)) {
                    return current;
                } else {
                    set.add(current);
                }
            }
            current = current.next;
        }
        return null;
    }

    /**
     * 1.要求空间复杂度是O(1)
     * 太难了，这里面竟然还有一个数学分析的过程：
     * 1.https://leetcode-cn.com/problems/linked-list-cycle-ii/solution/linked-list-cycle-ii-kuai-man-zhi-zhen-shuang-zhi-/
     * 2.https://leetcode-cn.com/problems/linked-list-cycle-ii/solution/huan-xing-lian-biao-ii-by-leetcode-solution/
     * <p>
     * 记住规律就行了：
     * 1.用快慢指针法，找到快指针追上慢指针的节点
     * 2.分别从头结点和相遇节点出发，向前走，他们相遇的那个节点就是入环的节点
     *
     * @param head
     * @return
     */
    public ListNode detectCycle1(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (slow != null && fast != null) {
            slow = slow.next;
            fast = fast.next == null ? null : fast.next.next;
            if (slow == fast) {
                break;
            }
        }
        if (fast == null) {
            return null;
        }
        ListNode cursor = head;
        while (cursor != slow) {
            cursor = cursor.next;
            slow = slow.next;
        }
        return cursor;
    }

}
