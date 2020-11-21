package com.alibaba.edison;

/**
 * Created by 钧戈 on 2020/9/17.
 */
public class Sword17 {

    //要考虑大数情况，这时应该用字符串表示数字
    public int[] printNumbers(int n) {
        return force(n);
    }

    private int[] force(int n) {
        int max=1;
        for (int i = 1; i <= n; i++) {
            max = max * 10;
        }
        max--;
        int[] result = new int[max];
        for (int i = 1; i <= max; i++) {
            result[i-1] = i;
        }
        return result;
    }
}
