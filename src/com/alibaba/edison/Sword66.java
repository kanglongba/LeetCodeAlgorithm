package com.alibaba.edison;

/**
 * Created by 钧戈 on 2020/10/16.
 */
public class Sword66 {

    public int[] constructArr(int[] a) {
        return math(a);
    }

    /**
     * 数学方法
     * 对称遍历，上三角和下三角
     * @param a
     * @return
     */
    private int[] math(int[] a) {
        if (a == null || a.length == 0) {
            return new int[0];
        }
        int[] res = new int[a.length];
        res[0] = 1;
        //计算下三角
        for (int i = 1; i < a.length; i++) {
            res[i] = res[i-1] * a[i-1];
        }
        //计算上三角
        int temp = 1;
        for (int i = a.length-2; i > 0; i--) {
            temp *= a[i+1];
            res[i] *= temp;
        }
        return res;
    }

    /**
     * 暴力法
     * 超出时间限制了
     * @param a
     * @return
     */
    private int[] force(int[] a) {
        if (a == null) {
            return new int[0];
        }
        int[] res = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            int count = 1;
            for (int j = 0; j < a.length; j++) {
                if (j != i) {
                    count *= a[j];
                }
            }
            res[i] = count;
        }
        return res;
    }
}
