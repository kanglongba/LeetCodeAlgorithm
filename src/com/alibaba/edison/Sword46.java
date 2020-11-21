package com.alibaba.edison;

/**
 * Created by 钧戈 on 2020/10/17.
 */
public class Sword46 {

    public int translateNum(int num) {
        return dp(num);
    }

    private int dp(int num) {
        String str = String.valueOf(num);
        char[] chars = str.toCharArray();
        if (chars.length < 2) {
            return 1;
        }
        int[] dp = new int[chars.length];
        dp[0] = 1;
        StringBuilder builder = new StringBuilder();
        builder.append(chars[0]).append(chars[1]);
        dp[1] = Integer.valueOf(builder.toString()) <=25 ? 2 : 1;
        for (int i = 2; i < chars.length; i++) {
            if (chars[i - 1] == '0') {
                dp[i] = dp[i-1];
            } else {
                StringBuilder builder1 = new StringBuilder();
                builder1.append(chars[i - 1]).append(chars[i]);
                int merge = Integer.valueOf(builder1.toString());
                if (merge <= 25) {
                    dp[i] = dp[i - 2] + dp[i - 1];
                } else {
                    dp[i] = dp[i - 1];
                }
            }
        }
        return dp[chars.length-1];
    }
}
