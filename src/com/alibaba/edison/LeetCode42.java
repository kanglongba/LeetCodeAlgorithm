package com.alibaba.edison;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 接雨水，hard
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 *
 * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出：6
 * 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
 *
 * 未能独立解答
 *
 * author: qonyqian
 * created on: 2022/3/12 10:26 下午
 * version：1.0
 * description:
 */
public class LeetCode42 {

    /**
     * 动态规划：https://leetcode-cn.com/problems/trapping-rain-water/solution/jie-yu-shui-by-leetcode-solution-tuvc/
     * 官方题解：
     * 对于下标 i，下雨后水能到达的最大高度等于下标 i 两边的最大高度的最小值，下标 i 处能接的雨水量等于下标 i 处的水能到达的最大高度减
     * 去 height[i]。
     * 朴素的做法是对于数组 height 中的每个元素，分别向左和向右扫描并记录左边和右边的最大高度，然后计算每个下标位置能接的雨水量。假设数
     * 组 height 的长度为 n，该做法需要对每个下标位置使用 O(n) 的时间向两边扫描并得到最大高度，因此总时间复杂度是 O(n^2)。
     * 上述做法的时间复杂度较高是因为需要对每个下标位置都向两边扫描。如果已经知道每个位置两边的最大高度，则可以在 O(n) 的时间内得到能接
     * 的雨水总量。使用动态规划的方法，可以在 O(n) 的时间内预处理得到每个位置两边的最大高度。
     * 创建两个长度为 n 的数组 leftMax 和 rightMax。对于  0≤i<n，
     * * leftMax[i] 表示下标 i 及其左边的位置中，height 的最大高度，
     * * rightMax[i] 表示下标 i 及其右边的位置中，height 的最大高度。
     * 显然，leftMax[0]=height[0]，rightMax[n−1]=height[n−1]。两个数组的其余元素的计算如下（状态转移方程）：
     * * 当 1≤i≤n−1 时，leftMax[i]=max(leftMax[i−1],height[i])；
     * * 当 0≤i≤n−2 时，rightMax[i]=max(rightMax[i+1],height[i])。
     * 因此可以正向遍历数组 height 得到数组 leftMax 的每个元素值，反向遍历数组 height 得到数组 rightMax 的每个元素值。
     * 在得到数组 leftMax 和 rightMax 的每个元素值之后，对于 0≤i<n，下标 i 处能接的雨水量等于 ：
     * min(leftMax[i],rightMax[i])−height[i]。
     * 遍历每个下标位置即可得到能接的雨水总量。
     *
     * 总结：
     * 出发点：计算每个位置 i 上的雨水量，然后把这些雨水量相加，就是你能接到的总量
     * 算法：每个位置上 i 上的雨量，取决于位置 i 上水位能达到的最高达高度，而水位最大高度又取决于位置 i 左右两边的最大高度（必须形成一个
     * 凹槽，才能接水）。
     * 动态规划：使用动态规划，分别寻找位置 i 左边的最大高度和右边的最大高度
     * leftMax[i] 表示下标 i 及其左边的位置中，height 的最大高度
     * rightMax[i] 表示下标 i 及其右边的位置中，height 的最大高度
     * 状态转移方程：
     * leftMax[i]=max(leftMax[i−1],height[i])
     * rightMax[i]=max(rightMax[i+1],height[i])
     * 边界条件：
     * leftMax[0]=height[0]
     * rightMax[n−1]=height[n−1]
     * 雨量计算公式：
     * min(leftMax[i],rightMax[i])−height[i]
     *
     * @param height
     * @return
     */
    public int trap(int[] height) {
        int length = height.length;
        if (length < 3) {
            return 0;
        }
        int[] leftMax = new int[length];
        int[] rightMax = new int[length];
        //从左向右遍历，寻找 leftMax
        for (int i = 0; i < length; i++) {
            if (i == 0) {
                leftMax[i] = height[i];
            } else {
                leftMax[i] = Math.max(height[i], leftMax[i - 1]);
            }
        }
        //从右向左遍历，寻找rightMax
        for (int i = length - 1; i >= 0; i--) {
            if (i == length - 1) {
                rightMax[i] = height[i];
            } else {
                rightMax[i] = Math.max(height[i], rightMax[i + 1]);
            }
        }
        int count = 0;
        //累加每个位置的雨量
        for (int i = 0; i < length; i++) {
            int value = Math.min(leftMax[i], rightMax[i]) - height[i];
            count += value;
        }
        return count;
    }

