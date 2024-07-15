package com.alibaba.edison;

/**
 * 反转链表2，medium
 * 给你单链表的头指针 head 和两个整数left 和 right ，其中left <= right 。请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表 。
 *
 * 输入：head = [1,2,3,4,5], left = 2, right = 4
 * 输出：[1,4,3,2,5]
 *
 * 输入：head = [5], left = 1, right = 1
 * 输出：[5]
 *
 * Created by 钧戈 on 2020/6/13.
 */
public class LeetCode92 {

    public static void main(String[] args) {
        System.out.println("args = [" + numDecodings("100") + "]");
    }

    public static int numDecodings(String s) {
        //dp[i]为从0到i的子串的解法
        int length = s.length();
        char[] chars = s.toCharArray();
        if (chars[0] == '0') {
            return 0;
        }
        if (length == 1) {
            return 1;
        }
        int[] dp = new int[length];
        dp[0] = 1;
        if(chars[1]=='0'){
            if(chars[0]=='1'||chars[0]=='2'){
                dp[1] = 1;
            } else {
                dp[1] = 0;
            }
        } else {
            if (chars[0] =='1'){
                dp[1] =2;
            } else if (chars[0]=='2'  && chars[1] <= '6'){
                dp[1] =2;
            } else {
                dp[1] =1;
            }
        }
        for (int i=2;i<length;i++){
            if (dp[i-1]==0){
                dp[i] = 0;
            } else if (chars[i]=='0'){
                if(chars[i-1]=='1'||chars[i-1]=='2'){
                    dp[i] = dp[i-2];
                } else {
                    dp[i] = 0;
                }
            } else {
                if (chars[i-1]=='1' || (chars[i-1]=='2' && chars[i] <= '6')) {
                    dp[i] = dp[i-1] + dp[i-2];
                } else {
                    dp[i] = dp[i-1];
                }
            }
        }
        return dp[length-1];
    }

    public ListNode reverseBetween2(ListNode head, int left, int right) {
        if (left == right) {
            return head;
        }
        ListNode solder = new ListNode(0);
        ListNode pre = solder;
        ListNode curr = head;
        ListNode leftNode = solder;
        int pos = 1;
        while (curr != null) {
            if (pos < left) {
                pre.next = curr;
                curr = curr.next;
                pre = pre.next;
                pre.next = null;
            } else if (pos == left) {
                leftNode = curr;
                pre.next = curr;
                curr = curr.next;
                leftNode.next = null;
            } else if (pos > left && pos <= right) {
                ListNode temp = curr.next;
                ListNode temp1 = pre.next;
                pre.next = curr;
                curr.next = temp1;
                curr = temp;
                if (pos == right) {
                    pre = leftNode;
                }
            } else if (pos > right) {
                pre.next = curr;
                curr = curr.next;
                pre = pre.next;
                pre.next = null;
            }
            pos++;
        }
        return solder.next;
    }
}
