package com.alibaba.edison;

/**
 * Created by 钧戈 on 2020/9/14.
 */
public class Sword58_ii {

    public String reverseLeftWords(String s, int n) {
        //1.字符串切片
        //2.列表遍历拼接
        return list(s, n);
    }

    private String force(String s, int n) {
        String start = s.substring(0,n);
        String end = s.substring(n, s.length());
        return start.concat(end);
    }

    private String list(String s, int n) {
        StringBuilder builder = new StringBuilder(s.length());
        for (int i=n;i<s.length();i++) {
            builder.append(s.charAt(i));
        }
        for(int i=0;i<n;i++) {
            builder.append(s.charAt(i));
        }
        return builder.toString();
    }
}
