package com.alibaba.edison;

import java.util.HashMap;

/**
 * 数组嵌套，medium
 * 索引从0开始长度为N的数组A，包含0到N - 1的所有整数。找到最大的集合S并返回其大小，其中 S[i] = {A[i], A[A[i]], A[A[A[i]]], ... }且遵守以下的规则。
 *
 * 假设选择索引为i的元素A[i]为S的第一个元素，S的下一个元素应该是A[A[i]]，之后是A[A[A[i]]]... 以此类推，不断添加直到S出现重复的元素。
 *
 * 输入: A = [5,4,0,3,1,6,2]
 * 输出: 4
 * 解释:
 * A[0] = 5, A[1] = 4, A[2] = 0, A[3] = 3, A[4] = 1, A[5] = 6, A[6] = 2.
 *
 * 其中一种最长的 S[K]:
 * S[0] = {A[0], A[5], A[6], A[2]} = {5, 6, 2, 0}
 *
 * author: qonyqian
 * created on: 2022/7/17 21:51
 * version：1.0
 * description:
 */
public class LeetCode565 {

    /**
     * 遍历这种做法，超出了时间限制
     *
     * @param nums
     * @return
     */
    public int arrayNesting(int[] nums) {
        HashMap<Integer, Integer> result = new HashMap();
        //好像只能遍历
        int length = nums.length;
        int max = 1;
        for (int i = 0; i < length; i++) {
            int j = i;
            while (j < length) {
                if (result.containsKey(nums[j])) {
                    max = Math.max(max, result.size());
                    result.clear();
                    break;
                } else {
                    result.put(nums[j], j);
                    j = nums[j];
                }
            }
        }
        return max;
    }

    /**
     * 图
     * 遍历数组，从 i 向 nums[i] 连边，我们可以得到一张有向图。
     * <p>
     * 由于题目保证 nums 中不含有重复的元素，因此有向图中每个点的出度和入度均为 1。
     * <p>
     * 在这种情况下，有向图必然由一个或多个环组成。我们可以遍历 nums，找到节点个数最大的环。
     * <p>
     * 代码实现时需要用一个 vis 数组来标记访问过的节点。
     *
     * @param nums
     * @return
     */
    public int arrayNesting2(int[] nums) {
        int ans = 0, n = nums.length;
        boolean[] vis = new boolean[n];
        for (int i = 0; i < n; ++i) {
            int cnt = 0;
            // 这道题的关键就是：出度和入度均为1。这就说明一个点只能在一个环上，且不会出现环与环相交的情况（否则会破坏出度入度为1的原则）。
            // 所以如果这个点被探测过了，就说明它所在的这个环已经被探测过，没必要再重复探测。直接向后继续探索新环。
            while (!vis[i]) {
                vis[i] = true;
                i = nums[i];
                ++cnt;
            }
            ans = Math.max(ans, cnt);
        }
        return ans;
    }

    /**
     * 原地标记
     * 利用nums 中的元素大小在 [0,n−1] 之间」这一条件，我们可以省略 vis 数组，改为标记 nums[i]=n，来实现和 vis 数组同样的功能。
     *
     * @param nums
     * @return
     */
    public int arrayNesting3(int[] nums) {
        int ans = 0, n = nums.length;
        for (int i = 0; i < n; ++i) {
            int cnt = 0;
            while (nums[i] < n) {
                int num = nums[i];
                nums[i] = n;
                i = num;
                ++cnt;
            }
            ans = Math.max(ans, cnt);
        }
        return ans;
    }
}
