package com.alibaba.edison;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 电话号码的字母组合，medium
 * 给定一个仅包含数字2-9的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 * https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/
 * <p>
 * 未能解答。
 * <p>
 * author: qonyqian
 * created on: 2022/2/5 6:18 下午
 * version：1.0
 * description:
 */
public class LeetCode17 {

    /**
     * 暴力解法，直接枚举就完事了。【暴力法代码不好实现，几重循环跟字符串长度有关，不容易表现在代码中】
     * <p>
     * 最优解：回溯法，回溯算法用于寻找所有的可行解，如果发现一个解不可行，则会舍弃不可行的解。在这道题中，由于每个数字对应的每个字母都可
     * 能进入字母组合，因此不存在不可行的解，直接穷举所有的解即可。
     * <p>
     * 解题思路：
     * 首先使用哈希表存储每个数字对应的所有可能的字母，然后进行回溯操作。
     * 回溯过程中维护一个字符串，表示已有的字母排列（如果未遍历完电话号码的所有数字，则已有的字母排列是不完整的）。该字符串初始为空。每次
     * 取电话号码的一位数字，从哈希表中获得该数字对应的所有可能的字母，并将其中的一个字母插入到已有的字母排列后面，然后继续处理电话号码的
     * 后一位数字，直到处理完电话号码中的所有数字，即得到一个完整的字母排列。然后进行回退操作，遍历其余的字母排列。
     * <p>
     * 回溯法资料：
     * [小白带你学---回溯算法（Back Tracking)](https://zhuanlan.zhihu.com/p/54275352)
     * <p>
     * 剪枝函数？
     *
     * @param digits
     * @return
     */
    public List<String> letterCombinations(String digits) {
        List<String> combinations = new ArrayList<String>();
        if (digits.length() == 0) {
            return combinations;
        }
        Map<Character, String> phoneMap = new HashMap<Character, String>() {{
            put('2', "abc");
            put('3', "def");
            put('4', "ghi");
            put('5', "jkl");
            put('6', "mno");
            put('7', "pqrs");
            put('8', "tuv");
            put('9', "wxyz");
        }};
        backtrack(combinations, phoneMap, digits, 0, new StringBuffer());
        return combinations;
    }

    /**
     * 回溯算法就是不断探索，遇到岔路口，先选择其中一条路往前探索，不论这条路能否找到答案，都要回退到到这个岔路口，然后探索其他路径的可能性。
     * <p>
     * 这里使用递归的方式向前探索，同时递归的返回对应岔路口的回溯。
     *
     * @param combinations
     * @param phoneMap
     * @param digits
     * @param index        探索字符串中第几个字符
     * @param combination  当前已探索的子串
     */
    public void backtrack(List<String> combinations, Map<Character, String> phoneMap, String digits, int index, StringBuffer combination) {
        if (index == digits.length()) { //这条路径探索到底
            combinations.add(combination.toString());
        } else {
            char digit = digits.charAt(index);
            String letters = phoneMap.get(digit);
            int lettersCount = letters.length();
            for (int i = 0; i < lettersCount; i++) {
                //遇到岔路口，探索其中的一个字符
                combination.append(letters.charAt(i));
                //这个字符后面还有岔路口，递归探索
                backtrack(combinations, phoneMap, digits, index + 1, combination);
                //这个字符已经探索完了，把它从子串中删除，在下个循环中，继续探路岔路口的下一个字符。
                combination.deleteCharAt(index);
            }
        }
    }

}