    /**
     * 双指针
     * 官方答案：https://leetcode-cn.com/problems/trapping-rain-water/solution/jie-yu-shui-by-leetcode-solution-tuvc/
     *
     * 双指针法也是脱胎于动态规化，基本思想与动态规划一样，只是用双指针简化了双数组。
     *
     * 维护两个指针 left 和 right，以及两个变量 leftMax 和 rightMax，初始时 left=0,right=n−1,leftMax=0,rightMax=0。
     * 指针 left 只会向右移动，指针 right 只会向左移动，在移动指针的过程中维护两个变量 leftMax 和 rightMax 的值。
     *
     * @param height
     * @return
     */
    public int trap1(int[] height) {
        int ans = 0;
        int left = 0, right = height.length - 1;
        int leftMax = 0, rightMax = 0;
        while (left < right) { //从两头向中间，计算每个位置的水量
            // leftMax表示位置 left 及其左边的最大值
            leftMax = Math.max(leftMax, height[left]);
            // rightMax表示位置 right 及其右边的最大值
            rightMax = Math.max(rightMax, height[right]);
            //如果 leftMax < rightMax，则说明左指针左边的最大值肯定小于右边的最大值（右边的最大值可能不是rightMax，但一定
            // 大于等于rightMax），所以左指针的最大水位 = leftMax。与动态规划的状态转移方程一致。
            if (leftMax < rightMax) {
                ans += leftMax - height[left];
                ++left;
            } else { //如果 leftMax >= rightMax，则说明右指针左边的最大值大于右边的最大值，所以右指针的最大水位 = rightMax。
                ans += rightMax - height[right];
                --right;
            }
        }
        return ans;
    }

    /**
     * 单调递减栈，比栈顶小的才能入栈
     * 官方答案：https://leetcode-cn.com/problems/trapping-rain-water/solution/jie-yu-shui-by-leetcode-solution-tuvc/
     *
     * 维护一个单调栈，单调栈存储的是下标，满足从栈底到栈顶的下标对应的数组 height 中的元素递减。
     *
     * 从左到右遍历数组，遍历到下标 i 时，如果栈内至少有两个元素，记栈顶元素为 top，top 的下面一个元素是 left，则一定
     * 有 height[left]≥height[top]。如果 height[i]>height[top]，则得到一个可以接雨水的区域，该区域的宽度
     * 是 i−left−1，高度是 min(height[left],height[i])−height[top]，根据宽度和高度即可计算得到该区域能接的雨水量。
     *
     * 为了得到 left，需要将 top 出栈。在对 top 计算能接的雨水量之后，left 变成新的 top，重复上述操作，直到栈变为空，或者栈顶下
     * 标对应的 height 中的元素大于或等于 height[i]。
     *
     * 在对下标 i 处计算能接的雨水量之后，将 i 入栈，继续遍历后面的下标，计算能接的雨水量。遍历结束之后即可得到能接的雨水总量。
     *
     * 单调栈这种算法不容易理解，只掌握动态规划和双指针就好了
     *
     * @param height
     * @return
     */
    public int trap2(int[] height) {
        int ans = 0;
        Deque<Integer> stack = new LinkedList<Integer>();
        int n = height.length;
        for (int i = 0; i < n; ++i) {
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                int top = stack.pop();
                if (stack.isEmpty()) {
                    break;
                }
                int left = stack.peek();
                int currWidth = i - left - 1;
                int currHeight = Math.min(height[left], height[i]) - height[top];
                ans += currWidth * currHeight;
            }
            stack.push(i);
        }
        return ans;
    }
}
