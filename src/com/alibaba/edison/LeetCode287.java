package com.alibaba.edison;

/**
 * 寻找重复数，medium
 * 给定一个包含n + 1 个整数的数组nums ，其数字都在[1, n]范围内（包括 1 和 n），可知至少存在一个重复的整数。
 * 假设 nums 只有 一个重复的整数 ，返回这个重复的数 。
 * 你设计的解决方案必须 不修改 数组 nums 且只用常量级 O(1) 的额外空间。
 *
 * 未能得出最优解
 *
 * author: qonyqian
 * created on: 2022/2/13 2:49 下午
 * version：1.0
 * description:
 */
public class LeetCode287 {

    /**
     * 1.限制条件太多，不能用排序这种方法
     * 2.暴力法肯定行的。
     * 3.快慢指针【真是没有想到】：https://leetcode-cn.com/problems/find-the-duplicate-number/solution/287xun-zhao-zhong-fu-shu-by-kirsche/
     * 本方法需要读者对 「Floyd 判圈算法」（又称龟兔赛跑算法）有所了解，它是一个检测链表是否有环的算法，LeetCode 中相关例题
     * 有 141. 环形链表，142. 环形链表 II。
     * 我们对 nums 数组建图，每个位置 i 连一条 i→nums[i] 的边。由于存在的重复的数字 target，因此 target 这个位置一定有起码两条指向
     * 它的边，因此整张图一定存在环，且我们要找到的 target 就是这个环的入口，那么整个问题就等价于 142. 环形链表 II。
     * 我们先设置慢指针 slow 和快指针 fast ，慢指针每次走一步，快指针每次走两步，根据「Floyd 判圈算法」两个指针在有环的情况下一定会相
     * 遇，此时我们再将 slow 放置起点 0，两个指针每次同时移动一步，相遇的点就是答案。
     *
     * @param nums
     * @return
     */
    public int findDuplicate(int[] nums) {
        int slow = 0, fast = 0;
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);
        slow = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }

    /**
     * 如果题目限制：n+1长度的数组，只有有一个数字出现两次，其余数字均出现一次，那么可以用下面这种方法（也可以用数组之和-n个数字之和）。
     *
     * @param nums
     * @return
     */
    public int findDuplicate1(int[] nums) {
        int n = nums.length - 1;
        int element = 0;
        for (int i = 1; i <= n; i++) {
            int value = nums[i - 1];
            element ^= value ^ i;
        }
        return element ^ nums[n];
    }
}
