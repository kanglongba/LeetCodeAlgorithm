package com.alibaba.edison;

/**
 * 最长回文子串，medium
 * 给你一个字符串 s，找到 s 中最长的回文子串。
 *
 * 输入：s = "babad"
 * 输出："bab"
 * 解释："aba" 同样是符合题意的答案。
 * <p>
 * 想到了中心扩展法，但是不知道代码该怎么写，没有想好怎么处理奇数和偶数扩展的问题
 * 未能想到动态规划法。
 * <p>
 * author: qonyqian
 * created on: 2022/2/3 8:47 下午
 * version：1.0
 * description:
 */
public class LeetCode5 {

    /**
     * 最优解：动态规划法
     * <p>
     * dp[i][j]表示区间[i,j]是否是回文串
     * dp[i][j] = dp[i+1][j-1] && nums[i]==nums[j]
     * 边界条件：
     * 1.dp[i][i] = true，一个元素肯定是回文串
     * 2.dp[i][i+1] = nums[i]==nums[i+1]，两个元素的话，如果相等就是回文串
     *
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        int n = s.length();
        if (n < 2) {
            return s;
        }
        int left = 0, right = 0, max = 1;
        boolean[][] dp = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            //初始化所有长度为1的子串都是回文串
            dp[i][i] = true;
        }
        //因为我们的状态转移方程是从较小长度递推到较大长度，所以递推不能单纯从下标开始，而是要从长度开始，这样后面的计算才能复用到前的结果
        for (int length = 2; length <= n; length++) {
            for (int i = 0; i < n; i++) { //下标从0开始，递推所有长度为length的子串是否为回文串
                int j = length + i - 1; //由长度可得子串的右边界
                if (j >= n) { //右边界违规
                    break;
                }
                if (length == 2) { //状态转移方程的边界条件
                    dp[i][j] = s.charAt(i) == s.charAt(j);
                } else {
                    // 状态转移方程。由于递推过程是由较小长度到较大较大长度，所以当计算dp[i][j]时，已经知道了比它长度小的dp[i + 1][j - 1]的值
                    dp[i][j] = dp[i + 1][j - 1] && (s.charAt(i) == s.charAt(j));
                }
                if (dp[i][j] && length > max) { //更新最长回文子串
                    max = length;
                    left = i;
                    right = j;
                }
            }
        }
        return s.substring(left, right + 1);
    }

    /**
     * 中心扩展法：遍历字符串，计算出每个以字符为中心点，向两边扩张的回文子串，然后返回最大值
     * 因为回文子串有奇数和偶数两种类型，所以枚举回传串时，要分两种类型枚举
     * <p>
     * 题解代码写得更好：https://leetcode-cn.com/problems/longest-palindromic-substring/solution/zui-chang-hui-wen-zi-chuan-by-leetcode-solution/
     *
     * @param s
     * @return
     */
    public String zhongxinkuozhan(String s) {
        int n = s.length();
        if (n < 2) {
            return s;
        }
        int max = 0; //最长回文子串的长度
        int start = 0, end = 0; //最长回文子串的上下边界
        for (int i = 0; i < n; i++) { //遍历字符串
            //按奇数查找
            int left1 = i - 1, right1 = i + 1;
            while (left1 >= 0 && right1 < n) {
                if (s.charAt(left1) == s.charAt(right1)) { //相等，继续往两边探索
                    left1--;
                    right1++;
                } else { //不等，结束探索
                    break;
                }
            }
            int count1 = right1 - left1 - 1;
            //按偶数查找
            int left2 = i, right2 = i + 1;
            while (left2 >= 0 && right2 < n) {
                if (s.charAt(left2) == s.charAt(right2)) { //相等，继续往两边探索
                    left2--;
                    right2++;
                } else { //不等，结束探索
                    break;
                }
            }
            int count2 = right2 - left2 - 1;
            //选出最大的回文串，统一用count1表示
            if (count2 > count1) {
                count1 = count2;
                left1 = left2;
                right1 = right2;
            }
            //更新最大值
            if (count1 > max) {
                max = count1;
                start = left1 + 1;
                end = right1 - 1;
            }
        }
        return s.substring(start, end + 1); //左闭右开，所以end+1
    }

}
