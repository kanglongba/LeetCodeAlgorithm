package com.alibaba.edison;

import java.util.List;

/**
 * author: qonyqian
 * created on: 2022/3/10 4:37 下午
 * version：1.0
 * description:
 */
public class MSTRTwoString {

    static void commonSubstring(List<String> a, List<String> b) {
        int length = a.size();
        for (int i = 0; i < length; i++) {
            String strA = a.get(i);
            String strB = b.get(i);
            int[] chars = new int[26];
            for (int j = 0; j < strA.length(); j++) {
                chars[strA.charAt(j) -'a']++;
            }
            boolean isMatch = false;
            for (int j = 0; j < strB.length(); j++) {
                if (chars[strB.charAt(j) - 'a'] > 0) {
                    isMatch = true;
                    break;
                }
            }
            if (isMatch) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }
}
