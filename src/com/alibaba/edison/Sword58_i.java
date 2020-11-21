package com.alibaba.edison;

import java.util.Arrays;

/**
 * Created by 钧戈 on 2020/10/4.
 */
public class Sword58_i {

    public String reverseWords(String s) {
        return force(s);
    }

    /**
     * 暴力法。直接分段，然后倒排。
     * @param s
     * @return
     */
    private String force(String s) {
        if (s == null) {
            return s;
        }
        StringBuilder builder = new StringBuilder();
        String[] split = s.split(" ");
        for (int i=split.length-1;i>=0;i--) {
            if (!"".equals(split[i].trim())) {
                builder.append(split[i]);
                if (i != 0){
                    builder.append(" ");
                }
            }
        }
        return builder.toString().trim();
    }

    /**
     * 双指针法
     * @param s
     * @return
     */
    private String point(String s) {
        if (s==null || s.trim()==""){
            return s;
        }
        int length = s.length();
        StringBuilder builder = new StringBuilder();
        int pointA = length-1, pointB = pointA;
        while(pointA >= 0 && pointB >= 0) {
            while (pointA >=0 && s.charAt(pointA) == ' ') {
                pointA--;
            }
            while (pointB >=0 && s.charAt(pointB) == ' ') {
                pointB--;
            }

            while (pointA >=0 && s.charAt(pointA) != ' ') {
                pointA--;
            }
            builder.append(s.substring(pointA + 1, pointB + 1)).append(" ");
            pointB = pointA;

        }
        return builder.toString().trim();
    }

    /**
     * 二刷 2020-11-01 17:32:37
     * @param s
     * @return
     */
    private String ershua(String s){
        char[] chars = s.toCharArray();
        int start = chars.length-1;
        int end = start;
        StringBuilder builder = new StringBuilder();
        while (start >= 0 && end >= 0) {
            while (start >= 0 && chars[start] == ' ') {
                start--;
            }
            end = start;
            while (start >= 0 && chars[start] != ' ') {
                start--;
            }
            builder.append(Arrays.copyOfRange(chars, start+1, end+1)).append(' ');
            end = start;
        }
        return builder.toString().trim();
    }

}
