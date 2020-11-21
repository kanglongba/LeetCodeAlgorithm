package com.alibaba.edison;

/**
 * Created by 钧戈 on 2020/6/13.
 */
public class LeetCode92 {

    public static void main(String[] args) {
        System.out.println("args = [" + numDecodings("100") + "]");
    }

    public static int numDecodings(String s) {
        //dp[i]为从0到i的子串的解法
        int length = s.length();
        char[] chars = s.toCharArray();
        if (chars[0] == '0') {
            return 0;
        }
        if (length == 1) {
            return 1;
        }
        int[] dp = new int[length];
        dp[0] = 1;
        if(chars[1]=='0'){
            if(chars[0]=='1'||chars[0]=='2'){
                dp[1] = 1;
            } else {
                dp[1] = 0;
            }
        } else {
            if (chars[0] =='1'){
                dp[1] =2;
            } else if (chars[0]=='2'  && chars[1] <= '6'){
                dp[1] =2;
            } else {
                dp[1] =1;
            }
        }
        for (int i=2;i<length;i++){
            if (dp[i-1]==0){
                dp[i] = 0;
            } else if (chars[i]=='0'){
                if(chars[i-1]=='1'||chars[i-1]=='2'){
                    dp[i] = dp[i-2];
                } else {
                    dp[i] = 0;
                }
            } else {
                if (chars[i-1]=='1' || (chars[i-1]=='2' && chars[i] <= '6')) {
                    dp[i] = dp[i-1] + dp[i-2];
                } else {
                    dp[i] = dp[i-1];
                }
            }
        }
        return dp[length-1];
    }
}
