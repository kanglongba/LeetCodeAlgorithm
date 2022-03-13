package com.alibaba.edison;

/**
 * 寻找两个正序数组的中位数，hard
 * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
 * 算法的时间复杂度应该为 O(log (m+n)) 。
 *
 * 输入：nums1 = [1,2], nums2 = [3,4]
 * 输出：2.50000
 * 解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
 *
 * author: qonyqian
 * created on: 2022/3/12 9:41 下午
 * version：1.0
 * description:
 */
public class LeetCode4 {

    /**
     * 先排序，但是只需要排一半就行了，最后直接根据奇偶，取中位数
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int oneLength = nums1.length;
        int twoLength = nums2.length;
        int oneMid = 0, twoMid = 0;
        int count = (oneLength + twoLength) / 2; //计算中位数
        int[] ans = new int[count + 1]; //合并后的数组如果是奇数，中位数是一个；合并后的数组如果是偶数，中位数是两个。所以数组长度要多一位。
        for (int i = 0; i < count + 1; i++) {
            if (oneMid >= oneLength) { // nums1已经探索完了
                ans[i] = nums2[twoMid];
                twoMid++;
            } else if (twoMid >= twoLength) { // nums2已经探索完了
                ans[i] = nums1[oneMid];
                oneMid++;
            } else if (nums1[oneMid] < nums2[twoMid]) { //从小到大排列，把更小的元素排到ans中
                ans[i] = nums1[oneMid];
                oneMid++;
            } else {
                ans[i] = nums2[twoMid];
                twoMid++;
            }
        }
        if ((oneLength + twoLength) % 2 == 0) { //合并后的数组如果是偶数
            return (ans[count - 1] + ans[count]) / 2.0;
        } else {  // 合并后的数组如果是奇数
            return ans[count];
        }
    }
}
