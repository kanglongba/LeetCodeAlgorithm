package com.alibaba.edison;

/**
 * Created by 钧戈 on 2020/9/17.
 */
public class Sword05 {
    public String replaceSpace(String s) {
        StringBuilder builder = new StringBuilder();
        for (int i=0;i<s.length();i++) {
            char c = s.charAt(i);
            if (c == ' ') {
                builder.append("%20");
            } else {
                builder.append(c);
            }
        }
        return builder.toString();
    }
}
