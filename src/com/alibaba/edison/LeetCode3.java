package com.alibaba.edison;

import java.util.HashSet;
import java.util.Set;

/**
 * 无重复字符的最长子串，medium
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 *
 * 只想到了dp的解法，最优解是滑动窗口
 * <p>
 * author: qonyqian
 * created on: 2022/2/3 7:20 下午
 * version：1.0
 * description:
 */
public class LeetCode3 {

    /**
     * 滑动窗口：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/solution/wu-zhong-fu-zi-fu-de-zui-chang-zi-chuan-by-leetc-2/
     * <p>
     * 看官方滑动窗口的代码，只是使用HashSet优化了在区内查找重复元素的逻辑，核心思想并没有变
     * <p>
     * 算法思想：
     * 如果我们依次递增地枚举子串的起始位置，那么子串的结束位置也是递增的！这里的原因在于，假设我们选择字符串中的第 k 个字符作为起始位
     * 置，并且得到了不包含重复字符的最长子串的结束位置为 rk。那么当我们选择第 k+1 个字符作为起始位置时，首先从 k+1 到 rk的字符显然
     * 是不重复的，并且由于少了原本的第 k 个字符，我们可以尝试继续增大 rk，直到右侧出现了重复字符为止。
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        // 哈希集合，记录每个字符是否出现过
        Set<Character> occ = new HashSet<Character>();
        int n = s.length();
        // 右指针，初始值为 -1，相当于我们在字符串的左边界的左侧，还没有开始移动
        int rk = -1, ans = 0;
        for (int i = 0; i < n; ++i) {
            if (i != 0) {
                // 左指针向右移动一格，移除一个字符
                occ.remove(s.charAt(i - 1));
            }
            while (rk + 1 < n && !occ.contains(s.charAt(rk + 1))) {
                // 不断地移动右指针
                occ.add(s.charAt(rk + 1));
                ++rk;
            }
            // 第 i 到 rk 个字符是一个极长的无重复字符子串
            ans = Math.max(ans, rk - i + 1);
        }
        return ans;
    }

    /**
     * 凭直觉就是dp
     * <p>
     * dp[i]表示以第i个字符结尾的最长子串的起点。
     * dp[i] = 在[dp[i-1], i-1]区间内，从右向左遍历，与第i个字符重复的位置
     *
     * @param s
     * @return
     */
    public int dp(String s) {
        int length = s.length();
        if (length < 1) {
            return length;
        }
        int[] dp = new int[length];
        dp[0] = 0;
        int max = 1;
        for (int i = 1; i < length; i++) {
            boolean find = false;
            for (int j = i - 1; j >= dp[i - 1]; j--) {
                if (s.charAt(j) == s.charAt(i)) {
                    find = true;
                    dp[i] = j + 1;
                    max = Math.max(max, i - j);
                    break;
                }
            }
            if (!find) { //如果和[dp[i-1], i-1]没有重复，直接扩充区间
                dp[i] = dp[i - 1];
                max = Math.max(max, i - dp[i] + 1);
            }
        }
        return max;
    }

    /**
     * 最长无重复字符子串
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring3(String s) {
        if(s == null) {
            return 0;
        }
        int left = 0, right = 0;
        int max = 0;
        HashSet<Character> values = new HashSet<>();
        while(left <= right && right < s.length()) {
            char value = s.charAt(right);
            if(values.contains(value)) {
                values.remove(s.charAt(left));
                left++;
            } else {
                values.add(value);
                right++;
                max = Math.max(max, values.size());
            }
        }
        return max;
    }
}
