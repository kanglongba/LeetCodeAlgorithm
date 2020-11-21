package com.alibaba.edison;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by 钧戈 on 2020/10/30.
 */
public class Sword50 {

    public char firstUniqChar(String s) {
        return hashMap(s);
    }

    /**
     * 这是对hash表的考察。这道题应该使用LinkedHashMap
     * @param s
     * @return
     */
    private char hashMap(String s) {
        LinkedHashMap<Character, Boolean> map = new LinkedHashMap<>();
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            map.put(chars[i], !map.containsKey(chars[i]));
        }
        for (Map.Entry<Character, Boolean> entry : map.entrySet()){
            if (entry.getValue()) {
                return entry.getKey();
            }
        }
        return ' ';
    }
}
