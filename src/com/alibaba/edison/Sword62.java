package com.alibaba.edison;

/**
 * Created by 钧戈 on 2020/10/30.
 */
public class Sword62 {

    public int lastRemaining(int n, int m) {
        //著名的约瑟夫环问题
        int ans = 0;
        // 最后一轮剩下2个人，所以从2开始反推
        for (int i = 2; i <= n; i++) {
            ans = (ans + m) % i;
        }
        return ans;
    }
}
