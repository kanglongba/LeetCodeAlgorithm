package com.alibaba.edison;

/**
 * Created by 钧戈 on 2020/10/20.
 */
public class Sword16 {

    /**
     * 本题竟然用到了分治法
     * @param x
     * @param n
     * @return
     */
    public double myPow(double x, int n) {
        return fenzhi(x, n);
    }

    private double math(double x, int n) {
        return Math.pow(x, n);
    }

    //某些测试用例会StackOverflowError
    //可以用分治法优化，减少乘的次数
    private double digui(double x, int n) {
        if (n == 0) {
            return 1;
        }
        if (n > 0) {
            return x * digui(x, n-1);
        } else {
            return (1/x)* digui(x, n+1);
        }
    }

    private double fenzhi(double x, int n) {
        return n >= 0 ? fenzhiDigui(x, n) : 1.0 / fenzhiDigui(x, -n);
    }

    private double fenzhiDigui(double x, int n) {
        if (n == 0) {
            return 1.0;
        }
        double y = fenzhiDigui(x, n / 2);
        return n % 2 == 0 ? y * y : y * y * x;
    }

    //某些测试用例会StackOverflowError
    //可以用分治法优化，减少乘的次数
    private double self(double x, int n) {
        if (n == 0) {
            return 1;
        }
        if (n > 0) {
            double res = x;
            for (int i = 2; i <= n; i++) {
                res *= x;
            }
            return res;
        } else {
            double res = 1;
            for (int i = 1; i <= Math.abs(n) ; i++) {
                res *= x;
            }
            return 1/res;
        }
    }

    private double fenzhi2(double x, int n) {
        //必须是long，否则如果n=INT_MIN，-n会越界
        long N = n;
        return N >= 0 ? fenzhiSelf(x, N) : 1.0 / fenzhiSelf(x, -N);
    }

    private double fenzhiSelf(double x, long N) {
        double ans = 1.0;
        // 贡献的初始值为 x
        double x_contribute = x;
        // 在对 N 进行二进制拆分的同时计算答案
        while (N > 0) {
            if (N % 2 == 1) {
                // 如果 N 二进制表示的最低位为 1，那么需要计入贡献
                ans *= x_contribute;
            }
            // 将贡献不断地平方
            x_contribute *= x_contribute;
            // 舍弃 N 二进制表示的最低位，这样我们每次只要判断最低位即可
            N /= 2;
        }
        return ans;
    }

    private double fenzhi3(double x, int n) {
        if(x == 0.0f) return 0.0d;
        long b = n;
        double res = 1.0;
        if(b < 0) {
            x = 1 / x;
            b = -b;
        }
        while(b > 0) {
            if((b & 1) == 1) res *= x;
            x *= x;
            b >>= 1;
        }
        return res;
    }

}
