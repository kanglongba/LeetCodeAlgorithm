package com.alibaba.edison;

/**
 * 跳跃游戏，medium
 * 给定一个非负整数数组 nums ，你最初位于数组的 第一个下标 。
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 判断你是否能够到达最后一个下标。
 * <p>
 * 未能想到最优解。
 * <p>
 * author: qonyqian
 * created on: 2022/2/7 10:20 下午
 * version：1.0
 * description:
 */
public class LeetCode55 {

    /**
     * 1.凭直觉是动态规划，但是总结不出状态转移方程
     * 2.回溯法，探索所有的可能解，但是可能情况太多，时间复杂度抗不住
     * 3.辅助数组，标志每个点是否可到达，如果可达，在以此点位起点，继续探索。
     * 4.官方最优解，竟然是贪心算法
     *
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {
        if (nums[0] == 0) { //先处理边界情况
            return nums.length <= 1;
        }
        int length = nums.length;
        boolean[] jump = new boolean[length]; // 借助辅助数组，表示这些点是否可到达
        jump[0] = true; //第一个点，肯定可以到达
        for (int i = 0; i < length; i++) { //遍历数组
            if (jump[i]) { //如果当前点可以到达
                for (int j = 1; j <= nums[i]; j++) { //那么以当前点为起点，以值为距离的区间 [i, i+nums[i]] 内的点，都是可以到达的
                    if (i + j <= length - 1) { //防止数组越界
                        jump[i + j] = true;
                    }
                }
                if (jump[length - 1]) { //已经探索了一条路径，提前返回
                    return true;
                }
            } else { // 如果当前点不可到达，那么就不能以当前点为起点继续向后探索了
                continue;
            }
        }
        return false;
    }

    /**
     * 官方最优解：贪心算法，https://leetcode-cn.com/problems/jump-game/solution/tiao-yue-you-xi-by-leetcode-solution/
     *
     * 我们依次遍历数组中的每一个位置，并实时维护 最远可以到达的位置。对于当前遍历到的位置 x，如果它在 最远可以到达的位置 的范围内，那么
     * 我们就可以从起点通过若干次跳跃到达该位置，因此我们可以用 x + nums[x] 更新 最远可以到达的位置。
     *
     * 在遍历的过程中，如果 最远可以到达的位置 大于等于数组中的最后一个位置，那就说明最后一个位置可达，我们就可以直接返回 True 作为答案。反
     * 之，如果在遍历结束后，最后一个位置仍然不可达，我们就返回 False 作为答案。
     *
     *
     * @param nums
     * @return
     */
    public boolean officialCanJump(int[] nums) {
        int n = nums.length;
        int rightmost = 0;
        for (int i = 0; i < n; ++i) {
            if (i <= rightmost) {
                rightmost = Math.max(rightmost, i + nums[i]);
                if (rightmost >= n - 1) {
                    return true;
                }
            }
        }
        return false;
    }
}
