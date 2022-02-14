package com.alibaba.edison;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 找到字符串中所有字母异位词，medium
 * 给定两个字符串s和 p，找到s中所有p的异位词的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
 * 异位词 指由相同字母重排列形成的字符串（包括相同的字符串）。
 *
 * 输入: s = "abab", p = "ab"
 * 输出: [0,1,2]
 *
 * 这道题很经典，对于滑动窗口的考察很好。
 *
 * 未能独立求解
 *
 * author: qonyqian
 * created on: 2022/2/14 10:18 下午
 * version：1.0
 * description:
 */
public class LeetCode438 {

    /**
     * 滑动窗口
     * 根据题目要求，我们需要在字符串 s 寻找字符串 p 的异位词。因为字符串 p 的异位词的长度一定与字符串 p 的长度相同，所以我们可以在字符
     * 串 s 中构造一个长度为与字符串 p 的长度相同的滑动窗口，并在滑动中维护窗口中每种字母的数量；当窗口中每种字母的数量与字符串 p 中每种
     * 字母的数量相同时，则说明当前窗口为字符串 p 的异位词。
     *
     * 算法
     * 在算法的实现中，我们可以使用数组来存储字符串 p 和滑动窗口中每种字母的数量。
     *
     * 细节
     * 当字符串 s 的长度小于字符串 p 的长度时，字符串 s 中一定不存在字符串 p 的异位词。但是因为字符串 s 中无法构造长度与字符串 p 的长度
     * 相同的窗口，所以这种情况需要单独处理。
     *
     * 官方代码
     *
     * @param s
     * @param p
     * @return
     */
    public List<Integer> findAnagrams(String s, String p) {
        int sLen = s.length(), pLen = p.length();

        if (sLen < pLen) {
            return new ArrayList<Integer>();
        }

        List<Integer> ans = new ArrayList<Integer>();
        int[] sCount = new int[26]; //位置代表26个小写字母，值代表这个小写字母的使用次数
        int[] pCount = new int[26]; //这道题对滑动窗口的应用，就是判断两个数组中，对字母的使用次数是否一致。如果一致，就是异位词。
        for (int i = 0; i < pLen; ++i) {
            ++sCount[s.charAt(i) - 'a']; //++ 给数组的值+1. char相减，是代表他们的ASCII码值相减，正好等于这个字符在数组中的位置。
            ++pCount[p.charAt(i) - 'a']; //这段循环主要的目的就是求出字符串 p 的字符使用情况。在接下来的运算中，pCount 不在变化。
        }

        if (Arrays.equals(sCount, pCount)) { //相同位置的元素的值相等。
            ans.add(0);
        }

        for (int i = 0; i < sLen - pLen; ++i) { //这里正式开始 滑动窗口 。要注意，滑动窗口是在字符串 s 上滑动。
            --sCount[s.charAt(i) - 'a']; //滑动窗口向前滑动一个位置，把滑出窗口的字符的使用次数 -1
            ++sCount[s.charAt(i + pLen) - 'a']; //滑动窗口向前滑动一个位置，把滑进窗口的字符的使用次数 +1

            if (Arrays.equals(sCount, pCount)) { //判断两个数组是否相等
                ans.add(i + 1);
            }
        }

        return ans;

    }

    public static void main(String[] args) {
        LeetCode438 leet = new LeetCode438();
        leet.findAnagrams("cbaebabacd","abc");
    }

}
