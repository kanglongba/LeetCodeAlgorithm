package com.alibaba.edison;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Stack;

/**
 * 字符串解码，medium
 * 给定一个经过编码的字符串，返回它解码后的字符串。
 * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
 * 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
 * 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像3a或2[4]的输入。
 *
 * 输入：s = "3[a2[c]]"
 * 输出："accaccacc"
 *
 * 未能独立解答
 *
 * author: qonyqian
 * created on: 2022/2/14 11:33 上午
 * version：1.0
 * description:
 */
public class LeetCode394 {

    /**
     * 猜到用栈，但是不知道怎么用
     *
     * 辅助栈法：数字存放在数字栈，字符串存放在字符串栈，遇到右括号时候弹出一个数字栈，字母栈弹到左括号为止。就是逆波兰式那种题
     *
     * 遍历这个栈：
     * 1.如果当前的字符为数位，解析出一个数字（连续的多个数位）并进栈
     * 2.如果当前的字符为字母或者左括号，直接进栈
     * 3.如果当前的字符为右括号，开始出栈，一直到左括号出栈，出栈序列反转后拼接成一个字符串，此时取出栈顶的数字（此时栈顶一定是数字，想想
     * 为什么？），就是这个字符串应该出现的次数，我们根据这个次数和字符串构造出新的字符串并进栈
     *
     * 重复如上操作，最终将栈中的元素按照从栈底到栈顶的顺序拼接起来，就得到了答案。注意：这里可以用不定长数组来模拟栈操作，方便从栈底向栈
     * 顶遍历。
     *
     * 自己实现的代码。分为数字栈和字符栈，跟单栈思路是一样。
     *
     * @param s
     * @return
     */
    public String decodeString(String s) {
        Stack<Integer> number = new Stack<>(); //存储数字
        Stack<Character> chars = new Stack<>(); //存储字符
        char[] charArray = s.toCharArray();
        int length = charArray.length;

        StringBuilder result = new StringBuilder();
        int digit = 0; //数字
        for (int i = 0; i < length; i++) { //从左向右遍历字符串
            if (Character.isDigit(charArray[i])) { //如果是数字
                digit = digit * 10 + Character.getNumericValue(charArray[i]); //数字有可能很大 ，比如122，占据三个字符的位置，所以必须逐位累积
            } else if (charArray[i] == '[') {
                number.push(digit); //数字读取完成，压入数字栈
                digit = 0; //重置
                chars.push(charArray[i]); //压入字符栈
            } else if (Character.isLetter(charArray[i])) { //如果是字符
                if (chars.empty()) { //当前字符栈为空，表示当前没有遇到编码的字符，直接插入结果集即可
                    result.append(charArray[i]);
                } else { //字符栈不为空，表示当前处于编码字符串的范围，读取的字符都是编码的一部分，直接插入字符栈，直到遇到"]"
                    chars.push(charArray[i]);
                }
            }  else if (charArray[i] == ']') { //遇到"]"，表示当前这段编码字符串结束了，我们要开始对这段字符串做解码操作
                int value = number.pop(); //取出栈顶数字，就是是这段编码字符的重复次数
                StringBuilder builder = new StringBuilder();
                while (!chars.empty()) {
                    if (chars.peek() == '[') { //不断出栈，直到遇到第一个"["。"["和"]"构成一个编码字符串。
                        chars.pop();
                        break;
                    } else {
                        char letter = chars.pop();
                        builder.insert(0,letter); //不能用append，要保证字符的顺序，必须使用头插法
                    }
                }
                String str = builder.toString();
                for (int j = 1; j < value; j++) { // 解码字符串，也就是重复 value 遍。
                    builder.append(str);
                }
                if (chars.empty()) { // 如果字符栈空了，表明当前没有嵌套的需要解码的字符串，可以直接将解完码的字符串插入结果集
                    result.append(builder);
                } else { //表明目前处于嵌套编码状态中，本次解完码的字符串又是其他编码的嵌套子串。所以要把解完码的字符串压回字符栈。
                    for (int j = 0; j < builder.length(); j++) {
                        chars.push(builder.charAt(j));
                    }
                }
            }
        }
        return result.toString();
    }



    /**
     * 评论里面别人写的高级代码
     * 使用StringBuffer作为入栈元素，简化了很多操作。
     * @param s
     * @return
     */
    public String decodeString2(String s) {
        StringBuffer ans = new StringBuffer();
        Stack<Integer> multiStack = new Stack<>();
        Stack<StringBuffer> ansStack = new Stack<>();
        int multi = 0;
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) multi = multi * 10 + c - '0';
            else if (c == '[') {
                ansStack.add(ans);
                multiStack.add(multi);
                ans = new StringBuffer();
                multi = 0;
            } else if (Character.isAlphabetic(c)) {
                ans.append(c);
            } else { //遇到了 "]"
                StringBuffer ansTmp = ansStack.pop();
                int tmp = multiStack.pop();
                for (int i = 0; i < tmp; i++) ansTmp.append(ans);
                ans = ansTmp;
            }
        }
        return ans.toString();
    }


    /**
     * 官方答案：https://leetcode-cn.com/problems/decode-string/solution/zi-fu-chuan-jie-ma-by-leetcode-solution/
     * 官方代码实际用了一个栈。
     * @param s
     * @return
     */
    public String decodeString3(String s) {
        LinkedList<String> stk = new LinkedList<String>();
        ptr = 0;

        while (ptr < s.length()) {
            char cur = s.charAt(ptr);
            if (Character.isDigit(cur)) {
                // 获取一个数字并进栈
                String digits = getDigits(s);
                stk.addLast(digits);
            } else if (Character.isLetter(cur) || cur == '[') {
                // 获取一个字母并进栈
                stk.addLast(String.valueOf(s.charAt(ptr++)));
            } else {
                ++ptr;
                LinkedList<String> sub = new LinkedList<String>();
                while (!"[".equals(stk.peekLast())) {
                    sub.addLast(stk.removeLast());
                }
                Collections.reverse(sub);
                // 左括号出栈
                stk.removeLast();
                // 此时栈顶为当前 sub 对应的字符串应该出现的次数
                int repTime = Integer.parseInt(stk.removeLast());
                StringBuffer t = new StringBuffer();
                String o = getString(sub);
                // 构造字符串
                while (repTime-- > 0) {
                    t.append(o);
                }
                // 将构造好的字符串入栈
                stk.addLast(t.toString());
            }
        }

        return getString(stk);
    }

    int ptr;

    public String getDigits(String s) {
        StringBuffer ret = new StringBuffer();
        while (Character.isDigit(s.charAt(ptr))) {
            ret.append(s.charAt(ptr++));
        }
        return ret.toString();
    }

    public String getString(LinkedList<String> v) {
        StringBuffer ret = new StringBuffer();
        for (String s : v) {
            ret.append(s);
        }
        return ret.toString();
    }


    public static void main(String[] args) {
        LeetCode394 leet = new LeetCode394();
        leet.decodeString("3[a]2[bc]");
    }
}
