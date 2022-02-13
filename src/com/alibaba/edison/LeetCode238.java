package com.alibaba.edison;

/**
 * 除自身以外数组的乘积，medium
 * 给你一个整数数组nums，返回 数组answer，其中answer[i]等于nums中除nums[i]之外其余各元素的乘积。
 * 题目数据 保证 数组nums之中任意元素的全部前缀元素和后缀的乘积都在 32 位 整数范围内。
 * 请不要使用除法，且在O(n) 时间复杂度内完成此题。
 *
 * 同 Sword66
 *
 * 非常经典的一道题
 *
 * 未能独立求得最优解
 *
 * author: qonyqian
 * created on: 2022/2/11 10:45 下午
 * version：1.0
 * description:
 */
public class LeetCode238 {


    /**
     * 1.最简单的就是暴力法，但是不符合O(n)的时间复杂度
     * 2.最简单的就是除法，但是题目要求不让用除法，所以必须想办法实现一个除法的效果。
     * 参考答案：https://leetcode-cn.com/problems/product-of-array-except-self/solution/cheng-ji-dang-qian-shu-zuo-bian-de-cheng-ji-dang-q/
     * @param nums
     * @return
     */
    public int[] productExceptSelf(int[] nums) {
        int[] res = new int[nums.length];
        int k = 1;
        for(int i = 0; i < res.length; i++){
            res[i] = k;
            k = k * nums[i]; // 此时数组存储的是除去当前元素左边的元素乘积
        }
        k = 1;
        for(int i = res.length - 1; i >= 0; i--){
            res[i] *= k; // k为该数右边的乘积。
            k *= nums[i]; // 此时数组等于左边的 * 该数右边的。
        }
        return res;
    }
}
