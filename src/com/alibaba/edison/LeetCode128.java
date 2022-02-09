package com.alibaba.edison;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * 最长连续序列，medium
 * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
 * 请你设计并实现时间复杂度为O(n) 的算法解决此问题。
 * <p>
 * LeetCode上评论说，这是字节跳动二面原题
 *
 *
 * 未能写出最优解
 *
 * author: qonyqian
 * created on: 2022/2/9 7:25 下午
 * version：1.0
 * description:
 */
public class LeetCode128 {

    public static void main(String[] args) {
        int[] arrays = new int[]{0, 3, 7, 2, 5, 8, 4, 6, 0, 1};
        LeetCode128 leet = new LeetCode128();
        System.out.println("args = " + leet.longestConsecutive(arrays));
    }

    /**
     * 貌似正经算法都不行
     * 要求时间复杂度是O(n)，必须借助额外的数据结构。只能向这个方向思考。
     * <p>
     * 代码能得到正确答案，但就是写得跟屎一样
     * 执行用时：45 ms, 在所有 Java 提交中击败了 35.12% 的用户
     * 内存消耗：52 MB, 在所有 Java 提交中击败了 75.84% 的用户
     * <p>
     * 思路：
     * 1.要求O(n)，那就必须只能一次遍历，必须利用额外的数据结构，记录遍历过的信息
     * 2.使用HashMap，以nums[i]为key，以包含nums[i]的连续序列为value
     * 3.遍历数组时，看HashMap中是否包含 nums[i]-1 和 nums[i]+1 的key
     * 3.1如果包含，说明nums[i]可以与它们构成连续序列，那么就更新区间的左右边界，同时更新最大值
     * 3.1.1 注意处理：更新区间左右边界时，要把原来边界点的 左右边界值也更新
     * 3.1.2 注意处理： nums[i] 使 nums[i]-1 和 nums[i]+1 连成了一片
     * 3.2如果不包含，就直接把 nums[i] 插入HashMap
     *
     * @param nums
     * @return
     */
    public int longestConsecutive(int[] nums) {
        int length = nums.length;
        if (length == 0) {
            return length;
        }
        int max = 1;
        HashMap<Integer, Range> map = new HashMap<>();
        for (int i = 0; i < length; i++) {
            int value = nums[i];
            if (map.containsKey(value)) {
                continue;
            }
            Range num = new Range(value, value);
            int leftLength = 0;
            int leftLeft = 0;
            int leftRight = 0;
            if (map.containsKey(value - 1)) {
                Range range = map.get(value - 1);
                leftLeft = range.left;
                leftRight = range.right;
                range.right = Math.max(num.right, range.right);
                num.right = range.right;
                range.left = Math.min(num.left, range.left);
                num.left = range.left;
                map.get(leftLeft).left = num.left;
                map.get(leftLeft).right = num.right;
                map.get(leftRight).left = num.left;
                map.get(leftRight).right = num.right;
                leftLength = range.right - range.left + 1;
            }
            int rightLength = 0;
            if (map.containsKey(value + 1)) {
                Range range = map.get(value + 1);
                int left = range.left;
                int right = range.right;
                range.left = Math.min(range.left, num.left);
                num.left = range.left;
                range.right = Math.max(range.right, num.right);
                num.right = range.right;
                map.get(left).left = num.left;
                map.get(left).right = num.right;
                map.get(right).left = num.left;
                map.get(right).right = num.right;
                rightLength = range.right - range.left + 1;
            }
            int numLength = num.right - num.left + 1;
            if (leftLength > 1 && rightLength > 1) {
                map.get(value - 1).left = num.left;
                map.get(value - 1).right = num.right;
                map.get(leftLeft).left = num.left;
                map.get(leftLeft).right = num.right;
                map.get(leftRight).left = num.left;
                map.get(leftRight).right = num.right;
            }
            max = Math.max(max, Math.max(leftLength, Math.max(rightLength, numLength)));
            map.put(value, num);
        }
        return max;
    }

    public class Range {
        int left;
        int right;

        public Range() {
            left = Integer.MIN_VALUE;
            right = Integer.MIN_VALUE;
        }

        public Range(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }

    /**
     * 优化上面那坨代码
     * 官方代码：https://leetcode-cn.com/problems/longest-consecutive-sequence/solution/zui-chang-lian-xu-xu-lie-by-leetcode-solution/
     * 官方答案果然构思更巧妙
     * 我们考虑枚举数组中的每个数 x，考虑以其为起点，不断尝试匹配 x+1, x+2, ⋯ 是否存在，假设最长匹配到了 x+y，那么以 x 为起点的最长连
     * 续序列即为 x, x+1, x+2, ⋯, x+y，其长度为 y+1，我们不断枚举并更新答案即可。
     * 仔细分析这个过程，我们会发现其中执行了很多不必要的枚举，如果已知有一个 x, x+1, x+2, ⋯, x+y 的连续序列，而我们却重新
     * 从 x+1，x+2 或者是 x+y 处开始尝试匹配，那么得到的结果肯定不会优于枚举 x 为起点的答案，因此我们在外层循环的时候碰到这
     * 种情况跳过即可。
     * 只有当一个数是连续序列的第一个数的情况下才会进入内层循环，然后在内层循环中匹配连续序列中的数，因此数组中的每个数只会进入内层循环一次。
     *
     * @param nums
     * @return
     */
    public int youhua(int[] nums) {
        Set<Integer> num_set = new HashSet<Integer>();
        for (int num : nums) {
            num_set.add(num);
        }

        int longestStreak = 0;

        for (int num : num_set) {
            if (!num_set.contains(num - 1)) {
                int currentNum = num;
                int currentStreak = 1;

                while (num_set.contains(currentNum + 1)) {
                    currentNum += 1;
                    currentStreak += 1;
                }

                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }

        return longestStreak;

    }
}
