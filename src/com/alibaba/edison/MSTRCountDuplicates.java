package com.alibaba.edison;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * author: qonyqian
 * created on: 2022/3/10 4:49 下午
 * version：1.0
 * description:
 */
public class MSTRCountDuplicates {

    // Complete the countDuplicates function below.
    static int countDuplicates(List<Integer> numbers) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (Integer value : numbers) {
            int count = map.getOrDefault(value, 0);
            map.put(value, count+1);
        }
        int count = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() >= 2) {
                count++;
            }
        }
        return count;
    }
}
