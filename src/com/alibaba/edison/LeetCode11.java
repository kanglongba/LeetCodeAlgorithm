package com.alibaba.edison;

/**
 * 盛水最多的容器，medium
 * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点(i,ai) 。在坐标内画 n 条垂直线，垂直线 i的两个端点分别
 * 为(i,ai) 和 (i, 0) 。找出其中的两条线，使得它们与x轴共同构成的容器可以容纳最多的水。
 *
 * 未能独立解答
 *
 * author: qonyqian
 * created on: 2022/2/4 11:28 下午
 * version：1.0
 * description:
 */
public class LeetCode11 {

    /**
     * 凭直觉就是动态规划【卧槽，直觉是错的】
     *
     * 最优解是双指针，从两头向中间移动，每次移动值较小的那边。移动较小的那边才有可能发现最优解。否则移动较大的边，随着横轴缩小，值只会越来越小。
     *
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        int n = height.length;
        int left = 0, right = n-1;
        int max = 0;
        while (left < right) {
            int area = Math.min(height[left], height[right]) * (right - left);
            max = Math.max(area, max);
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return max;
    }
}
