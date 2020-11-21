package com.alibaba.edison;

/**
 * Created by 钧戈 on 2020/10/18.
 */
public class Sword48 {

    public int lengthOfLongestSubstring(String s) {
        return dp(s);
    }

    /**
     * 动态规划
     *
     * @param s
     * @return
     */
    private int dp(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        } else if (s.length() == 1) {
            return 1;
        }
        char[] chars = s.toCharArray();
        //以chars[i]为结尾的最长无重复字符串长度
        int[] dp = new int[chars.length];
        dp[0] = 1;
        int max = 1;
        for (int i = 1; i < chars.length; i++) {
            boolean dupicate = false;
            for (int j = 1; j <= dp[i - 1]; j++) {
                if (chars[i - j] == chars[i]) {
                    dp[i] = j;
                    dupicate = true;
                    break;
                }
            }
            if (!dupicate) {
                dp[i] = dp[i - 1] + 1;
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    /**
     * 滑动窗口
     * 滑动窗口的思想与动态规划一样，只是实现形式选择了滑动窗口
     * @param s
     * @return
     */
    private int slide(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        } else if (s.length() == 1) {
            return 1;
        }
        char[] chars = s.toCharArray();
        int left = 0, right = 0;
        int max = 0;
        for (int i = 1; i < chars.length; i++) {
            for (int j = left; j <= right; j++) {
                if (chars[j] == chars[i]) {
                    left = j+1;
                    break;
                }
            }
            right++;
            max = Math.max(right-left+1, max);
        }
        return max;
    }
}
