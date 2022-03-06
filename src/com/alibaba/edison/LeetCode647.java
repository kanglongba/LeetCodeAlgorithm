package com.alibaba.edison;

/**
 * 回文子串，medium
 * 给你一个字符串 s ，请你统计并返回这个字符串中 回文子串 的数目。
 * 回文字符串 是正着读和倒过来读一样的字符串。
 * 子字符串 是字符串中的由连续字符组成的一个序列。
 * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。
 *
 * 输入：s = "aaa"
 * 输出：6
 * 解释：6个回文子串: "a", "a", "a", "aa", "aa", "aaa"
 *
 * 跟LeetCode5是同类型题目
 *
 * 未能独立解答
 *
 * author: qonyqian
 * created on: 2022/2/15 5:49 下午
 * version：1.0
 * description:
 */
public class LeetCode647 {

    /**
     * 动态规划：LeetCode5的动态规划代码，稍加修改即可
     * dp[i][j]表示范围 [i,j] 的子串是否是回文子串
     * 状态转移方程： dp[i][j] = dp[i+1][j-1] && string[i] == string[j]
     *
     * @param s
     * @return
     */
    public int countSubstrings(String s) {
        int n = s.length();
        if (n < 2) {
            return n;
        }
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
            }
        }
        int ans  = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (dp[i][j]) {
                    ans++;
                }
            }
        }
        return ans;
    }

    /**
     * 中心拓展法
     * 官方代码：https://leetcode-cn.com/problems/palindromic-substrings/solution/hui-wen-zi-chuan-by-leetcode-solution/
     *
     * 计算有多少个回文子串的最朴素方法就是枚举出所有的回文子串，而枚举出所有的回文字串又有两种思路，分别是：
     * 1.枚举出所有的子串，然后再判断这些子串是否是回文；
     * 2.枚举每一个可能的回文中心，然后用两个指针分别向左右两边拓展，当两个指针指向的元素相同的时候就拓展，否则停止拓展。
     * 我们选择第二种方法来枚举所有的回文子串。
     * 在实现的时候，我们需要处理一个问题，即如何有序地枚举所有可能的回文中心，我们需要考虑回文长度是奇数和回文长度是偶数的两种情况。如果回文长度是奇数，那么回文
     * 中心是一个字符；如果回文长度是偶数，那么中心是两个字符。当然你可以做两次循环来分别枚举奇数长度和偶数长度的回文，但是我们也可以用一个循环搞定。
     *
     * 我们可以推断出长度为 n 的字符串会生成 2n-1 组回文中心 [l_i, r_i]。这样我们只要从 0 到 2n - 2 遍历 i，就可以得到所有可能的回文中心，这样就把奇数长度和
     * 偶数长度两种情况统一起来了。
     *
     * @param s
     * @return
     */
    public int countSubstrings2(String s) {
        int n = s.length(), ans = 0;
        for (int i = 0; i < 2 * n - 1; ++i) {
            int l = i / 2, r = i / 2 + i % 2;
            while (l >= 0 && r < n && s.charAt(l) == s.charAt(r)) {
                --l;
                ++r;
                ++ans;
            }
        }
        return ans;
    }
}
