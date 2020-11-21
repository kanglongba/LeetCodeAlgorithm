package com.alibaba.edison;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 钧戈 on 2020/10/20.
 */
public class Sword67 {

    public int strToInt(String str) {
        return dfa(str);
    }

    /**
     * 正则表达式
     * @param str
     * @return
     */
    private int regrex(String str) {
        String pattern = "^\\s*([+-]?\\d+)";
        Pattern r = Pattern.compile(pattern);

        Matcher m = r.matcher(str);
        if (!m.find( ))  return 0;

        BigInteger ans = new BigInteger(m.group(1));
        if(ans.compareTo(new BigInteger(String.valueOf(Integer.MIN_VALUE)))<0){
            return Integer.MIN_VALUE;
        }
        if(ans.compareTo(new BigInteger(String.valueOf(Integer.MAX_VALUE)))>0){
            return Integer.MAX_VALUE;
        }
        return ans.intValue();
    }

    /**
     * 题目不难，主要考察大数越界的处理
     * @param str
     * @return
     */
    private int math(String str) {
        char[] c = str.trim().toCharArray();
        if(c.length == 0) return 0;
        int res = 0, bndry = Integer.MAX_VALUE / 10;
        int i = 1, sign = 1;
        if(c[0] == '-') sign = -1;
        else if(c[0] != '+') i = 0;
        for(int j = i; j < c.length; j++) {
            //非数字字符
            if(c[j] < '0' || c[j] > '9') break;
            //越界了
            if(res > bndry || res == bndry && c[j] > '7') return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            //正常数字
            res = res * 10 + (c[j] - '0');
        }
        return sign * res;
    }

    /**
     * 有限自动机，这个太牛逼了
     * https://leetcode-cn.com/problems/string-to-integer-atoi/solution/zi-fu-chuan-zhuan-huan-zheng-shu-atoi-by-leetcode-/
     * @param str
     * @return
     */
    private int dfa(String str) {
        Automaton automaton = new Automaton();
        int length = str.length();
        for (int i = 0; i < length; ++i) {
            automaton.get(str.charAt(i));
        }
        return (int) (automaton.sign * automaton.ans);
    }

    class Automaton {
        public int sign = 1;
        public long ans = 0;
        private String state = "start";
        private Map<String, String[]> table = new HashMap<String, String[]>() {{
            put("start", new String[] {"start", "signed", "in_number", "end"});
            put("signed", new String[] {"end", "end", "in_number", "end"});
            put("in_number", new String[] {"end", "end", "in_number", "end"});
            put("end", new String[] {"end", "end", "end", "end"});
        }};

        public void get(char c) {
            state = table.get(state)[get_col(c)];
            if ("in_number".equals(state)) {
                ans = ans * 10 + c - '0';
                ans = sign == 1 ? Math.min(ans, (long)Integer.MAX_VALUE) : Math.min(ans, -(long)Integer.MIN_VALUE);
            } else if ("signed".equals(state)) {
                sign = c == '+' ? 1 : -1;
            }
        }

        private int get_col(char c) {
            if (c == ' ') {
                return 0;
            }
            if (c == '+' || c == '-') {
                return 1;
            }
            if (Character.isDigit(c)) {
                return 2;
            }
            return 3;
        }
    }
}


